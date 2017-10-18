/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.House;
import GUI.RateStayGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ROGUE_BIBO
 */
public class RateDAO {

    public void rateStay(House h) {
        ConnectionRequest cr;
        cr = new ConnectionRequest() {

            int r = 0;
            boolean is = false;

            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");

                    for (Map<String, Object> obj : content) {

                        if (Integer.parseInt((String) obj.get("fk_rate_houseid")) == h.getId()) {
                            r = Integer.parseInt((String) obj.get("rate"));
                            is=true;
                        }

                    }
                } catch (IOException err) {
                }
            }

            @Override
            protected void postResponse() {

                Form f = guesthouse.getF();
                f.removeAll();
                f.add(new RateStayGUI().RateStay(h, r, is));
                f.repaint();

            }

        };

        cr.setUrl(guesthouse.localhost + "stay/findallRate.php?id=" + guesthouse.user.getId());

        NetworkManager.getInstance().addToQueue(cr);
    }
    
     public void addRate(int r,House h){
        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost+"stay/addrate.php?rate="+r+"&ratehouseid="+h.getId()+"&rateownerid="+guesthouse.user.getId());
        NetworkManager.getInstance().addToQueue(cr);
         calcRate(h);
     }
     
     public void updateRate(int r,House h) { 

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost +"stay/UpdateRate.php?rate="+r+"&ratehouseid="+h.getId()+"&rateownerid="+guesthouse.user.getId());
       
        NetworkManager.getInstance().addToQueue(cnx);
        
         calcRate(h);
    }
     
     private void calcRate(House h){
        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost+"stay/calc.php?h="+h.getId());
        NetworkManager.getInstance().addToQueue(cr);
     }
}
