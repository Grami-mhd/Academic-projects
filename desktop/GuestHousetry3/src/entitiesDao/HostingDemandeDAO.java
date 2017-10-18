/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesDao;


import entities.HostingDemande;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author asus
 */
public class HostingDemandeDAO {
    private Connection connection;
    private PreparedStatement sta;
    
     public HostingDemandeDAO() {
        connection=DataSource.getData().getConnection();
    }

    
    public void add(HostingDemande m,int sid, int rid) {
        try {
            String rq="INSERT INTO `hostingdemande` (`text`, `fk_hosting_houseid`,`fk_senderid`, `fk_receiverid`) VALUES (?,?,?,?);";
            
            sta =connection.prepareStatement(rq);
            sta.setString(1,m.getText());
            sta.setInt(2,m.getHouse().getId());
            sta.setInt(3,sid);
            sta.setInt(4,rid);
            
            
            sta.executeUpdate();
            System.out.println("don editing data base");
        } catch (SQLException ex) {
            Logger.getLogger(HostingDemandeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    public void removeById(int id) {
       String rq= "DELETE FROM `hostingdemande` WHERE `hostingdemande`.`id` = ?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
            System.out.println("don deleting user :"+id);
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    public HostingDemande findById(int id) {
        HostingDemande h;
        String rq="select * FROM `hostingdemande` where id = ?";
        try {            
            sta =connection.prepareStatement(rq);
           sta.setInt(1, id);
            ResultSet result =sta.executeQuery();
            if(result.next()){
                h=new HostingDemande();
                h.setId(result.getInt(1));
                h.setText(result.getString(5));
                h.setHouse(new HouseDAO().findbyID(result.getInt(2)));
                return h;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HostingDemandeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    
    public List<HostingDemande> findAll() {
         List<HostingDemande> l=new ArrayList<>();
        HostingDemande h;
        String rq="select * FROM `hostingdemande`";
        try {            
            sta =connection.prepareStatement(rq);
           
            ResultSet result =sta.executeQuery();
            while(result.next()){
                h=new HostingDemande();
                h.setId(result.getInt(1));
                h.setText(result.getString(5));
                h.setHouse(new HouseDAO().findbyID(result.getInt(2)));
                l.add(h);
                
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(HostingDemandeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

 
    public Map<HostingDemande,User> findbyReceiverID(int receiverid) {
        String rq="SELECT * FROM `hostingdemande` WHERE `fk_receiverid` = ?";
        
        HostingDemande p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, receiverid);
            
            ResultSet result =sta.executeQuery();
            HashMap <HostingDemande,User> H = new HashMap<>();
            UserDAO hj=new UserDAO();
            while(result.next()){
                p=new HostingDemande(result.getInt(1),result.getString(5),new HouseDAO().findbyID(result.getInt(2)));
                H.put(p,hj.findbyID( result.getInt(4)));                     
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(HostingDemandeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     
}
