/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HouseDAO;
import Entities.House;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;

/**
 *
 * @author dell
 */
public class ViewHouseGUI {

    public Container ViewHouse(House a) {
        Container mother = new Container(BoxLayout.y());
        Container img = new Container(BoxLayout.x());
        Container info = new Container(BoxLayout.y());
        Label im1 = new Label();
        Label im2 = new Label();
        Label im3 = new Label();
        try {
            im1.setIcon(Image.createImage("/house.png").scaled(50, 50));
            im2.setIcon(Image.createImage("/house.png").scaled(50, 50));
            im3.setIcon(Image.createImage("/house.png").scaled(50, 50));

        } catch (IOException ex) {
            //Logger.getLogger(ViewAddGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        img.add(im1);
        img.add(im2);
        img.add(im3);
        mother.add(img);
        info.add(new Label("country : " + a.getCountry()));
        info.add(new Label("town : " + a.getTown()));
        info.add(new Label("Nature : " + a.getNature()));
        //info.add(new Label("Price : "+a.getPrice()));
        TextField price = new TextField();
        TextField desc = new TextField();
        price.setText("" + a.getPrice());
        info.add(price);
        desc.setText(a.getDescription());
        info.add(desc);
        info.add(new Label("Rating : " + a.getRating()));

        Button b = new Button("Update");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                a.setPrice(Integer.parseInt(price.getText()));
                a.setDescription(desc.getText());
                guesthouse.getF().removeAll();
                new HouseDAO().updateHouse(a);
                Dialog.show("Update", "success", "ok", null);
                guesthouse.getF().add(ViewHouse(a));
                guesthouse.getF().repaint();
            }
        });

        info.add(b);
        mother.add(info);

        Picker p = new Picker();
        p.addActionListener((ActionListener) (ActionEvent evt) -> {
            new HouseDAO().addDate(p.getDate().toString(), a.getId());
            guesthouse.getF().removeAll();
            guesthouse.getF().add(ViewHouse(a));
            new HousesGUI().getdates(a.getId());
            guesthouse.getF().repaint();

        });
        mother.add(p);
        return mother;
    }

}
