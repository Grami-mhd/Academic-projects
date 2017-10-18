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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import utils.Pics;

/**
 *
 * @author grami
 */
public class HouseDAO {

    private Connection connection;
    private PreparedStatement sta;

    public HouseDAO() {
        connection = DataSource.getData().getConnection();
    }

    public void setPicture(House h, int picnbr) {
        try {

            if (picnbr == 1) {
                String rq = "UPDATE `house` SET picture1 = ? WHERE house.id =?";

                sta = connection.prepareStatement(rq);
                sta.setBlob(1, Pics.ImageToBlob(h.getPicture1()));
            }

            if (picnbr == 2) {
                String rq = "UPDATE `house` SET picture2 = ? WHERE house.id =?";

                sta = connection.prepareStatement(rq);
                sta.setBlob(1, Pics.ImageToBlob(h.getPicture2()));
            }

            if (picnbr == 3) {
                String rq = "UPDATE `house` SET picture3 = ? WHERE house.id =?";

                sta = connection.prepareStatement(rq);
                sta.setBlob(1, Pics.ImageToBlob(h.getPicture3()));
            }
            sta.setInt(2, h.getId());
            sta.executeUpdate();

            System.out.println("Done Updating House!!");
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(House t, int ownerId) {
        try {
            String rq = "INSERT INTO `house` (`id`, `country`, `town`, `nature`, `price`,"
                    + " `description`, `rating`, `fk_user`, `airconditioner`, `fireplace`,"
                    + " `washingmachine`, `dishwasher`, `dvd`, `indoorpool`,"
                    + " `sauna`, `jacuzzi`, `tv`, `elevator`, `barbecue`, `garden`, `childrengames`, `outdoorparking`,"
                    + " `indoorparking`, `gardenfurniture`, `terrace`, `other`) VALUES (?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            sta = connection.prepareStatement(rq);

            sta.setInt(1, t.getId());
            sta.setString(2, t.getCountry());
            sta.setString(3, t.getTown());
            sta.setString(4, t.getNature());
            sta.setInt(5, t.getPrice());
            sta.setString(6, t.getDescription());
            sta.setInt(7, t.getRating());

            sta.setInt(8, ownerId);
            sta.setBoolean(9, t.isAirconditioner());
            sta.setBoolean(10, t.isFireplace());
            sta.setBoolean(11, t.isWashingmachine());
            sta.setBoolean(12, t.isDishwasher());
            sta.setBoolean(13, t.isDvd());
            sta.setBoolean(14, t.isIndoorpool());
            sta.setBoolean(15, t.isSauna());
            sta.setBoolean(16, t.isJacuzzi());
            sta.setBoolean(17, t.isTv());

            sta.setBoolean(18, t.isElevator());
            sta.setBoolean(19, t.isBarbecue());
            sta.setBoolean(20, t.isGarden());
            sta.setBoolean(21, t.isChildrengames());
            sta.setBoolean(22, t.isOutdoorparking());
            sta.setBoolean(23, t.isIndoorparking());
            sta.setBoolean(24, t.isGardenfurniture());
            sta.setBoolean(25, t.isTerrace());
            sta.setString(26, t.getOther());

            sta.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(House t) {
        try {

            String rq = "UPDATE `house` SET `country`=?,`town`=?,`nature`=?,`price`=?,`description`=?,"
                    + "`rating`=?,`airconditioner`=?,`fireplace`=?,"
                    + "`washingmachine`=?,`dishwasher`=?,`dvd`=?,`indoorpool`=?,"
                    + "`sauna`=?,`jacuzzi`=?,`tv`=?,`elevator`=?,`barbecue`=?,"
                    + "`garden`=?,`childrengames`=?,`outdoorparking`=?,`indoorparking`=?,"
                    + "`gardenfurniture`=?,`terrace`=?,`other`=?  WHERE house.id =?";

            sta = connection.prepareStatement(rq);

            sta.setString(1, t.getCountry());
            sta.setString(2, t.getTown());
            sta.setString(3, t.getNature());
            sta.setInt(4, t.getPrice());
            sta.setString(5, t.getDescription());
            sta.setInt(6, t.getRating());

            sta.setBoolean(7, t.isAirconditioner());
            sta.setBoolean(8, t.isFireplace());
            sta.setBoolean(9, t.isWashingmachine());
            sta.setBoolean(10, t.isDishwasher());
            sta.setBoolean(11, t.isDvd());
            sta.setBoolean(12, t.isIndoorpool());
            sta.setBoolean(13, t.isSauna());
            sta.setBoolean(14, t.isJacuzzi());
            sta.setBoolean(15, t.isTv());

            sta.setBoolean(16, t.isElevator());
            sta.setBoolean(17, t.isBarbecue());
            sta.setBoolean(18, t.isGarden());
            sta.setBoolean(19, t.isChildrengames());
            sta.setBoolean(20, t.isOutdoorparking());
            sta.setBoolean(21, t.isIndoorparking());
            sta.setBoolean(22, t.isGardenfurniture());
            sta.setBoolean(23, t.isTerrace());
            sta.setString(24, t.getOther());
            sta.setInt(25, t.getId());

            sta.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeById(int id) {
        String rq = "DELETE FROM `house` WHERE `house`.`id` =?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<House> findAll() {
        List<House> l = new ArrayList<>();
        House p;
        String rq = "select * FROM `house`";
        try {
            sta = connection.prepareStatement(rq);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                p = new House();

                p.setId(result.getInt(1));
                p.setCountry(result.getString(3));
                p.setTown(result.getString(4));
                p.setNature(result.getString(5));

                p.setPrice(result.getInt(6));
                p.setDescription(result.getString(7));
                p.setRating(result.getInt(8));

                p.setAirconditioner(result.getBoolean(9));

                p.setFireplace(result.getBoolean(10));
                p.setWashingmachine(result.getBoolean(11));

                p.setDishwasher(result.getBoolean(12));
                p.setDvd(result.getBoolean(13));
                p.setIndoorpool(result.getBoolean(14));

                p.setSauna(result.getBoolean(15));
                p.setJacuzzi(result.getBoolean(16));
                p.setTv(result.getBoolean(17));

                p.setElevator(result.getBoolean(18));
                p.setBarbecue(result.getBoolean(19));
                p.setGarden(result.getBoolean(20));

                p.setChildrengames(result.getBoolean(21));
                p.setOutdoorparking(result.getBoolean(22));
                p.setIndoorparking(result.getBoolean(23));

                p.setGardenfurniture(result.getBoolean(24));
                p.setTerrace(result.getBoolean(25));
                p.setOther(result.getString(26));
                
                p.setPicture1(Pics.BlobToImage(result.getBlob(27)));
                p.setPicture2(Pics.BlobToImage(result.getBlob(28)));
                p.setPicture3(Pics.BlobToImage(result.getBlob(29)));

                l.add(p);

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public House findbyID(int id) {
        String rq = "SELECT * FROM `house` WHERE `id` = ?";
        House p;
        try {
            sta = connection.prepareStatement(rq);
            sta.setString(1, "" + id);

            ResultSet result = sta.executeQuery();
            if (result.next()) {
                p = new House(result.getInt(1), result.getString(3), result.getString(4),
                        result.getString(5), result.getInt(6), result.getString(7),
                        result.getInt(8), result.getBoolean(9), result.getBoolean(10),
                        result.getBoolean(11), result.getBoolean(12), result.getBoolean(13),
                        result.getBoolean(14), result.getBoolean(15), result.getBoolean(16),
                        result.getBoolean(17), result.getBoolean(18), result.getBoolean(19),
                        result.getBoolean(20), result.getBoolean(21), result.getBoolean(22),
                        result.getBoolean(23), result.getBoolean(24), result.getBoolean(25),
                        result.getString(26));
                p.setPicture1(Pics.BlobToImage(result.getBlob(27)));
                p.setPicture2(Pics.BlobToImage(result.getBlob(28)));
                p.setPicture3(Pics.BlobToImage(result.getBlob(29)));
                return p;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<House> findbyOwnerID(int id) {
        List<House> l = new ArrayList<>();
        String rq = "SELECT * FROM `house` WHERE `fk_user` = ?";
        House p;
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);

            ResultSet result = sta.executeQuery();
            while (result.next()) {
                p = new House(result.getInt(1), result.getString(3), result.getString(4),
                        result.getString(5), result.getInt(6), result.getString(7),
                        result.getInt(8), result.getBoolean(9), result.getBoolean(10),
                        result.getBoolean(11), result.getBoolean(12), result.getBoolean(13),
                        result.getBoolean(14), result.getBoolean(15), result.getBoolean(16),
                        result.getBoolean(17), result.getBoolean(18), result.getBoolean(19),
                        result.getBoolean(20), result.getBoolean(21), result.getBoolean(22),
                        result.getBoolean(23), result.getBoolean(24), result.getBoolean(25),
                        result.getString(26));
                p.setPicture1(Pics.BlobToImage(result.getBlob(27)));
                p.setPicture2(Pics.BlobToImage(result.getBlob(28)));
                p.setPicture3(Pics.BlobToImage(result.getBlob(29)));

                l.add(p);

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public User findOwnerById(int id) {

        User f;

        String rq = "select * FROM `house` where id =?";
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
}
