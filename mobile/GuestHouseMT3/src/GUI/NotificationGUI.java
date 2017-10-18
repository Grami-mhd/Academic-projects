/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ClaimsDAO;
import DAO.ForumDAO;
import DAO.HostingDemandDAO;
import DAO.NotificationDAO;
import Entities.Notification;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author grami
 */
public class NotificationGUI {

    public static Container showNotifications(Set<Notification> l) {
        Container mother = new Container(BoxLayout.y());
        for (Notification n : l) {
            Container notif = new Container(BoxLayout.x());
            Container txt = new Container(BoxLayout.y());
            Label dt = new Label(n.getDate());
            dt.getStyle().setFgColor(0x5499C7);
            txt.add(dt);
            SpanLabel s = new SpanLabel(n.getText());

            Button img = new Button();
            img.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (!n.isIsSeen()) {
                        new NotificationDAO().setSeen(n.getId());
                    }
                    if (n.getType() != null) {
                        if (n.getType().substring(0, 2).equals("hd")) {
                            new HostingDemandDAO().finishTheReservation(Integer.parseInt(n.getType().substring(2)));
                        }
                        if (n.getType().substring(0, 2).equals("fo")) {
                            ForumDAO fd = new ForumDAO();
                            fd.setSearchProtocol();
                            fd.getAllForums();
                            fd.getMyForums();
                            fd.getOldDiscussions();
                        }
                        if (n.getType().substring(0, 2).equals("cl")) {
                            new ClaimsDAO().getClaims();
                        }
                    }
                }
            });

            txt.add(s);

            if (n.getType() != null) {
                if (n.getType().substring(0, 2).equals("hd")) {
                    try {
                        img.setIcon(Image.createImage("/houses.png").scaled(50, 50));
                    } catch (IOException ex) {
                        System.out.println("houses pic not found");
                    }
                }
                if (n.getType().substring(0, 2).equals("fo")) {
                    try {
                        img.setIcon((Image.createImage("/forum.png").scaled(50, 50)));
                    } catch (IOException ex) {
                        System.out.println("forums pic not found");
                    }
                }
                if (n.getType().substring(0, 2).equals("cl")) {
                    try {
                        img.setIcon((Image.createImage("/claim.png").scaled(50, 50)));
                    } catch (IOException ex) {
                        System.out.println("claims pic not found");
                    }
                }
            } else {
                try {
                    img.setIcon((Image.createImage("/notifs.png").scaled(50, 50)));
                } catch (IOException ex) {
                    System.out.println("notifs pic not found");
                }
            }
            notif.add(img);
            notif.add(txt);

            if (!n.isIsSeen()) {
                try {
                    notif.add(new ImageViewer(Image.createImage("/New.png").scaled(50, 50)));
                } catch (IOException ex) {
                    System.out.println("new pic not found");
                }
            }

            ActionListener al = new ActionListener() {
                private Button nc;
                private boolean on = true;

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (on) {
                        try {
                            nc = (Button) notif.getComponentAt(0);
                            Button delete = new Button(Image.createImage("/delete.png").scaled(50, 50));
                            delete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (Dialog.show(n.getText(), "confirm deleting", "yes", "no")) {
                                        new NotificationDAO().deleteNotification(n.getId());
                                    }
                                }
                            });

                            notif.replaceAndWait(nc, delete, CommonTransitions.createSlideFadeTitle(false, 1000));
                        } catch (IOException ex) {
                            System.out.println("delete pic not found");
                        }
                        on = false;
                    } else {

                        notif.replaceAndWait(notif.getComponentAt(0), nc, CommonTransitions.createSlideFadeTitle(true, 1000));
                        on = true;
                    }
                }

            };
            notif.addPointerReleasedListener(al);

            mother.add(notif);
        }
        return mother;
    }

}
