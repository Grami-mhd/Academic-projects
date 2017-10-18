/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.User;
import GUI.UserGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grami
 */
public class UserDAO {

    public void login(String uname, String pw) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost+"users/login.php?un=" + uname + "&pw=" + pw) {
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
                if (str.toString().substring(0, 2).equals("ok")) {
                    int id = Integer.parseInt(str.toString().substring(2));
                    getUser(id);
                    
                } else if (!Dialog.show(str.toString(), "", "retry", "cancle")) {
                    System.out.println("sign up");
                } else if (str.toString().equals("wrong password")) {
                    UserGUI.password.requestFocus();
                } else {
                    UserGUI.username.requestFocus();
                }
            }

        };
        NetworkManager.getInstance().addToQueue(cnx);
        
        
    }

    public void getUser(int id) {

        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost + "users/getuser.php?id=" + id) {

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {

                        User u = new User();
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
                        
                        guesthouse.user = u;
                        Form f =guesthouse.getF();
                        f.setToolbar(new Toolbar());   
                        menus.setmenu(f);
                         menus.setNotifications();
                         menus.setHostingDemands(f);
                        f.show();
                    }
                } catch (IOException err) {
                }
            }

            @Override
            protected void postResponse() {

            }

        };

        NetworkManager.getInstance().addToQueue(cr);
    }

    public void signup( User u,String pw){
        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost+"users/signup.php?un="+u.getUserName()+"&pw="+pw+"&em="+u.getEmail()+"&fn="+u.getFirstName()+"&ln="+u.getLastName()) {
            @Override
            protected void postResponse() {
                login(u.getUserName(),pw);
            }

        };

        NetworkManager.getInstance().addToQueue(cr);
    }
    
    public void sheck(User u){
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost+"users/sheck.php?un=" + u.getUserName() + "&m=" +u.getEmail()) {
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
                if(str.toString().equals("00")){
                    signup(u, UserGUI.spassword.getText());
                }else{
                    if(str.toString().substring(0, 1).equals("0")){
                        Dialog.show("error", "email already exists", "okay","okay");
                        UserGUI.email.requestFocus();
                    }else{
                        Dialog.show("error", "Username already exists", "okay","okay");
                        UserGUI.susername.requestFocus();
                    }
                }
            }

        };
        NetworkManager.getInstance().addToQueue(cnx);
    }
    
    
    public void updateProfile(){
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "users/updateuser.php?id="+guesthouse.user.getId()+"&firstname=" + guesthouse.user.getFirstName()+"&lastname="+guesthouse.user.getLastName()+"&town="+guesthouse.user.getTown());
        NetworkManager.getInstance().addToQueue(cnx);
    
    }
    
    public void updatePoints(int newPoints, int id){
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "users/upDateUserPoints.php?points="+newPoints+"&id="+id);
        NetworkManager.getInstance().addToQueue(cnx);
    }
}
