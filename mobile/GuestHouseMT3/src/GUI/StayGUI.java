/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.RateDAO;
import Entities.House;
import Entities.Rate;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ROGUE_BIBO
 */
public class StayGUI {

    public Container showStay(List<House> ls) {

        Container all = new Container(BoxLayout.y());

        Container housepic;

        Container house;

        Container star;

        Container affichage;

        // Container ratestay ;
        // Container starsbutton ;
        for (House c : ls) {
            

            housepic = new Container(BoxLayout.y());

            house = new Container(BoxLayout.y());

            star = new Container(BoxLayout.x());

            affichage = new Container(BoxLayout.x());

            //ratestay = new Container(BoxLayout.y());
            // starsbutton = new Container(BoxLayout.x());
            try {
                housepic.add(new ImageViewer(Image.createImage("/house.png").scaled(70, 70)));
            } catch (IOException ex) {
            }

            Label l = new Label("Owner : " + c.getOwner().getFirstName() + " " + c.getOwner().getLastName());
            l.getStyle().setFgColor(0x1153E1);

            Label l1 = new Label("Location :" + c.getCountry() + " " + c.getTown());
            l1.getStyle().setFgColor(0x1153E1);

            Label l2 = new Label("Price :" + c.getPrice());
            l2.getStyle().setFgColor(0x1153E1);

            SpanLabel d = new SpanLabel(c.getDescription());

            if (c.getRating() == 0) {

                try {
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            } else if (c.getRating() == 1) {
                try {
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            } else if (c.getRating() == 2) {
                try {
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            } else if (c.getRating() == 3) {
                try {
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            } else if (c.getRating() == 4) {
                try {
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/starblanc.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            } else if (c.getRating() == 5) {
                try {
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));
                    star.add(new ImageViewer(Image.createImage("/star.png").scaled(40, 40)));

                } catch (IOException ex) {
                }

            }

            Button b5 = new Button("Rate this Stay");
            b5.getAllStyles().setBorder(Border.createEmpty());
            b5.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);

            b5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    new RateDAO().rateStay(c);
                }
            });

            house.add(l);
            house.add(l1);
            house.add(l2);
            house.add(d);
            house.add(star);
            house.add(b5);

            affichage.add(housepic);
            affichage.add(house);

            /* Label l3=new Label("Welcome Back:");
         l3.getStyle().setFgColor(0xFF0000); 
         
         Label l4=new Label("Have you enjoy your last Stay :");
         
         Button b=new Button();
         Button b1=new Button();
         Button b2=new Button();
         Button b3=new Button();
         Button b4=new Button();
         
            try {
                b.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
            } catch (IOException ex) {
                
            }
            
            starsbutton.add(b);
            starsbutton.add(b1);
            starsbutton.add(b2);
            starsbutton.add(b3);
            starsbutton.add(b4);
            
             Label l5=new Label("Leave a note :");
             
             TextArea txt=new TextArea();
             
             Button b5=new Button("Valider");
             Button b6=new Button("Claim about this stay !!!");
             
             b6.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
             }
         });
             
            b6.getAllStyles().setBorder(Border.createEmpty());
            b6.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
             
            
         
         ratestay.add("_______________________________________________________________________");
         ratestay.add(l3);
         ratestay.add(l4);
         ratestay.add(starsbutton);
         ratestay.add(l5);
         ratestay.add(txt);
         ratestay.add(b5);
         ratestay.add(b6);
        
        all.add(ratestay);*/
            all.add("_______________________________________________________________________");
            all.add(affichage);

        }

        return all;
    }

}
