/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Add;
import Entities.House;
import com.codename1.charts.views.PieChart;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.guesthouse;
import com.mycompany.myapp.menus;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class HousesGUI {

    public Container ViewHouses(List<House> L) {
        Container Mom = new Container(BoxLayout.y());
        for (House a : L) {
            Container house = new Container(BoxLayout.x());

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
                    menus.setmenu(f);

                    guesthouse.getF().removeAll();
                    guesthouse.getF().add(new ViewHouseGUI().ViewHouse(a));
                    getdates(a.getId());
                    guesthouse.getF().repaint();
                }
            });
            house.add(b);
            Container info = new Container(BoxLayout.y());
            info.add(new Label(a.getCountry()));
            info.add(new Label(a.getTown()));
            info.add(new Label(a.getNature()));
            info.add(a.getPrice() + "");
            house.setLeadComponent(b);
            house.add(info);
            Mom.add(house);

        }

        return Mom;

    }

    public void getdates(int h) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "date/findbyhouse.php?id=" + h) {

            List<String> dates = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {
                        dates.add((String) obj.get("date"));
                    }
                } catch (IOException err) {
                    System.out.println("not found in hosting demand");
                }

            }

            @Override
            protected void postResponse() {
                Container c = new Container(BoxLayout.y());

                for (String date : dates) {
                    c.add(date);
                }
                guesthouse.getF().add(c);
                guesthouse.getF().repaint();
            }
        };
        NetworkManager.getInstance().addToQueue(cnx);

    }

}
