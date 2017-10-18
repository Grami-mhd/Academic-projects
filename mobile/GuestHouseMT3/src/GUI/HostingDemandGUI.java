/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HostingDemandDAO;
import DAO.StayDAO;
import DAO.UserDAO;
import Entities.HostingDemande;
import Entities.House;
import Entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author grami
 */

public class HostingDemandGUI {

    public Container viewHostingDemends(HostingDemande hd) {

        Container demend = new Container(BoxLayout.y());
        Container Buttons = new Container(BoxLayout.x());
        Container infos = new Container(BoxLayout.x());
        Container txt = new Container(BoxLayout.y());
        demend.add("New Hosting Demend From :");

            infos.add(new ImageViewer(menus.getProfilePicture().scaled(100, 150)));
       
        txt.add(hd.getSender().getFirstName() + " " + hd.getSender().getLastName());
        txt.add(hd.getSender().getEmail());
        txt.add(hd.getSender().getPhone() + "");
        txt.add(hd.getSender().getCountry() + " " + hd.getSender().getTown());

        infos.add(txt);
        demend.add(infos);
        Label s = new Label(hd.getText());
        demend.add(s);

        Button viewHouse = new Button("House");
        viewHouse.addActionListener((ActionListener) (ActionEvent evt) -> {
            Form f = new Form();
            f.add("this is the deam house");
            menus.stBack(f);
            f.show();
        });
        Button accept = new Button("Accept");
        accept.addActionListener((ActionListener) (ActionEvent evt) -> {
            new HostingDemandDAO().accept(hd);
        });

        Button decline = new Button("Decline");
        decline.addActionListener((ActionListener) (ActionEvent evt) -> {
            new HostingDemandDAO().decline(hd);
        });

        Buttons.add(viewHouse);
        Buttons.add(accept);
        Buttons.add(decline);
        demend.add(Buttons);
        try {
            demend.getStyle().setBgImage(Image.createImage("/house.png"));
        } catch (IOException ex) {
        }
        return demend;
    }

    public Container setDates(List<String> l, House h) {
        Container mother = new Container(BoxLayout.y());
        Container all = new Container(BoxLayout.x());
        Container dates = new Container(BoxLayout.y());
        Container infos = new Container(BoxLayout.y());
        Button finish = new Button("validate");
        infos.add("you have " + guesthouse.user.getPoints() + "Points");
        Label price = new Label("Price = " + h.getPrice() + " /Day");
        price.getStyle().setFgColor(0xFF000);
        infos.add(price);
        SpanLabel txt = new SpanLabel("");
        infos.add(txt);

        for (String s : l) {
            DateButton b = new DateButton(s);

            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                b.setSelected(!b.getSelected());
                int sum = DateButton.count * h.getPrice();
                if (sum <= guesthouse.user.getPoints()) {
                    txt.setText("Total :" + sum + "DT");
                    if(!finish.isEnabled())
                        finish.setEnabled(true);
                } else {
                    txt.setText("not enough points" );
                    txt.getStyle().setFgColor(0X8A0829);
                    finish.setEnabled(false);
                    
                }
                infos.repaint();
            });
            dates.add(b);
        }
        all.add(dates);
        all.add(infos);
        mother.add(all);
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int sum = DateButton.count * h.getPrice();
                User u = h.getOwner();                
                new UserDAO().updatePoints((guesthouse.user.getPoints()-sum),(guesthouse.user.getId()));
                new UserDAO().updatePoints((u.getPoints()+sum),(u.getId()));
                System.out.println(DateButton.selectedDates);
                for (String s : DateButton.selectedDates) {
                    new HostingDemandDAO().deletedate(s,h.getId());
                }
                new StayDAO().addstay(h.getId());
                mother.removeAll();
                mother.add("done congratulations");
                mother.repaint();
                        
            }
        });
        mother.add(finish);
        return mother;
    }

}
