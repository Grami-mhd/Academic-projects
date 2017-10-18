/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.UserDAO;
import Entities.User;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.menus;
import java.io.IOException;

/**
 *
 * @author grami
 */
public class UserGUI {

    private static Form loginForm;
    private static Form signupForm;
    public static TextField susername;
    public static TextField spassword;
    public static TextField username;
    public static TextField password;
    public static TextField email;
    private static TextField cpass;
    private static TextField lastName;
    private static TextField firstName;

    public static void logIn() {
        Container cont = new Container(BoxLayout.y());
        username = new TextField(null, "username");
        password = new TextField("", "Password");
        password.setConstraint(TextField.PASSWORD);

        try {
            username.setHintIcon(menus.getProfilePicture().scaled(30, 30));
            password.setHintIcon(Image.createImage("/Password.png").scaled(30, 30));
        } catch (IOException ex) {
            System.out.println("pic not found");
        }
        Button login = new Button("login");

        login.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (username.getText().length() == 0) {
                    username.requestFocus();
                } else if (password.getText().length() == 0) {
                    password.requestFocus();
                } else {
                    new UserDAO().login(username.getText(), password.getText());
                }
            }
        });

        cont.add(username);
        cont.add(password);
        cont.add(login);
        Container c = new Container(BoxLayout.x());
        try {
            Button fb = new Button(Image.createImage("/facebook-logo.png").scaled(35, 35));
            
            fb.addActionListener(e -> {
                
            });
            c.add(fb);
            
            Button sign = new Button("signup here");
            sign.addActionListener(e -> {
                UserGUI.signup();
            });
            c.add(sign);
        } catch (IOException ex) {
            System.out.println("pic not found");
        }
        cont.add(c);
        loginForm = new Form();
        FlowLayout l = new FlowLayout(Component.CENTER, Component.CENTER);
        loginForm.setLayout(l);
        loginForm.add(cont);
        loginForm.showBack();

    }

    public static void signup() {
        signupForm = new Form();
        menus.stBack(signupForm);
        FlowLayout l = new FlowLayout(Component.CENTER, Component.CENTER);
        signupForm.setLayout(l);

        Container all = new Container(BoxLayout.y());

        Container form = new Container(BoxLayout.y());
        Container profilepic = new Container(BoxLayout.y());

        try {
            ImageViewer img = new ImageViewer(menus.getProfilePicture().scaled(200,250));
            profilepic.add(img);

            Button gallery = new Button("gallery", Image.createImage("/gallery.png").scaled(50, 50));

            gallery.addActionListener((ActionListener) (ActionEvent evt) -> {
                Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        Image img1;
                        try {
                            img1 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                            img.setImage(img1.scaled(200,250));
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
                        img.setImage(Image.createImage(path).scaled(200,250));
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

        form.add(profilepic);

        susername = new TextField(null, "username");
        spassword = new TextField("", "Password");

        cpass = new TextField();
        lastName = new TextField();
        firstName = new TextField();
        spassword.setConstraint(TextField.PASSWORD);

        cpass.setConstraint(TextField.PASSWORD);
        try {
            susername.setHintIcon(menus.getProfilePicture().scaled(30, 30));
            spassword.setHintIcon(Image.createImage("/Password.png").scaled(30, 30));
            cpass.setHintIcon(Image.createImage("/Password.png").scaled(30, 30));
        } catch (IOException ex) {
            System.out.println("pic not found");
        }
        cpass.setHint("Confirm password");
        form.add(susername);
        form.add(spassword);
        form.add(cpass);

        firstName.setHint("first name");
        form.add(firstName);

        lastName.setHint("last name");
        form.add(lastName);
        email = new TextField();
        email.setHint("email");
        email.setConstraint(TextField.EMAILADDR);
        form.add(email);

        all.add(form);
        Button submit = new Button("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (susername.getText().equals("")) {
                    susername.requestFocus();
                    return;
                }
                if (spassword.getText().equals("")) {
                    spassword.requestFocus();
                    return;
                }
                if (!spassword.getText().equals(cpass.getText())) {
                    cpass.requestFocus();
                    return;
                }
                if (firstName.getText().equals("")) {
                    firstName.requestFocus();
                    return;
                }
                if (lastName.getText().equals("")) {
                    lastName.requestFocus();
                    return;
                }
                if (email.getText().equals("")) {
                    email.requestFocus();
                    return;
                }
                User u = new User();
                u.setFirstName(firstName.getText());
                u.setLastName(lastName.getText());
                u.setEmail(email.getText());
                u.setUserName(susername.getText());
                new UserDAO().sheck(u);
            }
        });

        all.add(submit);

        signupForm.add(all);
        signupForm.show();
    }
}
