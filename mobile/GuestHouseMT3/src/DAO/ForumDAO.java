/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Forum;
import Entities.Message;
import Entities.User;
import GUI.ForumsGUI;
import GUI.singleForumGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
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
 * @author Mohamed
 */
public class ForumDAO {

    //private List<Forum> listF;
    public void getAllForums() {

        ConnectionRequest cr;
        cr = new ConnectionRequest() {

            List<Forum> lf = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    lf.clear();
                    for (Map<String, Object> obj : content) {

                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("forumownerid")));
                        u.setFirstName((String) obj.get("firstname"));
                        u.setLastName((String) obj.get("lastname"));

                        Forum f = new Forum();
                        if (Integer.parseInt((String) obj.get("iscreated")) == 0) {
                            f.setCreated(false);
                        } else {
                            f.setCreated(true);
                        }

                        f.setId(Integer.parseInt((String) obj.get("id")));
                        f.setSubject((String) obj.get("subject"));
                        f.setUser(u);
                        f.setNbrMessages(Integer.parseInt((String) obj.get("nbrmessages")));

                        lf.add(f);

                    }

                } catch (IOException err) {
                    Log.e(err);
                }
                //listF = lf;
            }

            @Override
            protected void postResponse() {
                Form f = guesthouse.getF();
                f.removeAll();
                f.add(new ForumsGUI().addForum());
                Label l = new Label("All forums");

                l.getStyle().setBorder(Border.createDashedBorder(1, 0x212F3D));
                l.getStyle().setBgColor(0xE74C3C);

                f.add(l);
                f.add(new ForumsGUI().viewForums(lf));
                //getMyForums();
                f.repaint();
            }

        };
        cr.setUrl(guesthouse.localhost + "forums/findall.php");
        NetworkManager.getInstance().addToQueue(cr);
    }

    public void getMyForums() {

        ConnectionRequest cr;
        cr = new ConnectionRequest() {
            //Container af= new Container();
            List<Forum> lf = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    lf.clear();
                    for (Map<String, Object> obj : content) {

                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("forumownerid")));
                        u.setFirstName((String) obj.get("firstname"));
                        u.setLastName((String) obj.get("lastname"));

                        Forum f = new Forum();
                        if (Integer.parseInt((String) obj.get("iscreated")) == 0) {
                            f.setCreated(false);
                        } else {
                            f.setCreated(true);
                        }

                        f.setId(Integer.parseInt((String) obj.get("id")));
                        f.setSubject((String) obj.get("subject"));
                        f.setUser(u);
                        f.setNbrMessages(Integer.parseInt((String) obj.get("nbrmessages")));

                        lf.add(f);

                    }

                } catch (IOException err) {
                    Log.e(err);
                }
                //listF = lf;
            }

            @Override
            protected void postResponse() {
                Form f = guesthouse.getF();
                Label l = new Label("My Forums");

                l.getStyle().setBorder(Border.createDashedBorder(1, 0x212F3D));
                l.getStyle().setBgColor(0xE74C3C);
                f.add(l);

                f.add(new ForumsGUI().viewmyForums(lf));
                f.repaint();
            }

        };
        cr.setUrl(guesthouse.localhost + "forums/findmyforums.php?id=" + guesthouse.user.getId());
        NetworkManager.getInstance().addToQueue(cr);

    }

    public void getOldDiscussions() {

        ConnectionRequest cr;
        cr = new ConnectionRequest() {

            List<Forum> lf = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    lf.clear();
                    for (Map<String, Object> obj : content) {

                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("forumownerid")));
                        u.setFirstName((String) obj.get("firstname"));
                        u.setLastName((String) obj.get("lastname"));

                        Forum f = new Forum();
                        if (Integer.parseInt((String) obj.get("iscreated")) == 0) {
                            f.setCreated(false);
                        } else {
                            f.setCreated(true);
                        }

                        f.setId(Integer.parseInt((String) obj.get("forumid")));
                        f.setSubject((String) obj.get("subject"));
                        f.setUser(u);
                        f.setNbrMessages(Integer.parseInt((String) obj.get("nbrmessages")));

                        lf.add(f);

                    }

                } catch (IOException err) {
                    Log.e(err);
                }
                //listF = lf;
            }

            @Override
            protected void postResponse() {
                Form f = guesthouse.getF();
                Label l = new Label("My old discussions");
                l.getStyle().setBgColor(0xE74C3C);
                l.getStyle().setBorder(Border.createDashedBorder(1, 0x212F3D));
                l.getStyle().setBgColor(0xE74C3C);

                f.add(l);
                f.add(new ForumsGUI().viewForums(lf));
                //getMyForums();
                f.repaint();
            }

        };
        cr.setUrl(guesthouse.localhost + "forums/findolddiscussions.php?id=" + guesthouse.user.getId());
        NetworkManager.getInstance().addToQueue(cr);

    }

    public void getForumMessages(int id) {

        ConnectionRequest cr;
        cr = new ConnectionRequest() {

            List<Message> lm = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    lm.clear();
                    for (Map<String, Object> obj : content) {

                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("msgownerid")));
                        u.setFirstName((String) obj.get("firstname"));
                        u.setLastName((String) obj.get("lastname"));

                        Message m = new Message();

                        m.setId(Integer.parseInt((String) obj.get("msgid")));
                        m.setText((String) obj.get("text"));
                        m.setUser(u);

                        lm.add(m);

                    }

                } catch (IOException err) {
                    Log.e(err);
                }
                //listF = lf;
            }

            @Override
            protected void postResponse() {
                Form f = guesthouse.getF();

                f.add(new singleForumGUI().showMessages(lm));
                //getMyForums();
                f.repaint();
            }

        };
        cr.setUrl(guesthouse.localhost + "forums/find_forum_messages.php?id=" + id);
        NetworkManager.getInstance().addToQueue(cr);

    }

    public void addForum(Forum f) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "forums/addforum.php?forumownerid=" + guesthouse.user.getId() + "&subject=" + f.getSubject());
        NetworkManager.getInstance().addToQueue(cnx);

    }

    public void deleteForum(Forum f) {

        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

            }

            @Override
            protected void postResponse() {

                new ForumDAO().getAllForums();
                new ForumDAO().getMyForums();
                new ForumDAO().getOldDiscussions();
            }

        };

        connectionRequest.setUrl(guesthouse.localhost + "forums/deleteforum.php?id=" + f.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void searchforum(String key) {
        System.out.println("hhhhhhhhhhhi");
        ConnectionRequest cr;
        cr = new ConnectionRequest() {

            List<Forum> lf = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    lf.clear();
                    for (Map<String, Object> obj : content) {

                        User u = new User();
                        u.setFirstName((String) obj.get("firstname"));
                        u.setLastName((String) obj.get("lastname"));

                        Forum f = new Forum();
                        if (Integer.parseInt((String) obj.get("iscreated")) == 0) {
                            f.setCreated(false);
                        } else {
                            f.setCreated(true);
                        }

                        f.setId(Integer.parseInt((String) obj.get("id")));
                        f.setSubject((String) obj.get("subject"));
                        f.setUser(u);
                        f.setNbrMessages(Integer.parseInt((String) obj.get("nbrmessages")));

                        lf.add(f);

                    }

                } catch (IOException err) {
                    Log.e(err);
                }

            }

            @Override
            protected void postResponse() {
                Form f = guesthouse.getF();
                f.removeAll();
                f.add("Searching result :");
                f.add(new ForumsGUI().viewForums(lf));
                System.out.println("vvvvvv");
                f.repaint();
            }

        };
        cr.setUrl(guesthouse.localhost + "forums/searchforum.php?key=" + key);
        NetworkManager.getInstance().addToQueue(cr);

    }

    public void updateforum(Forum f) {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "forums/updateforum.php?sub=" + f.getSubject() + "&id=" + f.getId());
        System.out.println(cnx.getUrl());
        NetworkManager.getInstance().addToQueue(cnx);

    }

    public void setSearchProtocol() {
        System.out.println("hhhhh");
        menus.search = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                searchforum(menus.key);
                System.out.println("llllll");
            }
        };
    }

}
