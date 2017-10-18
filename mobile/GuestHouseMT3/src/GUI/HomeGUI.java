/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.AddDAO;
import Entities.Add;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asus
 */
public class HomeGUI {

    public static String key;

    public Container ViewAdds(List<Add> L) {
        Container Mom = new Container(BoxLayout.y());
        Container at = new Container();
        //***********************************************
        Button Stown = new Button("Search By Town");
        Stown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().searchAddByTown();
            }
        });
        Mom.add(Stown);
        //***********************************************
        Button Snature = new Button("Search By Nature");
        Snature.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().searchAddByNature();
            }
        });
        Mom.add(Snature);
        //***********************************************
        Button Sprice = new Button("Search By Price");
        Sprice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().searchAddByPrice();
            }
        });
        Mom.add(Sprice);
        //***********************************************
        for (Add a : L) {
            Container add = new Container(BoxLayout.x());

            Button b = new Button();
            try {
                b.setIcon(Image.createImage("/house.png").scaled(50, 50));
            } catch (IOException ex) {

            }
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("ffff");
                    Form f = new Form();
                    if (guesthouse.user != null) {
                        menus.setmenu(f);
                    } else {
                        menus.addConnect(f);
                    }
                    menus.stBack(f);
                    
                   f.add(new ViewAddGUI().ViewAdd(a));
                   new AddDAO().getdates(a, f);
                   new AddDAO().getAdsComments(a.getId(),f);
                   
                   f.repaint();
                f.show();
                }
            });
            add.add(b);

            Container info = new Container(BoxLayout.y());
            Label l = new Label("Our GuesHouse");
            l.getStyle().setFgColor(0X08088A);
            info.add(l);
            info.add(new Label("Country: " + (a.getCountry()) + "  Town: " + (a.getTown())));
            info.add(new Label("Nature: " + a.getNature()));
            info.add("Prix: " + a.getPrice() + "");
            add.setLeadComponent(b);
            add.add(info);
            Label k = new Label("_______________________________________");
            k.getStyle().setFgColor(0X8A0829);

            Mom.add(k);
            Mom.add(add);

        }

        return Mom;

    }

    public Container searchByTown() {

        //---------------------------------------
        Container sea = new Container();
        Form hi;
        AutoCompleteTextField ac;

        hi = new Form("Search By Town", new BoxLayout(BoxLayout.Y_AXIS));
        Container ba = new Container(BoxLayout.x());
        Button back = new Button();
        try {
            back.setIcon(Image.createImage("/backI.png").scaled(50, 50));
        } catch (IOException ex) {

        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().getAds();
            }
        });
        ac = new AutoCompleteTextField("tunis", "sfax", "kef", "mounastir", "Djerba", "ariana", "beja", "ben arous", "bizerte",
                "gabès", "gafsa", "jendouba", "kairouan", "kasserine", "kebili", "mahdia", "manouba", "médenine", "nabeul",
                "sidi bouzid", "siliana", "sousse", "tataouine", "tozeur", "zaghouan");
        ac.addActionListener((ActionListener) (ActionEvent evt) -> {
            key = ac.getText();
            System.out.println(key);
            new AddDAO().searchAddByTown();
            //search.actionPerformed(evt);

        });
        ac.setMinimumElementsShownInPopup(5);
        ba.add(back);
        ba.add(ac);
        hi.add(ba);
        sea.add(hi);

        //---------------------------------------
        return sea;
    }

    public Container searchByNature() {

        //---------------------------------------
        Container sea = new Container();
        Form hi;
        AutoCompleteTextField ac;

        hi = new Form("Search By Nature", new BoxLayout(BoxLayout.Y_AXIS));
        Container ba = new Container(BoxLayout.x());
        Button back = new Button();
        try {
            back.setIcon(Image.createImage("/backI.png").scaled(50, 50));
        } catch (IOException ex) {

        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().getAds();
            }
        });
        ac = new AutoCompleteTextField("room", "house", "appart");
        ac.addActionListener((ActionListener) (ActionEvent evt) -> {
            key = ac.getText();
            System.out.println(key);
            new AddDAO().searchAddByNature();
            //search.actionPerformed(evt);

        });
        ac.setMinimumElementsShownInPopup(5);
        ba.add(back);
        ba.add(ac);
        hi.add(ba);
        sea.add(hi);

        //---------------------------------------
        return sea;
    }

    public Container searchByPrice() {

        //---------------------------------------
        Container sea = new Container();
        Form hi;
        TextField ac;

        hi = new Form("Search By Price", new BoxLayout(BoxLayout.Y_AXIS));
        Container ba = new Container(BoxLayout.x());
        Button back = new Button();
        try {
            back.setIcon(Image.createImage("/backI.png").scaled(50, 50));
        } catch (IOException ex) {

        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddDAO().getAds();
            }
        });
        ac = new TextField();
        ac.setConstraint(TextField.NUMERIC);
        ac.setHint("Enter your maximun price");
        ac.addActionListener((ActionListener) (ActionEvent evt) -> {
            key = ac.getText();
            System.out.println(key);
            new AddDAO().searchAddByPrice();

        });
        ba.add(back);
        ba.add(ac);
        hi.add(ba);
        sea.add(hi);

        //---------------------------------------
        return sea;
    }

    public Container ViewAddsSearched(Set<Add> L) {
        Container Mom = new Container(BoxLayout.y());
        Container at = new Container();

        for (Add a : L) {
            Container add = new Container(BoxLayout.x());

            Button b = new Button();
            try {
                b.setIcon(Image.createImage("/house.png").scaled(50, 50));
            } catch (IOException ex) {

            }
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("ffff");
                }
            });
            add.add(b);

            Container info = new Container(BoxLayout.y());
            Label l = new Label("Our GuesHouse");
            l.getStyle().setFgColor(0X08088A);
            info.add(l);
            info.add(new Label("Country: " + (a.getCountry()) + "  Town: " + (a.getTown())));
            info.add(new Label("Nature: " + a.getNature()));
            info.add("Prix: " + a.getPrice() + "");
            add.setLeadComponent(b);
            add.add(info);
            Label k = new Label("_______________________________________");
            k.getStyle().setFgColor(0X8A0829);

            Mom.add(k);
            Mom.add(add);

        }

        return Mom;

    }

}
