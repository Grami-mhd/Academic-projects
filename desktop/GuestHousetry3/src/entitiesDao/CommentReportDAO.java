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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author grami
 */
public class CommentReportDAO {
    private Connection connection;
    private PreparedStatement sta;
    
    

    public CommentReportDAO() {
        connection=DataSource.getData().getConnection();
    }   
    
    public void add(int t,int s) {
        try {
            String req = "insert into commentreport(fk_comment_report_ownerid,fk_commentid)values (?,?)";
            
            sta= connection.prepareStatement(req);
            sta.setInt(1,t);
            sta.setInt(2,s);
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removeById(int t,int s) {
        String rq= "DELETE FROM `commentreport` WHERE `commentreport`.`fk_comment_report_ownerid` = ? AND `commentreport`.`fk_commentid` = ?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, t);
            sta.setInt(2, s);
            sta.executeUpdate();
            System.out.println("don deleting comment report :");
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public List<Comment> findbyOwnerID(int ownerid) {
        String rq="SELECT * FROM `commentreport` WHERE `fk_comment_report_ownerid` = ?";
        
        try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            CommentDAO cd =new CommentDAO();
            ResultSet result =sta.executeQuery();
            List<Comment> H= new ArrayList<>();
            while(result.next()){
                H.add( cd.findById(result.getInt(2)));                  
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    public List<User> findByCommentID(int messageid) {
         String rq="SELECT * FROM `commentreport` WHERE `fk_commentid` = ?";
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, messageid);
            UserDAO ud=new UserDAO();
            ResultSet result =sta.executeQuery();
            List<User> H = new ArrayList<>();
            while(result.next()){
               H.add(ud.findbyID(result.getInt(1)));                
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Comment> findAll(){
         String rq="SELECT * FROM `commentreport`";
        
        try {     
            sta =connection.prepareStatement(rq);
            CommentDAO cd =new CommentDAO();
            ResultSet result =sta.executeQuery();
            List<Comment> H= new ArrayList<>();
            while(result.next()){
                H.add( cd.findById(result.getInt(2)));                  
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Set<House> findAllHouses(){
         String rq="SELECT * FROM `commentreport`";
        
        try {     
            sta =connection.prepareStatement(rq);
            CommentDAO cd =new CommentDAO();
            ResultSet result =sta.executeQuery();
            Set<House> H= new HashSet<>();
            while(result.next()){
                H.add(new CommentDAO().findHouseById(result.getInt(2)));                  
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}