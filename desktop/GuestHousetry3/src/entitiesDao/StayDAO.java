/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;


import entities.House;
import entities.User;
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
public class StayDAO{
        private Connection connection;
    private PreparedStatement sta;
    
     public StayDAO() {
        connection=DataSource.getData().getConnection();
    }
     
     
    public void add(int houseId,int userId) {
        try {
            String rq="INSERT INTO `stay` (`fk_stay_houseid`, `fk_stay_userid`) VALUES (?,?);";
            
            sta =connection.prepareStatement(rq);
            sta.setInt(1,houseId);
            sta.setInt(2,userId);
            
            
            sta.executeUpdate();
           // System.out.println("don editing data base");
        } catch (SQLException ex) {
            Logger.getLogger(StayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
   
    public List<House> findByUserID(int userid) {
         String rq="SELECT * FROM `stay` WHERE `fk_stay_userid` = ?";
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, userid);
            HouseDAO l=new HouseDAO();
            ResultSet result =sta.executeQuery();
            List<House> H = new ArrayList<>();
            while(result.next()){
               H.add(l.findbyID(result.getInt(1)));                
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(StayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<User> findByHouseID(int houseid) {
         String rq="SELECT * FROM `stay` WHERE `fk_stay_houseid` = ?";
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, houseid);
            UserDAO l=new UserDAO();
            ResultSet result =sta.executeQuery();
            List<User> H = new ArrayList<>();
            while(result.next()){
               H.add(l.findbyID(result.getInt(2)));                
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(StayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
