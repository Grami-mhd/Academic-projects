/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Notification;
import GUI.NotificationGUI;
import com.mycompany.myapp.guesthouse;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.mycompany.myapp.menus;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author grami
 */
public class NotificationDAO {

    public void getNotifications(int id) {

        Set<Notification> notifs = new TreeSet<>();

        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost + "notifications/getnotifications.php?id=" + id) {

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {

                        Notification n = new Notification();
                        n.setId(Integer.parseInt((String) obj.get("id")));

                        if (Integer.parseInt((String) obj.get("isSeen")) == 0) {
                            n.setIsSeen(false);
                        } else {
                            n.setIsSeen(true);
                        }

                        n.setDate(obj.get("date").toString());
                        n.setText((String) obj.get("text"));
                        n.setType((String) obj.get("type"));
                        notifs.add(n);
                    }
                } catch (IOException err) {
                }
            }

            @Override
            protected void postResponse() {

                Form lf = new Form();
                menus.stBack(lf);
                menus.setmenu(lf);
                lf.add(NotificationGUI.showNotifications(notifs));
                lf.show();

            }

        };

        NetworkManager.getInstance().addToQueue(cr);

    }
    private static String n = "0";

    public void countNotifications(int id) {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "notifications/countnotifications.php?id=" + id) {
            int ch;

            StringBuffer str;

            @Override
            protected void readResponse(InputStream input) throws IOException {
                str = new StringBuffer();
                while ((ch = input.read()) != -1) {
                    str.append((char) ch);
                }
                if (!n.equals(str.toString())) {
                    menus.notifs.setCommandName(str.toString());
                    n = str.toString();
                    guesthouse.getF().getToolbar().repaint();
                    guesthouse.getF().repaint();
                }
                 
            }

            @Override
            protected void postResponse() {
               
            }

        };

        NetworkManager.getInstance().addToQueue(cnx);
        
    }

    public void deleteNotification(int notifid) {
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

            }

            @Override
            protected void postResponse() {
                getNotifications(guesthouse.user.getId());
            }

        };

        connectionRequest.setUrl(guesthouse.localhost + "notifications/delete.php?id=" + notifid);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void setSeen(int id) {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "notifications/setseen.php?id=" + id) {

            @Override
            protected void readResponse(InputStream input) throws IOException {
            }

            @Override
            protected void postResponse() {

            }

        };
        NetworkManager.getInstance().addToQueue(cnx);
    }

    public void notify(Notification n, int userid) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "notifications/notify.php?id=" + userid + "&txt=" + n.getText() + "&t=" + n.getType());
        NetworkManager.getInstance().addToQueue(cnx);
    }
}
