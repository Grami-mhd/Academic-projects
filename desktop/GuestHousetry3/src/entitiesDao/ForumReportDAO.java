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
public class ForumReportDAO  {

    private Connection connection;
    private PreparedStatement sta;

    public ForumReportDAO() {
        connection = DataSource.getData().getConnection();
    }

    public void add(int x, int y) {
        try {
            String rq = "INSERT INTO `forumreport` (`fk_forum_report_forumid`, `fk_forum_report_userid`) VALUES (?,?);";

            sta = connection.prepareStatement(rq);

            sta.setInt(1, x);
            sta.setInt(2, y);

            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ForumReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void removeById(int x, int y) {
        String rq = "DELETE FROM `forumreport` WHERE `forumreport`.`fk_forum_report_forumid` = ? AND fk_forum_report_userid = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, x);
            sta.setInt(2, y);
            sta.executeUpdate();
          //  System.out.println("don deleting forum report forum" + x + " user " + y);
        } catch (SQLException ex) {
            Logger.getLogger(ForumReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Forum> findbyUserId(int Userid) {
        String rq = "SELECT * FROM `forumreport` WHERE `fk_forum_report_userid` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, Userid);
            ForumDAO fd = new ForumDAO();
            ResultSet result = sta.executeQuery();
            List<Forum> H = new ArrayList<>();
            while (result.next()) {
                H.add(fd.findById(result.getInt(1)));
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ForumReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> findbyForumId(int Forumid) {
        String rq = "SELECT * FROM `forumreport` WHERE `fk_forum_report_forumid` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, Forumid);

            ResultSet result = sta.executeQuery();
            List<String> H = new ArrayList<>();
            while (result.next()) {
                H.add(result.getString(1));
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ForumReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int getNbrReports() {
        int f = 0;
        String rq = "SELECT * FROM `forumreport` where istreated = 0";
        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                f++;
            }
            
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Map<Forum, User> findAll() {
        String rq = "SELECT * FROM `forumreport`";
        try {
            sta = connection.prepareStatement(rq);
            ForumDAO fd = new ForumDAO();
            ResultSet result = sta.executeQuery();
            Map<Forum, User> H = new HashMap<>();
            while (result.next()) {
                if (!result.getBoolean(3)) {
                    H.put(fd.findById(result.getInt(1)), new UserDAO().findbyID(result.getInt(2)));
                }
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ForumReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setTreated(int id,int oid){
        String rq = "UPDATE `forumreport` SET `istreated` = '1' WHERE `forumreport`.`fk_forum_report_forumid` = ? AND `forumreport`.`fk_forum_report_userid` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1,id);
            sta.setInt(2, oid);
            sta.executeUpdate();
           // System.out.println("don editing data base");

        } catch (SQLException ex) {
            Logger.getLogger(MessageReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
