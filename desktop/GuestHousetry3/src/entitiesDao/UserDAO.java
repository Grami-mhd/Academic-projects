/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDao;

import entities.User;
import guesthousetry1.GuestHouseTry1;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

public class UserDAO  {

    private final Connection connection;
    private PreparedStatement sta;
   

    public UserDAO() {
        connection = DataSource.getData().getConnection();
       
    }

   
    public void add(User u) {
        try {
          URLConnection c = new URL("https://guesthouse.000webhostapp.com/users/signup.php?un="
                   +u.getUsername()+"&pw="+u.getPassword()+"&em="+u.getEmail()+"&fn="+u.getFirstName()+"&ln="+
                   u.getLastName()).openConnection();
         
            c.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = c.getInputStream();
        int ch;
            StringBuffer str = new StringBuffer();
            while ((ch = response.read()) != -1) {
                str.append((char) ch);
            }
      
            System.out.println( str.toString());
            
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        System.out.println("https://guesthouse.000webhostapp.com/users/signup.php?un="+u.getUsername()+"&pw="+u.getPassword()+"&em="+u.getEmail()+"&fn="+u.getFirstName()+"&ln="+u.getLastName());
    }

  
    public void update(User t) {

        try {
            String rq = "UPDATE `user` SET `password` = ?, `firstname` = ?, `lastname` = ?, `country` = ?, `town` = ?, `phone` = ?, `points` = ? , `type` = ? WHERE `user`.`id` = ?";

            sta = connection.prepareStatement(rq);
            sta.setString(1, t.getPassword());
            sta.setString(2, t.getFirstName());
            sta.setString(3, t.getLastName());
            sta.setString(4, t.getCountry());
            sta.setString(5, t.getTown());
            sta.setString(6, "" + t.getPhone());
            sta.setInt(7, t.getPoints());
            sta.setBoolean(8, t.isIsAdmin());
            sta.setString(9, t.getEmail());
            sta.executeUpdate();
            System.out.println("don updating user");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void removeById(int id) {
        String rq = "DELETE FROM `user` WHERE `user`.`id` =?";
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
            System.out.println("don deleting user :" + id);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatePicture(User t){
        try {
            String rq = "UPDATE `user` SET `picture` = ? WHERE `user`.`email` = ?";

            sta = connection.prepareStatement(rq);
            
             if (t.getPicture() != null) {
                sta.setBlob(1, Pics.ImageToBlob(t.getPicture()));
            } else {
                sta.setString(1, "");
            }
             sta.setString(2, t.getEmail());
            sta.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
    public List<User> findAll() {
        List<User> l = new ArrayList<>();
        User p;
        String rq = "select * FROM `user`";
        try {
            sta = connection.prepareStatement(rq);
            ResultSet result = sta.executeQuery();
            while (result.next()) {
                p = new User();
                p.setId(result.getInt(1));
                p.setUsername(result.getString(2));
                p.setEmail(result.getString(4));
                p.setPassword(result.getString(8));
                p.setFirstName(result.getString(16));
                p.setLastName(result.getString(17));
                p.setCountry(result.getString(18));
                p.setTown(result.getString(19));
                p.setPhone(result.getLong(20));
                p.setPoints(result.getInt(21));
                String s =result.getString(14);
                if(s.charAt(2)=='1')
                    p.setIsAdmin(true);
                else
                    p.setIsAdmin(false);

                l.add(p);

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public User findbyID(int id) {
        String rq = "SELECT * FROM `user` WHERE `id` = ?";
        System.out.println("hhhhhhhh");
        try {
            sta = connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result = sta.executeQuery();
            System.out.println(sta.toString());
            
            
            if(result.next()) {
                System.out.println("hhhhhhhh");
                User p = new User();
                p.setId(result.getInt(1));
                p.setUsername(result.getString(2));
                p.setEmail(result.getString(4));
                
                p.setFirstName(result.getString(16));
                p.setLastName(result.getString(17));
                p.setCountry(result.getString(18));
                p.setTown(result.getString(19));
                p.setPhone(result.getLong(20));
                p.setPoints(result.getInt(21));
                String s =result.getString(14);
                
                if(s.charAt(2)=='1')
                    p.setIsAdmin(true);
                else
                    p.setIsAdmin(false);
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String findbyLogIn(String id, String password) {
             try {
            URLConnection c = new URL("https://guesthouse.000webhostapp.com/users/login.php?un=" + id + "&pw=" + password).openConnection();
            c.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = c.getInputStream();
            int ch;
            StringBuffer str = new StringBuffer();
            while ((ch = response.read()) != -1) {
                str.append((char) ch);
            }
      
           return str.toString();
            
            
        } catch (IOException ex) {
            Logger.getLogger(GuestHouseTry1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public User findbyusername(String id){
        String rq = "SELECT * FROM `user` WHERE `username` = ?";
        User p = null;
        try {
            sta = connection.prepareStatement(rq);
            sta.setString(1, id);

            ResultSet result = sta.executeQuery();
            if (result.next()) {

                p = new User();
                p.setId(result.getInt(1));
                p.setUsername(result.getString(2));
                p.setEmail(result.getString(4));
                p.setPassword(result.getString(8));
                p.setFirstName(result.getString(16));
                p.setLastName(result.getString(17));
                p.setCountry(result.getString(18));
                p.setTown(result.getString(19));
                p.setPhone(result.getLong(20));
                p.setPoints(result.getInt(21));
                String s =result.getString(14);
                if(s.charAt(2)=='1')
                    p.setIsAdmin(true);
                else
                    p.setIsAdmin(false);
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    public User findbyemail(String id){
        String rq = "SELECT * FROM `user` WHERE `email` = ?";
        User p = null;
        try {
            sta = connection.prepareStatement(rq);
            sta.setString(1, id);

            ResultSet result = sta.executeQuery();
            if (result.next()) {

                p = new User();
                p.setId(result.getInt(1));
                p.setUsername(result.getString(2));
                p.setEmail(result.getString(4));
                p.setPassword(result.getString(8));
                p.setFirstName(result.getString(16));
                p.setLastName(result.getString(17));
                p.setCountry(result.getString(18));
                p.setTown(result.getString(19));
                p.setPhone(result.getLong(20));
                p.setPoints(result.getInt(21));
                String s =result.getString(14);
                if(s.charAt(2)=='1')
                    p.setIsAdmin(true);
                else
                    p.setIsAdmin(false);
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
