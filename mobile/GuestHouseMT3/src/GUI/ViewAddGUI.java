/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HostingDemandDAO;
import DAO.NotificationDAO;
import Entities.Add;
import Entities.Comment;
import Entities.HostingDemande;
import Entities.House;
import Entities.Notification;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author dell
 */
public class ViewAddGUI {

    public Container ViewAdd(Add a) {
        Container mother = new Container(BoxLayout.y());
        Container img = new Container(BoxLayout.x());
        Form fo = new Form(BoxLayout.y());
        Container info = new Container(BoxLayout.y());
        Label im1 = new Label();
        Label im2 = new Label();
        Label im3 = new Label();
        try {
            im1.setIcon(Image.createImage("/house.png").scaled(90, 90));
            im2.setIcon(Image.createImage("/house.png").scaled(90, 90));
            im3.setIcon(Image.createImage("/house.png").scaled(90, 90));

        } catch (IOException ex) {
            //Logger.getLogger(ViewAddGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        img.add(im1);
        img.add(im2);
        img.add(im3);
        mother.add(img);

        //info.add(new Label("Owner : "+a.getOwner().getFirstName()+", "+a.getOwner().getLastName()));
        //info.add(new Label("country : "+a.getCountry()));
        //info.add(new Label("town : "+a.getTown()));
        //info.add(new Label("Nature : "+a.getNature()));
        //info.add(new Label("Price : "+a.getPrice()));
        //info.add(new Label("Description : "+a.getDescription()));
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        Container ow = new Container(BoxLayout.x());
        Label o = new Label("Owner:        ");
        o.getStyle().setFgColor(0X08088A);
        Label owner = new Label();
        owner.setText(a.getOwner().getFirstName() + ", " + a.getOwner().getLastName());
        ow.add(o);
        ow.add(owner);
        fo.add(ow);

        Container to = new Container(BoxLayout.x());
        Label t = new Label("Town:          ");
        t.getStyle().setFgColor(0X08088A);
        Label town = new Label();
        town.setText(a.getTown());
        to.add(t);
        to.add(town);
        fo.add(to);

        Container co = new Container(BoxLayout.x());
        Label c = new Label("Country:      ");
        c.getStyle().setFgColor(0X08088A);
        Label country = new Label();
        country.setText(a.getCountry());
        co.add(c);
        co.add(country);
        fo.add(co);

        Container nu = new Container(BoxLayout.x());
        Label n = new Label("Nature:       ");
        n.getStyle().setFgColor(0X08088A);
        Label nature = new Label();
        nature.setText(a.getNature());
        nu.add(n);
        nu.add(nature);
        fo.add(nu);

        Container pr = new Container(BoxLayout.x());
        Label r = new Label("Price:          ");
        r.getStyle().setFgColor(0X08088A);
        Label price = new Label();
        price.setText(a.getPrice() + "");
        pr.add(r);
        pr.add(price);
        fo.add(pr);

        info.add(fo);

        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        Container star = new Container(BoxLayout.x());
        if (a.getRating() == 0) {
            try {
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        } else if (a.getRating() == 1) {
            try {
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        } else if (a.getRating() == 2) {
            try {
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        } else if (a.getRating() == 3) {
            try {
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        } else if (a.getRating() == 4) {
            try {
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        } else if (a.getRating() == 5) {
            try {
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));

            } catch (IOException ex) {
            }
        }
        //info.add(new Label("Rating : "+a.getRating()));
        info.add(star);
        Label comments = new Label("Dates : ");
        comments.getStyle().setFgColor(0X08088A);
        info.add(comments);
        mother.add(info);

        return mother;
    }

    public Container GetComments(List<Comment> lc) {
        Container comments = new Container(BoxLayout.y());
        for (Comment c : lc) {
            Container comment = new Container(BoxLayout.y());
            Label user = new Label(c.getOwner().getFirstName() + " " + c.getOwner().getLastName());
            user.getStyle().setFgColor(0XFF0000);
            SpanLabel text = new SpanLabel(c.getComment());
            comment.add(user);
            comment.add(text);
            comment.add("_____________________________________________");
            comments.add(comment);
        }
        return comments;
    }

    public Container dates(List<String> l, House h) {
        Container btns = new Container(BoxLayout.x());
        Container mother = new Container(BoxLayout.y());
        for (String d : l) {
            mother.add(d);
        }
        Button hd = new Button("Rent");
        Button n = new Button("message");
        TextArea t = new TextArea(3, 35);

        t.setHint("Type your message here");

        mother.add(t);
        mother.add(btns);
        btns.add(n);
        btns.add(hd);
        n.addActionListener((ActionListener) (ActionEvent evt) -> {

            if (guesthouse.user != null) {
                Notification nt = new Notification();
                nt.setText(guesthouse.user.getFirstName() + " " + guesthouse.user.getLastName() + " :" + t.getText());
                new NotificationDAO().notify(nt, h.getOwner().getId());
            } else if (Dialog.show("Connect", "do you want to login", "Yes", "Not Now")) {
                UserGUI.logIn();
            }
        });

        hd.addActionListener((ActionListener) (ActionEvent evt) -> {

            if (guesthouse.user != null) {
                HostingDemande hdd = new HostingDemande();
                hdd.setSender(guesthouse.user);
                hdd.setHouseid(h.getId());
                hdd.setText(guesthouse.user.getFirstName() + " " + guesthouse.user.getLastName() + " :" + t.getText());
                new HostingDemandDAO().addHostingDemand(hdd, h.getOwner().getId());
            } else if (Dialog.show("Connect", "do you want to login", "Yes", "Not Now")) {
                UserGUI.logIn();
            }
        });

        return mother;
    }
}
