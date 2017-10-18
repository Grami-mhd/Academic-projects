/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.House;
import Entities.User;
import GUI.StayGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
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
 * @author ROGUE_BIBO
 */
public class StayDAO {
    
     public void getstay() { 

        ConnectionRequest cr;
        cr = new ConnectionRequest() {
            
            List<House> stay = new ArrayList<>();

            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                        
                    stay.clear();
                    
                        for (Map<String, Object> obj : content) {
                            
                            User u=new User();
                            u.setFirstName((String) obj.get("prenom"));
                            u.setLastName((String) obj.get("nom"));
                            
                            House h=new House();
                            h.setOwner(u);
                            h.setDescription((String) obj.get("description"));
                            h.setRating(Integer.parseInt((String) obj.get("rating")));
                            h.setId(Integer.parseInt((String) obj.get("id")));
                            h.setPrice(Integer.parseInt((String) obj.get("price")));
                            h.setCountry((String) obj.get("country"));
                            h.setTown((String) obj.get("town"));
                       
                        stay.add(h);
                        
                    }
                } catch (IOException err) {
                }
            }

            @Override
            protected void postResponse() {

                Form lf = guesthouse.getF();
                lf.removeAll();
                lf.add("Your Stays :");
                lf.add(new StayGUI().showStay(stay));
                lf.repaint();
                

            }

        };
        
        cr.setUrl(guesthouse.localhost + "stay/show.php?id="+guesthouse.user.getId());

        NetworkManager.getInstance().addToQueue(cr);

    }
     
     
     public void addstay(int hid) { 

        ConnectionRequest cr;
        cr = new ConnectionRequest() ;
        
        cr.setUrl(guesthouse.localhost + "stay/addstay.php?hid="+hid+"&uid="+guesthouse.user.getId());

        NetworkManager.getInstance().addToQueue(cr);

    }
}
    

           
    

            
