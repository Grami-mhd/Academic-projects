package entitiesDao;

import entities.Forum;
import entities.Message;
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

public class MessageReportDAO {

    private Connection connection;
    private PreparedStatement sta;

    public MessageReportDAO() {
        connection = DataSource.getData().getConnection();
    }

    
    public void add(int messageid, int ownerid) {
        String rq = "INSERT INTO `messagereport` (`fk_reporter_id`, `fk_message_id`) VALUES (?,?);";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            sta.setInt(2, messageid);
            sta.executeUpdate();
            //System.out.println("don editing data base");

        } catch (SQLException ex) {
            Logger.getLogger(MessageReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void removeById(int messageId, int ownerId) {
        String rq = "DELETE FROM `messagereport` WHERE `messagereport`.`fk_reporter_id` = ? AND `messagereport`.`fk_message_id` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, ownerId);
            sta.setInt(2, messageId);
            sta.executeUpdate();
            //System.out.println("don deleting message report :");
        } catch (SQLException ex) {
            Logger.getLogger(CommentReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Message> findByOwnerId(int ownerid) {
        String rq = "SELECT * FROM `messagereport` WHERE `fk_reporter_id` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            ResultSet result = sta.executeQuery();
            List<Message> H = new ArrayList<>();
            MessageDAO fd = new MessageDAO();
            while (result.next()) {
                H.add(fd.findById(result.getInt(3)));
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<Message, User> findAll() {
        String rq = "SELECT * FROM `messagereport`";
        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            Map<Message, User> H = new HashMap<>();
            UserDAO udao = new UserDAO();
            MessageDAO mdao = new MessageDAO();
            while (result.next()) {
                if (!result.getBoolean(4)) {
                    H.put(mdao.findById(result.getInt(3)), udao.findbyID(result.getInt(2)));
                }
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getnbr(){
        return findAll().size();
    }
    
    public List<String> findbyMessageID(int messageid) {
        String rq = "SELECT * FROM `messagereport` WHERE `fk_message_id` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, messageid);

            ResultSet result = sta.executeQuery();
            List<String> H = new ArrayList<>();
            while (result.next()) {

                H.add(result.getString(2));
            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public int numberOfReports(User u) {
        MessageDAO mdao = new MessageDAO();
        Map<Message, Forum> m = new HashMap<>();
        m = mdao.findbyOwnerId(u.getId());
        int nbr = 0;
        for (Map.Entry<Message, Forum> entry : m.entrySet()) {
            Message key = entry.getKey();
            nbr += findbyMessageID(key.getId()).size();

        }
        return nbr;
    }

    public void setTreated(int messageId) {
        String rq = "UPDATE `messagereport` SET `istreated` = '1' WHERE `messagereport`.`fk_message_id` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, messageId);
            sta.executeUpdate();
           // System.out.println("don editing data base");

        } catch (SQLException ex) {
            Logger.getLogger(MessageReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
