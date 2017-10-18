package entitiesDao;

import entities.*;
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
public class ForumDAO  {

    private final Connection connection;
    private PreparedStatement sta;

    public ForumDAO() {
        connection = DataSource.getData().getConnection();
    }

    
    public void add(Forum m, int oid) {
        try {
            String rq = "INSERT INTO `forum` (`subject`,`fk_forum_ownerid`) VALUES (?,?);";

            sta = connection.prepareStatement(rq);
            sta.setString(1, m.getSubject());
            sta.setInt(2, oid);

            sta.executeUpdate();
            System.out.println("don adding forum");
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Forum f) {

        try {

            String rq = "UPDATE `forum` SET `subject` = ? , `iscreated` = ? WHERE `forum`.`id` = ?";

            sta = connection.prepareStatement(rq);
            sta.setString(1, f.getSubject());
            sta.setInt(3, f.getId());
            sta.setBoolean(2, f.isCreated());
            sta.executeUpdate();
            System.out.println("done updating forum");
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeById(int id) {
        String rq = "DELETE FROM `forum` WHERE `forum`.`id` = ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
            System.out.println("don deleting Forum :" + id);
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findAllNotCreated() {

        int s = 0;
        List<Forum> h = findAll();
        for (Forum forum : h) {
            if (!forum.isCreated()) {
                s++;
            }
        }
        return s;
    }

    public List<Forum> findAll() {

        List<Forum> l = new ArrayList<>();
        Forum f;
        String rq = "select * FROM `forum`";
        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                f = new Forum();
                f.setId(result.getInt(1));
                f.setSubject(result.getString(3));
                f.setCreated(result.getBoolean(4));
                l.add(f);

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User findOwnerById(int id) {

        User f;

        String rq = "select * FROM `forum` where id =?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result = sta.executeQuery();
            if (result.next()) {
                UserDAO ud = new UserDAO();
                f = ud.findbyID(result.getInt(2));

                return f;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Forum> findByOwnerId(int idowner) {
        List<Forum> l = new ArrayList<>();

        String rq = "SELECT * FROM `forum` WHERE `fk_forum_ownerid` = ?";
        Forum f;
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, idowner);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                f = new Forum();
                f.setId(result.getInt(1));
                f.setSubject(result.getString(3));
                f.setCreated(result.getBoolean(4));
                if (f.isCreated()) {
                    l.add(f);
                }

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Forum> findbyKeyWord(String keyword) {
        List<Forum> l = new ArrayList<>();
        String rq = "SELECT * FROM `forum` WHERE `subject` LIKE ? ";
        Forum f;
        keyword = "%" + keyword + "%";
        try {
            sta = connection.prepareStatement(rq);
            sta.setString(1, keyword);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                f = new Forum();
                f.setId(result.getInt(1));
                f.setSubject(result.getString(3));
                f.setCreated(result.getBoolean(4));
                if (f.isCreated()) {
                    l.add(f);
                }

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public Forum findById(int forumid) {
        Forum f = new Forum();

        String rq = "select * FROM `forum` where id= ?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, forumid);

            ResultSet result = sta.executeQuery();
            if (result.next()) {
                f = new Forum();
                f.setId(result.getInt(1));
                f.setSubject(result.getString(3));
                f.setCreated(result.getBoolean(4));
                return f;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
