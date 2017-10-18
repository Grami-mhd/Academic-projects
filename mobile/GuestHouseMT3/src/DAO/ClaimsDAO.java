/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Claim;
import Entities.House;
import GUI.ClaimsGUI;
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
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ROGUE_BIBO
 */
public class ClaimsDAO {
    
    public void getClaims() { 

        ConnectionRequest cr;
        cr = new ConnectionRequest() {
            
            List<Claim> claims = new ArrayList<>();

            protected void readResponse(InputStream input) throws IOException {
                JSONParser js = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String, Object> data = js.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                        
                    claims.clear();
                    
                        for (Map<String, Object> obj : content) {
                            
                            House h=new House();
                            h.setCountry((String) obj.get("country"));
                            h.setTown((String) obj.get("town"));

                        Claim c=new Claim();
                        c.setHouse(h);
                        c.setDate((String) obj.get("date"));
                        c.setText((String) obj.get("text"));
                       
                        claims.add(c);
                        
                    }
                } catch (IOException err) {
                }
            }

            @Override
            protected void postResponse() {

                Form lf = guesthouse.getF();
                lf.removeAll();
                lf.add("History of Claims");
                lf.add(new ClaimsGUI().showClaims(claims));
                lf.repaint();
                

            }

        };
        
        cr.setUrl(guesthouse.localhost + "claims/show.php?id="+guesthouse.user.getId());

        NetworkManager.getInstance().addToQueue(cr);

    }
    
    public void addClaim(int c,String txt){
        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost+"claims/addclaim.php?claimownerid="+guesthouse.user.getId()+"&claimhouseid="+c+"&text="+txt);
        
        NetworkManager.getInstance().addToQueue(cr);
        
    }
}
