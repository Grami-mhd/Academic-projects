/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import DAO.AddDAO;
import DAO.ClaimsDAO;
import DAO.ForumDAO;
import DAO.HostingDemandDAO;
import DAO.HouseDAO;
import DAO.NotificationDAO;
import DAO.StayDAO;
import GUI.ProfileGUI;
import GUI.UserGUI;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

/**
 *
 * @author grami
 */
public abstract class menus {

    public static ActionListener search = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        }
    };
    public static String key;
    public static Command notifs;
    public static Command hds;

    public static void setmenu(Form f) {

        Toolbar t = f.getToolbar();

        try {
            t.addCommandToSideMenu("Home", Image.createImage("/home.png").scaled(40, 40), (ActionListener) (ActionEvent evt) -> {
                guesthouse.getF().removeAll();
                    new AddDAO().getAds();
                    guesthouse.getF().repaint();
            });
        } catch (IOException ex) {
            System.out.println("Home icon not found");
        }

        
            t.addCommandToSideMenu("Profile", getProfilePicture().scaled(40, 40), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    guesthouse.getF().removeAll();
                    guesthouse.getF().add(new ProfileGUI().showProfile());
                    guesthouse.getF().repaint();
                }
            });
        
        try {
            t.addCommandToSideMenu("Houses", Image.createImage("/houses.png").scaled(40, 40), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("houses");
                    guesthouse.getF().removeAll();
                    new HouseDAO().getHouses();
                    guesthouse.getF().repaint();

                }
            });
        } catch (IOException ex) {
            System.out.println("house icon not found");
        }
        try {
            t.addCommandToSideMenu("Stays", Image.createImage("/Stay.png").scaled(40, 40), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     new StayDAO().getstay();
                }
            });
        } catch (IOException ex) {
            System.out.println("Stays icon not found");
        }
        try {
            t.addCommandToSideMenu("Forums", Image.createImage("/forum.png").scaled(40, 40), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ForumDAO fd = new ForumDAO();
                    fd.setSearchProtocol();
                    fd.getAllForums();
                    fd.getMyForums();
                    fd.getOldDiscussions();
                    
                }
            });
        } catch (IOException ex) {
            System.out.println("Forums icon not found");
        }
        try {
            t.addCommandToSideMenu("Claims", Image.createImage("/claim.png").scaled(40, 40), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new ClaimsDAO().getClaims();
                }
            });
        } catch (IOException ex) {
            System.out.println("Claims icon not found");
        }
        try {
            t.setTitleComponent(new ImageViewer(Image.createImage("/logo.png").scaled(300, 40)));
        } catch (IOException ex) {
        }

        t.addSearchCommand((ActionListener) (ActionEvent evt) -> {
            key = evt.getSource().toString();
            System.out.println(evt.getSource().toString());
            search.actionPerformed(evt);
        });

    }

    public static void setSearch(Form f) {

        guesthouse.getF().getToolbar().addSearchCommand((ActionListener) (ActionEvent evt) -> {
            key = evt.getSource().toString();
            System.out.println(evt.getSource().toString());
            search.actionPerformed(evt);
        });
    }

    public static void setNotifications() {

        Form f = guesthouse.getF();
        Toolbar t = f.getToolbar();
        try {
            notifs = Command.create("0", Image.createImage("/notifs.png").scaled(30, 30), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new NotificationDAO().getNotifications(guesthouse.user.getId());
                }
            }
            );
        } catch (IOException ex) {
        }
        t.addCommandToRightBar(notifs);
        t.repaint();

        new notifsThread().start();
    }

    public static void stBack(Form f) {

        Image imBa;
        try {
            imBa = Image.createImage("/backI.png").scaled(30, 30);

            Toolbar t = f.getToolbar();
            t.addCommandToLeftBar("back", imBa, (ActionListener) (ActionEvent evt) -> {
                guesthouse.getF().showBack();
            });
        } catch (IOException ex) {
        }
    }

    public static void addConnect(Form f) {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserGUI.logIn();
            }
        };

        try {
            f.getToolbar().addCommandToRightBar("Connect", Image.createImage("/connect.png").scaled(40, 40), al);
        } catch (IOException ex) {
            System.out.println("connect pic nor found");
        }
    }

    public static void setHostingDemands(Form f) {

        Toolbar t = f.getToolbar();
        try {
            hds = Command.create("0", Image.createImage("/houses.png").scaled(30, 30), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new HostingDemandDAO().showAll();
                }
            }
            );
        } catch (IOException ex) {
        }
        t.addCommandToLeftBar(hds);
        t.repaint();

    }

    public static Image getProfilePicture() {
        EncodedImage enc;
        String url=guesthouse.localhost+"profile.png";
        Image imgs;
        
        try {
            enc = EncodedImage.create("/star.png");
            imgs = URLImage.createToStorage(enc, "imge", url, URLImage.RESIZE_SCALE);
            return imgs;
        } catch (IOException ex) {
            System.out.println(ex.toString() + "ma5edmetch");
        }
        return null;
    }
}
