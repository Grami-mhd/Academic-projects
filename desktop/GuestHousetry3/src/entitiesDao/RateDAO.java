/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;

import entities.*;
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
 * @author grami
 */
public class RateDAO {
    private Connection connection;
    private PreparedStatement sta;
    
    

    public RateDAO() {
        connection=DataSource.getData().getConnection();
        setRates();
    }
    
    
   
    public void add(Rate t,int i,int s) {
        try {
            String req = "insert into rate(rate,fk_rate_houseid,fk_rate_ownerid)values (?,?,?)";
            
            sta= connection.prepareStatement(req);
            sta.setInt(1,t.getRate());
            sta.setInt(2,i);
            sta.setInt(3,s);
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        setRates();
    }

    
    
    public void update(Rate t) {
         try {
            String req = "update rate set rate=? where id=?";
            
            sta= connection.prepareStatement(req);
            sta.setInt(1, t.getRate());
            sta.setInt(2, t.getId());
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        setRates();
    }

    
    
    public void removeById(int id) {
        String rq= "DELETE FROM `rate` WHERE `rate`.`id` =?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
            System.out.println("don deleting rate :"+id);
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        setRates();
    }


    public List<Rate> findAll() {
        List<Rate> l=new ArrayList<>();
        Rate p;
        String rq="select * FROM `rate`";
        
        try {  
            sta =connection.prepareStatement(rq);
           
            ResultSet result =sta.executeQuery();
            while(result.next()){
                p=new Rate();
                p.setId(result.getInt(1));
                p.setRate(result.getInt(4));
                l.add(p);            
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
        
    public  Map< House,Rate> findbyOwnerID(int ownerid) {
        String rq="SELECT * FROM `rate` WHERE `fk_rate_ownerid` = ?";
        
        Rate p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            
            ResultSet result =sta.executeQuery();
             Map< House,Rate> H = new HashMap<>();
            HouseDAO hd=new HouseDAO();
            while(result.next()){
                p=new Rate(result.getInt(1),result.getInt(4));
                H.put(hd.findbyID( result.getInt(3)),p);                    
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Rate> findbyHouseID(int houseid) {
        String rq="SELECT * FROM `rate` WHERE `fk_rate_houseid` = ?";
        
        Rate p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, houseid);
            
            ResultSet result =sta.executeQuery();
            List<Rate> H = new ArrayList<>();
            while(result.next()){
                p=new Rate(result.getInt(1),result.getInt(4));
                H.add(p);
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setRates(){
        HouseDAO hd=new HouseDAO();
        List<House> lh=hd.findAll();
        for (House house : lh) {
            List<Rate> lr = findbyHouseID(house.getId());
            int x=0;
            for (Rate rate : lr) {
                x+=rate.getRate();
                
                        
            }
            house.setRating( Math.round((float)x/(float)lr.size()));
            hd.update(house);
        }
    }
}
