/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author grami
 */
public class DataSource {
    
    private Connection connection;
    private String logIn;
    private String passWord;
    private String path;
    private Properties properties;
    private static DataSource data;
    
    private DataSource(){
        try {
            properties = new Properties();            
            properties.load(new FileInputStream(new File("GuestHouse.properties")));
            path=properties.getProperty("path");
            logIn=properties.getProperty("logIn");
            passWord=properties.getProperty("passWord");
            
            connection=DriverManager.getConnection(path, logIn,passWord );
            System.out.println("don connecting to "+path);
      
        } catch (IOException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getData() {
        if(data==null)
            data=new DataSource();
        return data;
    }  
}
