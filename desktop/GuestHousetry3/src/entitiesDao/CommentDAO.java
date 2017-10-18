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
public class CommentDAO  {
    private Connection connection;
    private PreparedStatement sta;
    
    

    public CommentDAO() {
        connection=DataSource.getData().getConnection();
    }
    
   
    public void add(Comment t,int i,int s) {
        try {
            String req = "insert into comment(comment,fk_comment_houseid,fk_comment_ownerid)values (?,?,?)";
            
            sta= connection.prepareStatement(req);
            sta.setString(1,t.getComment());
            sta.setInt(2,i);
            sta.setInt(3,s);
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void update(Comment t) {
         try {
            String req = "UPDATE `comment` SET `comment` = ? WHERE `comment`.`id` = ?;";
            
            sta= connection.prepareStatement(req);
            sta.setString(1, t.getComment());
            sta.setInt(2,t.getId());
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removeById(int id) {
        String rq= "DELETE FROM `comment` WHERE `comment`.`id` =?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
          //  System.out.println("don deleting comment :"+id);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    public Comment findById(int id) {
        
        Comment p;
        String rq="select * FROM `comment` where id= ?";
        
        try {  
            sta =connection.prepareStatement(rq);
           
            sta.setInt(1, id);
            ResultSet result =sta.executeQuery();
            if(result.next()){
                p=new Comment();
                p.setId(result.getInt(1));
                p.setComment(result.getString(4));
                return p;            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
    public List<Comment> findAll() {
        List<Comment> l=new ArrayList<>();
        Comment p;
        String rq="select * FROM `comment`";
        
        try {  
            sta =connection.prepareStatement(rq);
           
            ResultSet result =sta.executeQuery();
            while(result.next()){
                p=new Comment();
                p.setId(result.getInt(1));
                p.setComment(result.getString(4));
                l.add(p);            
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
    public Map<Comment,House> findbyOwnerID(int ownerid) {
        String rq="SELECT * FROM `comment` WHERE `fk_comment_ownerid` = ?";
        
        Comment p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            
            ResultSet result =sta.executeQuery();
            Map <Comment,House> H = new HashMap<>();
            HouseDAO hd=new HouseDAO();
            while(result.next()){
               p=new Comment();
                p.setId(result.getInt(1));
                p.setComment(result.getString(4));
                H.put(p, hd.findbyID(result.getInt(2)));                 
            }return H;   
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public HashMap<Comment,User> findbyHouseID(int houseid) {
        String rq="SELECT * FROM `comment` WHERE `fk_comment_houseid` = ?";
        
        Comment p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, houseid);
            
            ResultSet result =sta.executeQuery();
            HashMap <Comment,User> H = new HashMap<>();
            UserDAO u =new UserDAO();
            while(result.next()){
                p=new Comment();
                p.setId(result.getInt(1));
                p.setComment(result.getString(4));
                H.put(p, u.findbyID(result.getInt(3)));
                
                                
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public House findHouseById(int id){
        String rq="SELECT * FROM `comment` WHERE `id` = ?";
        
        
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1,id);
            
            ResultSet result =sta.executeQuery();
            
            if(result.next()){ 
             return new HouseDAO().findbyID(result.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    
}
