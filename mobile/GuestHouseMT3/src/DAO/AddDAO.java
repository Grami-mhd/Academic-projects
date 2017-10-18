/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Add;
import Entities.Comment;
import Entities.User;

import GUI.HomeGUI;
import GUI.ViewAddGUI;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author asus
 */
public class AddDAO {

  public void getAds(){
        ConnectionRequest connectionRequest;
        
        connectionRequest = new ConnectionRequest (){
            List<Add> ads = new ArrayList<>();
                @Override 
            protected void readResponse(InputStream in) throws IOException{
                 JSONParser json = new JSONParser();
                 
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    ads.clear();
                    for (Map<String, Object> obj : content) {
                 
                        Add a=new Add((String)obj.get("date"),Integer.parseInt((String) obj.get("houseid")),((String) obj.get("country")), ((String) obj.get("town")), ((String) obj.get("nature")),Integer.parseInt((String) obj.get("price")),((String) obj.get("description")),Integer.parseInt((String) obj.get("rating")));
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
                        ads.add(a);
                         
                    }
                }catch (IOException err) {
                    
                }
         
        }
           @Override
            protected void postResponse() {
                guesthouse.getF().removeAll();
                guesthouse.getF().add(new HomeGUI().ViewAdds(ads));
                guesthouse.getF().repaint();
                   
               }
            };
       
    
      connectionRequest.setUrl(guesthouse.localhost + "add/adds.php");  
      NetworkManager.getInstance().addToQueue(connectionRequest);
    
    };
    
    public void getAdsComments(int id, Form f){
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest (){
            
            List<Comment> lc = new ArrayList<>();
            @Override 
            protected void readResponse(InputStream in) throws IOException{
                 JSONParser json = new JSONParser();
                 
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    lc.clear();
                   
                    for (Map<String, Object> obj : content) {
  
                        User u = new User();
                        u.setId(Integer.parseInt((String) obj.get("ownerid")));
                        u.setFirstName((String) obj.get("firstName"));
                        u.setLastName((String) obj.get("lastName"));
                        
                        Comment c = new Comment();
                        c.setId(Integer.parseInt((String) obj.get("commentid")));
                        c.setHouseid(Integer.parseInt((String) obj.get("house")));
                        c.setComment((String) obj.get("comment"));
                        c.setOwner(u);
                    
                        lc.add(c);
                    }
                }catch (IOException err) {
                    
                }
            }
                 @Override
            protected void postResponse() {
               
                f.add(new ViewAddGUI().GetComments(lc));
                f.repaint();
               }
  
        };
      connectionRequest.setUrl(guesthouse.localhost + "add/comment_add.php?houseid="+id);  
      NetworkManager.getInstance().addToQueue(connectionRequest);

    }
    
    public void getdates(Add  h,Form f){
        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "date/findbyhouse.php?id=" + h.getId()) {
            
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
                for (String date : dates) {
                    f.add(new ViewAddGUI().dates(dates,h));
                }
                f.repaint();
            }
        };
        NetworkManager.getInstance().addToQueue(cnx);
        
    }

    public void searchAddByTown() {
        ConnectionRequest connectionRequest;

        connectionRequest = new ConnectionRequest() {
            Set<Add> ads = new HashSet<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();

                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    ads.clear();
                    for (Map<String, Object> obj : content) {

                        Add a = new Add((String) obj.get("date"), Integer.parseInt((String) obj.get("houseid")), ((String) obj.get("country")), ((String) obj.get("town")), ((String) obj.get("nature")), Integer.parseInt((String) obj.get("price")));
                        ads.add(a);

                    }
                } catch (IOException err) {

                }

            }

            @Override
            protected void postResponse() {
                guesthouse.getF().removeAll();
                guesthouse.getF().add(new HomeGUI().searchByTown());
                guesthouse.getF().add(new HomeGUI().ViewAddsSearched(ads));
                guesthouse.getF().repaint();

            }
        };

        connectionRequest.setUrl(guesthouse.localhost + "add/searchAddsByTown.php?key=" + HomeGUI.key);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void searchAddByNature() {
        ConnectionRequest connectionRequest;

        connectionRequest = new ConnectionRequest() {
            Set<Add> ads = new HashSet<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();

                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    ads.clear();
                    for (Map<String, Object> obj : content) {

                        Add a = new Add((String) obj.get("date"), Integer.parseInt((String) obj.get("houseid")), ((String) obj.get("country")), ((String) obj.get("town")), ((String) obj.get("nature")), Integer.parseInt((String) obj.get("price")));
                        ads.add(a);

                    }
                } catch (IOException err) {

                }

            }

            @Override
            protected void postResponse() {
                guesthouse.getF().removeAll();
                guesthouse.getF().add(new HomeGUI().searchByNature());
                guesthouse.getF().add(new HomeGUI().ViewAddsSearched(ads));
                guesthouse.getF().repaint();

            }
        };

        connectionRequest.setUrl(guesthouse.localhost + "add/searchAddsByNature.php?key=" + HomeGUI.key);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void searchAddByPrice() {
        ConnectionRequest connectionRequest;

        connectionRequest = new ConnectionRequest() {
            Set<Add> ads = new HashSet<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();

                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);

                    List<Map<String, Object>> content
                            = (List<Map<String, Object>>) data.get("root");
                    ads.clear();
                    for (Map<String, Object> obj : content) {

                        Add a = new Add((String) obj.get("date"), Integer.parseInt((String) obj.get("houseid")), ((String) obj.get("country")), ((String) obj.get("town")), ((String) obj.get("nature")), Integer.parseInt((String) obj.get("price")));
                        ads.add(a);

                    }
                } catch (IOException err) {

                }

            }

            @Override
            protected void postResponse() {
                guesthouse.getF().removeAll();
                guesthouse.getF().add(new HomeGUI().searchByPrice());
                guesthouse.getF().add(new HomeGUI().ViewAddsSearched(ads));
                guesthouse.getF().repaint();

            }
        };

        connectionRequest.setUrl(guesthouse.localhost + "add/searchAddsByPrice.php?key=" + HomeGUI.key);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}
