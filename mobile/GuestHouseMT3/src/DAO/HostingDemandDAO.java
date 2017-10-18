/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.HostingDemande;
import Entities.House;
import Entities.Notification;
import Entities.User;
import GUI.HostingDemandGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grami
 */
public class HostingDemandDAO {

    public void addHostingDemand(HostingDemande hd,int uid) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "hostingdemends/add.php?h=" +  hd.getHouseid()+ "&s=" +guesthouse.user.getId()+ "&r="  +uid+ "&t=" + hd.getText());
        NetworkManager.getInstance().addToQueue(cnx);
    }

    public void accept(HostingDemande hd) {
        //        ndao.add(new Notification("",new Date(Calendar.getInstance().getTimeInMillis()), "hd"+hd.getHouse().getId()) ,u.getMail());
        Notification n = new Notification();
        n.setText("Hosting Demand: your hosting demand has been accepted!!");
        n.setType("hd" + hd.getHouseid());
        new NotificationDAO().notify(n, hd.getSender().getId());
        delete(hd.getId());
    }

    public void decline(HostingDemande hd) {
        Notification n = new Notification();
        n.setText("Sorry : your hosting demand has been declined!!");

        new NotificationDAO().notify(n, hd.getSender().getId());
        delete(hd.getId());
    }

    private static String n = "0";

    public void count() {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "hostingdemends/count.php?id=" + guesthouse.user.getId()) {
            int ch;

            StringBuffer str;

            @Override
            protected void readResponse(InputStream input) throws IOException {
                str = new StringBuffer();
                while ((ch = input.read()) != -1) {
                    str.append((char) ch);
                }
            }

            @Override
            protected void postResponse() {
                if (!n.equals(str.toString())) {
                    LocalNotification no = new LocalNotification();
                    no.setId("hds");
                    no.setAlertBody("you have " + str.toString() + "new hosting demande");
                    no.setAlertTitle("Hosting Demands!");

                    Display.getInstance().scheduleLocalNotification(
                            no,
                            System.currentTimeMillis() + 10, // fire date/time
                            LocalNotification.REPEAT_NONE // Whether to repeat and what frequency
                    );

                    menus.hds.setCommandName(str.toString());
                    n = str.toString();
                    System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    guesthouse.getF().getToolbar().repaint();
                    guesthouse.getF().repaint();
                }
            }
        };
        NetworkManager.getInstance().addToQueue(cnx);

    }

    public void showAll() {
        ConnectionRequest cnx;
        Form f = guesthouse.getF();
        f.removeAll();

        System.out.println("beginning");
        cnx = new ConnectionRequest(guesthouse.localhost + "hostingdemends/findall.php?id=" + guesthouse.user.getId()) {

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {

                        HostingDemande hd = new HostingDemande();
                        hd.setId(Integer.parseInt((String) obj.get("id")));
                        hd.setText((String) obj.get("text"));
                        hd.setHouseid(Integer.parseInt((String) obj.get("houseid")));
                        ConnectionRequest cr = new ConnectionRequest(guesthouse.localhost + "users/getuser.php?id=" + Integer.parseInt((String) obj.get("senderid"))) {
                            User u = new User();

                            @Override
                            protected void readResponse(InputStream input) throws IOException {
                                JSONParser js = new JSONParser();
                                try {
                                    Reader reader = new InputStreamReader(input, "UTF-8");
                                    Map<String, Object> data = js.parseJSON(reader);

                                    List<Map<String, Object>> content
                                            = (List<Map<String, Object>>) data.get("root");

                                    for (Map<String, Object> obj : content) {

                                        u.setId(Integer.parseInt((String) obj.get("id")));
                                        u.setUserName((String) obj.get("userName"));
                                        u.setEmail((String) obj.get("email"));
                                        u.setCountry((String) obj.get("country"));
                                        u.setTown((String) obj.get("town"));
                                        u.setFirstName((String) obj.get("firstName"));
                                        u.setLastName((String) obj.get("lastName"));
                                        if (obj.get("phone") != null) {
                                            u.setPhone(Integer.parseInt((String) obj.get("phone")));
                                        }
                                        if (obj.get("points") != null) {
                                            u.setPoints(Integer.parseInt((String) obj.get("points")));
                                        }
                                        System.out.println("emchet hethi");
                                    }
                                } catch (IOException err) {
                                    System.out.println("not fout in user");
                                }
                            }

                            @Override
                            protected void postResponse() {
                                hd.setSender(u);
                                f.add(new HostingDemandGUI().viewHostingDemends(hd));
                                f.animateHierarchyFade(1000, 0);
                            }

                        };

                        NetworkManager.getInstance().addToQueue(cr);

                    }
                } catch (IOException err) {
                    System.out.println("not found in hosting demand");
                }

            }

            @Override
            protected void postResponse() {
            }
        };
        NetworkManager.getInstance().addToQueue(cnx);
        f.repaint();
    }

    private void delete(int hdid) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "hostingdemends/delete.php?id=" + hdid);
        NetworkManager.getInstance().addToQueue(cnx);
        showAll();
    }

    public void finishTheReservation(int id) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "date/findbyhouse.php?id=" + id) {

            List<String> dates = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {
                        dates.add((String) obj.get("date"));
                    }
                } catch (IOException err) {
                    System.out.println("not found in hosting demand");
                }

            }

            @Override
            protected void postResponse() {

                ConnectionRequest connectionRequest;

                connectionRequest = new ConnectionRequest() {
                    House h = new House();

                    @Override
                    protected void readResponse(InputStream in) throws IOException {
                        JSONParser json = new JSONParser();

                        try {
                            Reader reader = new InputStreamReader(in, "UTF-8");
                            Map<String, Object> data = json.parseJSON(reader);

                            List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");

                            for (Map<String, Object> obj : content) {

                                h = new House(Integer.parseInt((String) obj.get("id")), (String) obj.get("country"), (String) obj.get("town"), (String) obj.get("nature"), Integer.parseInt((String) obj.get("price")), (String) obj.get("description"), Integer.parseInt((String) obj.get("rating")));
                                User u = new User();
                                u.setId(Integer.parseInt((String) obj.get("userid")));
                                u.setUserName((String) obj.get("userName"));
                                u.setEmail((String) obj.get("email"));
                                u.setCountry((String) obj.get("ucountry"));
                                u.setTown((String) obj.get("utown"));
                                u.setFirstName((String) obj.get("firstName"));
                                u.setLastName((String) obj.get("lastName"));
                                if (obj.get("phone") != null) {
                                    u.setPhone(Integer.parseInt((String) obj.get("phone")));
                                }
                                if (obj.get("points") != null) {
                                    u.setPoints(Integer.parseInt((String) obj.get("points")));
                                }
                                h.setOwner(u);

                            }
                        } catch (IOException err) {

                        }

                    }

                    @Override
                    protected void postResponse() {
                        Dialog d = new Dialog("finish the reservation");
                        d.add(new HostingDemandGUI().setDates(dates, h));
                        d.setLayout(BoxLayout.y());
                        d.setDisposeWhenPointerOutOfBounds(true);
                        d.show();
                    }
                };

                connectionRequest.setUrl(guesthouse.localhost + "house/gethouse.php?id=" + id);
                NetworkManager.getInstance().addToQueue(connectionRequest);

            }
        };
        NetworkManager.getInstance().addToQueue(cnx);

    }

    public void deletedate(String d, int id) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "date/deletedate.php?id=" + id + "&d=" + d);
        NetworkManager.getInstance().addToQueue(cnx);
    }
}
