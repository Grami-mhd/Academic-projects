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

public class ClaimDAO  {

    private Connection connection;
    private PreparedStatement sta;

    public ClaimDAO() {
        connection = DataSource.getData().getConnection();
    }

    public void add(Claim t, int houseid, int userid) {
        String rq = "INSERT INTO `claim` (`date`, `text`, `status`,`fk_claim_houseid`, `fk_claim_ownerrid`) VALUES (?,?,?,?,?);";
        try {
            sta = connection.prepareStatement(rq);
            sta.setDate(1, t.getDate());
            sta.setString(2, t.getText());
            sta.setString(3, t.getStatus());
            sta.setInt(4, houseid);
            sta.setInt(5, userid);
            sta.executeUpdate();
            System.out.println("don editing data base");
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeById(int id) {
        String rq = "DELETE FROM `claim` WHERE `claim`.`id` =?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
            System.out.println("don deleting claim :" + id);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Claim> findAll() {
        List<Claim> l = new ArrayList<>();
        Claim p;
        String rq = "select * FROM `claim`";

        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                p = new Claim();
                p.setId(result.getInt(1));
                p.setDate(result.getDate(4));
                p.setText(result.getString(5));
                p.setStatus(result.getString(6));
                p.setIsTreated(result.getBoolean(7));
                l.add(p);
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<Claim, House> findbyOwnerID(int ownerid) {
        String rq = "SELECT * FROM `claim` WHERE `fk_claim_ownerrid` = ?";

        Claim p;
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, ownerid);
            HouseDAO hd = new HouseDAO();
            ResultSet result = sta.executeQuery();
            Map<Claim, House> H = new HashMap<>();

            while (result.next()) {
p = new Claim();
                p.setId(result.getInt(1));
                p.setDate(result.getDate(4));
                p.setText(result.getString(5));
                p.setStatus(result.getString(6));
                p.setIsTreated(result.getBoolean(7));
                H.put(p, hd.findbyID(result.getInt(3)));

            }
            return H;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Claim> findByHouseId(int houseId) {

        List<Claim> l = new ArrayList<>();
        Claim p;
        String rq = "SELECT * FROM `claim` WHERE `fk_claim_houseid` = ?";

        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, houseId);
            ResultSet result = sta.executeQuery();
            while (result.next()) {
                p = new Claim();
                p.setId(result.getInt(1));
                p.setDate(result.getDate(4));
                p.setText(result.getString(5));
                p.setStatus(result.getString(6));
                p.setIsTreated(result.getBoolean(7));
                l.add(p);
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public User findOwnerById(int id) {
        List<Claim> l = new ArrayList<>();

        String rq = "select * FROM `claim` where id = ? ";

        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result = sta.executeQuery();
            if (result.next()) {
                return new UserDAO().findbyID(result.getInt(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public House findHouseById(int id) {
        String rq = "select * FROM `claim` where id =?";

        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result = sta.executeQuery();

            if (result.next()) {
                sta.setInt(1, id);
                return new HouseDAO().findbyID(result.getInt(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void update(Claim f) {

        try {

            String rq = "UPDATE `claim` SET `text` = ?, `status` = ?, `istreated` = ? WHERE `claim`.`id` = ?";

            sta = connection.prepareStatement(rq);
            sta.setInt(4,f.getId());
            sta.setString(1, f.getText());
            sta.setString(2, f.getStatus());
            sta.setBoolean(3, f.isIsTreated());
            sta.executeUpdate();
         //   System.out.println("done updating forum");
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int findNbr(){
        String rq = "SELECT * FROM `claim` WHERE `istreated` = 0";
        int n=0;
        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                n++;
            }
            return n;
        } catch (SQLException ex) {
            Logger.getLogger(ClaimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
//