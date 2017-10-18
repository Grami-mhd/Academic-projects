/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ForumDAO;
import Entities.Forum;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class ForumsGUI {

    public Container viewForums(List<Forum> ll) {

        List<Forum> createdF = new ArrayList<>();
        Container all;
        Container forum;
        Container txt;
        Container cont;
        all = new Container(BoxLayout.y());

        for (Forum fr : ll) {

            if (fr.isCreated()) {

                createdF.add(fr);
            }
        }

        for (Forum fff : createdF) {

            cont = new Container(BoxLayout.x());
            forum = new Container(BoxLayout.x());

            txt = new Container(BoxLayout.y());

            SpanLabel sup = new SpanLabel("Subject :   " + fff.getSubject());
            sup.setWidth(200);
            txt.add(sup);
            
            Label s = new Label(fff.getUser().getFirstName() + " " + fff.getUser().getLastName());
            s.getStyle().setFgColor(0x1F618D);
            

            Label nbr = new Label("Nb Msg =" + fff.getNbrMessages());
            nbr.getStyle().setTextDecoration(Style.TEXT_DECORATION_3D_SHADOW_NORTH);
            nbr.getStyle().setFgColor(0xFFC300);
            
            cont.add(s);
            cont.add(nbr);
            txt.add(cont);

            Button b = new Button();
            try {
                b.setIcon(Image.createImage("/forum.png"));
            } catch (IOException ex) {
            }
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f = guesthouse.getF();
                    f.removeAll();
                    //f.add("View forum");
                    f.add(new singleForumGUI().viewSingleForum(fff));
                    new ForumDAO().getForumMessages(fff.getId());
                    //getMyForums();
                    f.repaint();
                }
            });

            forum.add(b);
            forum.add(txt);

            all.add(forum);
            forum.setLeadComponent(b);

        }

        return all;

    }

    public Container addForum() {
        Container createforum = new Container(BoxLayout.y());

        Container subContainer = new Container(BoxLayout.y());

        TextArea suj = new TextArea(2, 50);
        suj.setHint("subject here...");

        Button addf = new Button("Subbmit");
        addf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Forum ff = new Forum();
                ff.setUser(guesthouse.user);
                ff.setSubject(suj.getText());

                suj.setText("");

                new ForumDAO().addForum(ff);

            }
        });
        subContainer.add(suj);

        subContainer.add(addf);

        createforum.add(new Label("Create forum"));
        createforum.add(subContainer);

        return createforum;
    }

    public Container viewmyForums(List<Forum> ll) {

        List<Forum> createdF = new ArrayList<>();
        Container assemb;
        Container buttons;
        Container all;
        Container forum;
        Container txt;
        Container cont;
        all = new Container(BoxLayout.y());

        for (Forum fr : ll) {

            if (fr.isCreated()) {

                createdF.add(fr);
            }
        }

        for (Forum fff : createdF) {
            assemb = new Container(BoxLayout.y());
            cont = new Container(BoxLayout.x());
            forum = new Container(BoxLayout.x());
            buttons = new Container(BoxLayout.y());

            txt = new Container(BoxLayout.y());

            SpanLabel sup = new SpanLabel("Subject :   " + fff.getSubject());
            sup.setWidth(200);
            txt.add(sup);
            Label s = new Label(fff.getUser().getFirstName() + " " + fff.getUser().getLastName());
            s.getStyle().setFgColor(0x1F618D);

            Label nbr = new Label("Nb Msg =  " + fff.getNbrMessages());
            nbr.getStyle().setTextDecoration(Style.TEXT_DECORATION_3D_SHADOW_NORTH);
            nbr.getStyle().setFgColor(0xFFC300);
            

            cont.add(s);
            cont.add(nbr);

            txt.add(cont);

            Button b = new Button();
            try {
                b.setIcon(Image.createImage("/forum.png").scaled(30, 30));
            } catch (IOException ex) {
            }
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f = guesthouse.getF();
                    f.removeAll();
                    //f.add("View forum");
                    f.add(new singleForumGUI().viewSingleForum(fff));
                    new ForumDAO().getForumMessages(fff.getId());
                    //getMyForums();
                    f.repaint();
                }
            });

            Button bt = new Button();
            try {
                bt.setIcon(Image.createImage("/delete.png").scaled(30, 30));
            } catch (IOException ex) {
            }
            bt.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Dialog.show("Forum delete :", "Are you sure to proceed deleting this forum", "Confirm", "cancel")) {
                        new ForumDAO().deleteForum(fff);
                    }

                }
            });

            buttons.add(b);
            buttons.add(bt);

            forum.add(buttons);
            forum.add(txt);

            assemb.add(forum);
            assemb.add("___________________________________________________");

            all.add(assemb);
            txt.setLeadComponent(b);

        }

        return all;

    }
    
//    public Container searchForums(List<Forum> ll){
//        
//        List<Forum> createdF = new ArrayList<>();
//        Container all;
//        Container forum;
//        Container txt;
//        Container cont;
//        all = new Container(BoxLayout.y());
//        
//        for (Forum fr : ll) {
//
//            if (fr.isCreated()) {
//
//                createdF.add(fr);
//            }
//        }
//
//        for (Forum fff : createdF) {
//
//            cont = new Container(BoxLayout.x());
//            forum = new Container(BoxLayout.x());
//
//            txt = new Container(BoxLayout.y());
//
//            SpanLabel sup = new SpanLabel("Subject :" + fff.getSubject());
//            sup.setWidth(200);
//            txt.add(sup);
//            
//            Label s = new Label(fff.getUser().getFirstName() + " " + fff.getUser().getLastName());
//            s.getStyle().setFgColor(0x1153E1);
//
//            Label nbr = new Label("Nb Msg =" + fff.getNbrMessages());
//            nbr.getStyle().setTextDecoration(Style.TEXT_DECORATION_3D_SHADOW_NORTH);
//            
//            cont.add(s);
//            cont.add(nbr);
//            txt.add(cont);
//
//            Button b = new Button();
//            try {
//                b.setIcon(Image.createImage("/forum.png"));
//            } catch (IOException ex) {
//            }
//            b.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//
//                    Form f = guesthouse.getF();
//                    f.removeAll();
//                    //f.add("View forum");
//                    f.add(new singleForumGUI().viewSingleForum(fff));
//                    new ForumDAO().getForumMessages(fff.getId());
//                    //getMyForums();
//                    f.repaint();
//                }
//            });
//
//            forum.add(b);
//            forum.add(txt);
//
//            all.add(forum);
//            forum.setLeadComponent(b);
//
//        }
//
//        return all;
//    }
}
