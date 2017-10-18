/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.UserDAO;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class ProfileGUI {

    public Container showProfile() {
        Container ttt =new Container();
        Label susername;
        Label email;
        Label phone;
        Label country;
        Label town;
        Label lastName;
        Label firstName;
        Label ppp;
        
        
        Container all = new Container(BoxLayout.y());

        Container form = new Container(BoxLayout.y());
        Container profilepic = new Container(BoxLayout.y());

        try {
            ImageViewer img = new ImageViewer(menus.getProfilePicture().scaled(250, 250));
            profilepic.add(img);

            Button gallery = new Button("gallery", Image.createImage("/gallery.png").scaled(50, 50));

            gallery.addActionListener((ActionListener) (ActionEvent evt) -> {
                Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        Image img1;
                        try {
                            img1 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                            img.setImage(img1.scaled(200, 250));
                        } catch (IOException ex) {
                            System.out.println("gallery pic not found");
                        }

                    }
                }, Display.GALLERY_IMAGE);
            });

            if (Capture.hasCamera()) {
                Container c = new Container(BoxLayout.x());
                c.add(gallery);
                Button take = new Button("camera", Image.createImage("/camera.png").scaled(50, 50));
                take.addActionListener((ActionListener) (ActionEvent evt) -> {
                    String path = Capture.capturePhoto();
                    try {
                        img.setImage(Image.createImage(path).scaled(200, 250));
                        System.out.println(path);
                    } catch (IOException ex) {
                        System.out.println("path from capture not found");
                    }
                });
                c.add(take);
                profilepic.add(c);
            } else {
                profilepic.add(gallery);
            }

        } catch (IOException ex) {
            System.out.println("profile pic not found");
        }

        all.add(profilepic);

        
        ppp = new Label();
        ppp.setText("Profile");
        form.add(ppp);

        Container us=new Container(BoxLayout.x());
        Label u=new Label("Username:  ");
        u.getStyle().setFgColor(0X08088A);
        susername = new Label();
        susername.setText(guesthouse.user.getUserName());
        us.add(u);
        us.add(susername);
        form.add(us);

        Container fn=new Container(BoxLayout.x());
        Label n=new Label("first name:  ");
        n.getStyle().setFgColor(0X08088A);
        firstName = new Label();
        firstName.setText(guesthouse.user.getFirstName());
        fn.add(n);
        fn.add(firstName);
        form.add(fn);

        Container ln=new Container(BoxLayout.x());
        Label l=new Label("last name:  ");
        l.getStyle().setFgColor(0X08088A);
        lastName = new Label();
        lastName.setText(guesthouse.user.getLastName());
        ln.add(l);
        ln.add(lastName);
        form.add(ln);

        Container em=new Container(BoxLayout.x());
        Label e=new Label("Email:      ");
        e.getStyle().setFgColor(0X08088A);
        email = new Label();
        email.setText(guesthouse.user.getEmail());
        em.add(e);
        em.add(email);
        form.add(em);

        Container ph=new Container(BoxLayout.x());
        Label p=new Label("Phone:      ");
        p.getStyle().setFgColor(0X08088A);
        phone = new Label();
        phone.setText(guesthouse.user.getPhone() + "");
        ph.add(p);
        ph.add(phone);
        form.add(ph);
                       
        Container co=new Container(BoxLayout.x());
        Label c=new Label("Country:    ");
        c.getStyle().setFgColor(0X08088A);
        country=new Label();
        country.setText(guesthouse.user.getCountry());
        co.add(c);
        co.add(country);
        form.add(co);
        
        /*town=new Label();
        town.setText("Town : "+guesthouse.user.getTown());
        form.add(town);*/
        
        Container to=new Container(BoxLayout.x());
        Label t=new Label("Town:       ");
        t.getStyle().setFgColor(0X08088A);
        town=new Label();
        town.setText(guesthouse.user.getTown());
        to.add(t);
        to.add(town);
        form.add(to);

        ttt.add(form);
        
        all.add(ttt);
        form.addPointerReleasedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            ttt.replaceAndWait(ttt.getComponentAt(0), updateProfile(),CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 1500));}
        });
        return all;
    }

    public Container updateProfile() {

        Label susername;
        Label email;
        TextField phone;
        Label country;
        TextField town;
        TextField lastName;
        TextField firstName;

        Container form = new Container(BoxLayout.y());
        
        Container us=new Container(BoxLayout.x());
        Label u=new Label("Username:");
        u.getStyle().setFgColor(0X08088A);
        susername = new Label();
        susername.setText(guesthouse.user.getUserName());
        us.add(u);
        us.add(susername);
        form.add(us);
        
        Container fn=new Container(BoxLayout.x());
        Label n=new Label("first name:");
        n.getStyle().setFgColor(0X08088A);
        firstName = new TextField();
        firstName.setHint("first name");
        firstName.setText(guesthouse.user.getFirstName());
        fn.add(n);
        fn.add(firstName);
        form.add(fn);
        
        Container ln=new Container(BoxLayout.x());
        Label h=new Label("last name: ");
        h.getStyle().setFgColor(0X08088A);
        lastName = new TextField();
        lastName.setHint("last name");
        lastName.setText(guesthouse.user.getLastName());
        ln.add(h);
        ln.add(lastName);
        form.add(ln);

        Container em=new Container(BoxLayout.x());
        Label e=new Label("Email:");
        e.getStyle().setFgColor(0X08088A);
        email = new Label();
        email.setText(guesthouse.user.getEmail());
        em.add(e);
        em.add(email);
        form.add(em);
        
        
        Container ph=new Container(BoxLayout.x());
        Label p=new Label("phone:      ");
        p.getStyle().setFgColor(0X08088A);
        phone = new TextField();
        phone.setHint("phone number");
        phone.setConstraint(TextField.PHONENUMBER);
        phone.setText(+guesthouse.user.getPhone() + "");
        ph.add(p);
        ph.add(phone);
        form.add(ph);
        
        Container co=new Container(BoxLayout.x());
        Label c=new Label("Email:");
        c.getStyle().setFgColor(0X08088A);
        country=new Label();
        country.setText("Country:  "+guesthouse.user.getCountry());
        co.add(c);
        co.add(country);
        form.add(co);
        
        Container to=new Container(BoxLayout.x());
        Label t=new Label("Town:        ");
        t.getStyle().setFgColor(0X08088A);
        town= new TextField();
        town.setHint("town");
        town.setText(guesthouse.user.getTown());
        to.add(t);
        to.add(town);
        form.add(to);
        
        
        
        Button update = new Button("update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 

                guesthouse.user.setFirstName(firstName.getText());
                guesthouse.user.setLastName(lastName.getText());
                guesthouse.user.setPhone(Integer.parseInt(phone.getText()));
                guesthouse.user.setCountry(country.getText());
                guesthouse.user.setTown(town.getText());
                guesthouse.getF().removeAll();
                    if (Dialog.show("UpDateProfile :", "Are you sure you want to update your profile", "Confirm", "cancel")) {
                        new UserDAO().updateProfile();
                        guesthouse.getF().add(new ProfileGUI().showProfile());
                        guesthouse.getF().repaint();
                        Message m = new Message("your profile has been updated");
                        //m.getAttachments().put   ("", "text/plain");
                        Display.getInstance().sendMessage(new String[] {/*guesthouse.user.getEmail()*/"sou.belguith@gmail.com"}, "Subject of message", m);  
                }

            }
        });

        form.add(update);
        return form;

    }
}
