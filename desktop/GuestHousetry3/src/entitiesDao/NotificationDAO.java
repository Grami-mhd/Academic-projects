/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;

import entities.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author grami
 */
public class NotificationDAO {
    
    private Connection connection;
    private PreparedStatement sta;
    
    public NotificationDAO() {
        connection=DataSource.getData().getConnection();
    }
 
    public void add(Notification n,int oid) {
        try {
            String rq="INSERT INTO `notification` (`text`, `fk_notif_userid`, `date`,type) VALUES (?,?,?,?);";
            
            sta =connection.prepareStatement(rq);
            sta.setString(1,n.getText());
            sta.setInt(2,oid);
            sta.setDate(3,n.getDate());
            sta.setString(4,n.getType());
            
            sta.executeUpdate();
            System.out.println("don adding notification");
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
   
    public void setSeen(Notification n){
        
        String rq="UPDATE `notification` SET `isseen`=1 WHERE `notification`.`id`=?";
        
        try {
            sta=connection.prepareStatement(rq);
            sta.setInt(1,n.getId());
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public List<Notification> findbyUserID(int userid) {
        String rq="SELECT * FROM `notification` WHERE `fk_notif_userid` = ?";
        
        Notification p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, userid);
            
            ResultSet result =sta.executeQuery();
            List <Notification> H = new ArrayList<>();
            
              while(result.next()){
                p=new Notification();
                p.setId(result.getInt(1));
                p.setText(result.getString(3));
                p.setDate(result.getDate(4));
                p.setIsSeen(result.getBoolean(5));    
                p.setType(result.getString(6));
                H.add(p);
            }
            return H;               
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
