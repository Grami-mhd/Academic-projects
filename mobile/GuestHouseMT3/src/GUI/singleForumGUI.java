/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ForumDAO;
import DAO.MessageDAO;
import Entities.Forum;
import Entities.Message;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class singleForumGUI {

    private static Forum f;

    public Container viewSingleForum(Forum f) {

        this.f = f;
        Container sub;
        Container entete;
        Container sous_subj;
        System.out.println(f.getUser().getId());
        sous_subj = new Container(BoxLayout.x());
        entete = new Container(BoxLayout.y());
        SpanLabel subj = new SpanLabel("Subject :" + f.getSubject());
        subj.setWidth(200);
        Label s = new Label(f.getUser().getFirstName() + " " + f.getUser().getLastName());
        s.getStyle().setFgColor(0xFF000);
        Label nbr = new Label("Nb Msg =" + f.getNbrMessages());
        nbr.getStyle().setTextDecoration(Style.TEXT_DECORATION_3D_SHADOW_NORTH);
        sous_subj.add(s);
        sous_subj.add(nbr);
        entete.add(subj);
        entete.add(sous_subj);
        entete.add("_______________________________________________________________________________________________");
        ActionListener a = (ActionListener) (ActionEvent evt) -> {
            if (entete.getComponentAt(0) instanceof SpanLabel) {
                Container bla;
                TextArea t = new TextArea(f.getSubject());
                bla = new Container(BoxLayout.y());
                bla.add(t);
                try {
                    Container buttons = new Container(BoxLayout.x());
                    Button update = new Button(Image.createImage("/claim.png").scaled(30, 30));
                    
                    update.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                    
                            singleForumGUI.f.setSubject(t.getText());
                            
                            new ForumDAO().updateforum(singleForumGUI.f);
                             Form form = guesthouse.getF();
                            form.removeAll();
                            form.add(new singleForumGUI().viewSingleForum(f));
                            new ForumDAO().getForumMessages(f.getId());
                            form.repaint();
                            
                        }
                    });
                    buttons.add(update);
                    
                    bla.add(buttons);
                    
                } catch (IOException ex) {
                    
                }

                entete.replaceAndWait(entete.getComponentAt(0), bla, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 1000));
            } else {
                
                entete.replaceAndWait(entete.getComponentAt(0), new SpanLabel("Subject :" + f.getSubject()), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 1000));
            }
            entete.repaint();
        };
        if (f.getUser().getId() == guesthouse.user.getId()) {
            entete.addPointerReleasedListener(a);
        }

        return entete;
    }

    public Container showMessages(List<Message> lm) {

        Container mother = new Container(BoxLayout.y());

        for (Message m : lm) {

            Container msg = new Container(BoxLayout.y());

            SpanLabel txt = new SpanLabel(m.getText());
            Label msgowner = new Label(m.getUser().getFirstName() + " " + m.getUser().getLastName());
            msgowner.getStyle().setFgColor(0x154360);

            msg.add(txt);
            msg.add(msgowner);
            ActionListener a = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (msg.getComponentAt(0) instanceof SpanLabel) {
                        Container bla;

                        if (m.getUser().getId() == guesthouse.user.getId()) {
                            TextArea t = new TextArea(m.getText());
                            bla = new Container(BoxLayout.y());
                            bla.add(t);
                            try {
                                Container buttons = new Container(BoxLayout.x());
                                Button update = new Button(Image.createImage("/claim.png").scaled(30, 30));
                                Button delete = new Button(Image.createImage("/delete.png").scaled(30, 30));

                                delete.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        m.setForum(f);
                                        new MessageDAO().deleteMesssage(m);

                                    }
                                });
                                
                                update.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        
                                        /*******update message********/
                                        
                                        m.setText(t.getText());
                            
                            new MessageDAO().updatemessage(m);
                            Form form = guesthouse.getF();
                            form.removeAll();
                            form.add(new singleForumGUI().viewSingleForum(f));
                            new ForumDAO().getForumMessages(f.getId());
                            form.repaint();
                                    }
                                });
                                buttons.add(update);
                                buttons.add(delete);
                                bla.add(buttons);

                            } catch (IOException ex) {

                            }

                            msg.replaceAndWait(msg.getComponentAt(0), bla, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 1000));

                        } else {
                            bla = new Container(BoxLayout.x());
                            Button report = new Button("report");
                            report.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent evt) {

                                    new MessageDAO().reportMessage(m);

                                }
                            });
                            bla.add(report);
                            SpanLabel txt = new SpanLabel(m.getText());
                            bla.add(txt);
                            msg.replaceAndWait(msg.getComponentAt(0), bla, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 1000));

                        }
                    } else {

                        msg.replaceAndWait(msg.getComponentAt(0), new SpanLabel(m.getText()), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 1000));
                    }
                    msg.repaint();
                }

            };
            msg.addPointerReleasedListener(a);

            mother.add(msg);
            msg.add("_________________________________________________");

        }
        Container postcont = new Container(BoxLayout.x());

        TextArea msgtopost = new TextArea(4, 100);

        msgtopost.setHint("Type your message here ...");
        Button post = new Button("Post");
        post.addActionListener((ActionListener) (ActionEvent evt) -> {

            String m = msgtopost.getText();
            System.out.println(m);
            Message ms = new Message();
            ms.setText(m);
            ms.setUser(guesthouse.user);
            ms.setForum(f);
            System.out.println(f.getSubject());
            new MessageDAO().addMessage(ms);

            Form frm = guesthouse.getF();
            frm.removeAll();
            //f.add("View forum");
            frm.add(new singleForumGUI().viewSingleForum(f));
            new ForumDAO().getForumMessages(f.getId());
            //getMyForums();
            frm.repaint();
            msgtopost.setText("");
            msgtopost.requestFocus();
        });
        postcont.add(post);
        postcont.add(msgtopost);

        mother.add(postcont);

        return mother;
    }

    }
