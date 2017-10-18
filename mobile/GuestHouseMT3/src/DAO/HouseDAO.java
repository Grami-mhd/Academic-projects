/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.House;
import Entities.User;
import GUI.HousesGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.guesthouse;
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
public class HouseDAO {

    public void getHouses() {
        ConnectionRequest connectionRequest;

        connectionRequest = new ConnectionRequest() {
            List<House> houses = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();

                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    houses.clear();
                    for (Map<String, Object> obj : content) {

                        House a = new House(Integer.parseInt((String) obj.get("id")), (String) obj.get("country"), (String) obj.get("town"), (String) obj.get("nature"), Integer.parseInt((String) obj.get("price")), (String) obj.get("description"), Integer.parseInt((String) obj.get("rating")));
                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("userid")));
                        u.setUserName((String) obj.get("userName"));
                        u.setEmail((String) obj.get("email"));
                        u.setCountry((String) obj.get("ucountry"));
                        u.setTown((String) obj.get("utown"));
                        u.setFirstName((String) obj.get("firstName"));
                        u.setLastName((String) obj.get("lastName"));
                        if (obj.get("phone") != null) {
                            u.setPhone(Integer.parseInt((String) obj.get("phone")));
                        }
                        if (obj.get("points") != null) {
                            u.setPoints(Integer.parseInt((String) obj.get("points")));
                        }
                        a.setOwner(u);
                        houses.add(a);

                    }
                } catch (IOException err) {

                }

            }

            @Override
            protected void postResponse() {
                guesthouse.getF().removeAll();
                guesthouse.getF().add(new HousesGUI().ViewHouses(houses));
                guesthouse.getF().repaint();

            }
        };

        connectionRequest.setUrl(guesthouse.localhost + "house/houses.php?id=" + guesthouse.user.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

    ;

    public void updateHouse(House h) {
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "house/updateHouse.php?id=" + h.getId() + "&price=" + h.getPrice() + "&description=" + h.getDescription()) {

            @Override
            protected void readResponse(InputStream input) throws IOException {
            }

            @Override
            protected void postResponse() {

            }

        };
        NetworkManager.getInstance().addToQueue(cnx);
    }

    public void getHouse(int id) {
        
    }
    public void addDate(String d,int id){
        
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "house/adddate.php?d=" +  d+ "&h="+ id);
        NetworkManager.getInstance().addToQueue(cnx);
    }
}
