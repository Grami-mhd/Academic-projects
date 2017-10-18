/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;

import entities.Forum;
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
public class BanDAO {
    private Connection connection;
    private PreparedStatement sta;
    
     public BanDAO() {
        connection=DataSource.getData().getConnection();
    }
     
     
    public void add(int userId,int forumId) {
        try {
            String rq="INSERT INTO `ban` (`fk_banneduser`, `fk_forum`) VALUES (?,?);";
            
            sta =connection.prepareStatement(rq);
            sta.setInt(1,userId);
            sta.setInt(2,forumId);
            
            
            sta.executeUpdate();
          //  System.out.println("don editing data base");
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public List<Forum> findByUserID(int userid) {
         String rq="SELECT * FROM `ban` WHERE `fk_banneduser` = ?";
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, userid);
            ForumDAO f=new ForumDAO();
            ResultSet result =sta.executeQuery();
            List<Forum> H = new ArrayList<>();
            while(result.next()){
               H.add(f.findById(result.getInt(2)));   
               
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<User> findByForumID(int forumid) {
         String rq="SELECT * FROM `ban` WHERE `fk_forumid` = ?";
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, forumid);
            UserDAO l=new UserDAO();
            ResultSet result =sta.executeQuery();
            List<User> H = new ArrayList<>();
            while(result.next()){
               H.add(l.findbyID(result.getInt(1)));                
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
