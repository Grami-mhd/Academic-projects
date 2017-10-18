/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author grami
 */
public class DateDAO {
    private Connection connection;
    private PreparedStatement sta;

    public DateDAO() {
        connection=DataSource.getData().getConnection();
        
    }
    
    
    public void add(int id,Date d){
        try {
            String rq="INSERT INTO `date` (`date`,fk_addid) VALUES ( ?,?);";            
            sta =connection.prepareStatement(rq);
            sta.setDate(1, d);  
            sta.setInt(2, id);
            sta.executeUpdate();
          //  System.out.println("don editing data base");           
        } catch (SQLException ex) {
            Logger.getLogger(AddDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteDate(int id,Date d){
        try {
            String rq="DELETE FROM `date` WHERE `date`.`fk_addid` = ? and date = ?";            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);            
            sta.setDate(2, d);
            sta.executeUpdate();
           // System.out.println("don editing data base");           
        } catch (SQLException ex) {
            Logger.getLogger(AddDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public void deleteByAddId(int id){
        try {
            String rq="DELETE FROM `date` WHERE `date`.`fk_addid` = ? ";            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);            
            sta.executeUpdate();
           // System.out.println("don editing data base");           
        } catch (SQLException ex) {
            Logger.getLogger(AddDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateDate(int id,Date d,Date newdate){
        try {
            String rq="UPDATE `date` SET `date` = ? WHERE `date`.`fk_addid` = ? and date = ?;";            
            sta =connection.prepareStatement(rq);
            sta.setInt(2, id);            
            sta.setDate(3, d);
            sta.setDate(1, newdate);
            sta.executeUpdate();
          //  System.out.println("don editing data base");           
        } catch (SQLException ex) {
            Logger.getLogger(AddDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }       
    public TreeSet<Date> findByAddId(int addId) {
        try {
            String rq="SELECT date FROM `date` WHERE `fk_addid` = ?";
            
            
            TreeSet<Date> L = null;
            sta =connection.prepareStatement(rq);
            sta.setInt(1, addId);
            
            ResultSet result =sta.executeQuery();
            while(result.next()){
                if(L==null)
                    L=new TreeSet<>();
                L.add(result.getDate(1));
            }
            if(L!=null)
                return L;                
        
        } catch (SQLException ex) {
            Logger.getLogger(DateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return null;
        
        
    }
}
