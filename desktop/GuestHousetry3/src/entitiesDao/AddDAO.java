
package entitiesDao;

import entities.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author grami
 */
public class AddDAO  {
    
    private Connection connection;
    private PreparedStatement sta;

    public AddDAO() {
        connection=DataSource.getData().getConnection();    
    }   
    

    public void add(Add a){

        HouseDAO hd=new HouseDAO();
        House h=hd.findbyID(a.getId());
        if (h!=null) {
            DateDAO dd = new DateDAO();
            for (Date d : a.getDates()) {
                dd.add(a.getId(), d);
            }            
        }
        else
            System.out.println("house does not exist !!!");
    }
     
    
    public void removeById(int id) {
        
        DateDAO d=new DateDAO();
        d.deleteByAddId(id);
    } 
    
   
    public void update(Add a){
        removeById(a.getId());
        add(a);
    }
    
    public Add findById(int id){
        HouseDAO hd=new HouseDAO();
        Add a=new Add(hd.findbyID(id));
       
             a.setDates();
        return a;
    }
    
    public List<Add> findAll(){
        List<Add> l=new ArrayList<>();
        List<House> hlist=new ArrayList<>();
        TreeSet<Date> dates=null;
        DateDAO dd=new DateDAO();
        HouseDAO hd=new HouseDAO();
       
        hlist=hd.findAll();
        for(House  h :  hlist){
            dates=null;
            dates=dd.findByAddId(h.getId());
            if(dates!=null)
                l.add(new Add(h, dates));
        }
        return l;
    }
    
    public List<Add> findByOwnerId(int id){
        List<Add> l=new ArrayList<>();
        List<House> hlist=new ArrayList<>();
        TreeSet<Date> dates=null;
        DateDAO dd=new DateDAO();
        HouseDAO hd=new HouseDAO();
        hlist=hd.findbyOwnerID(id);
        for(House  h :  hlist){
            dates=null;
            dates=dd.findByAddId(h.getId());
            if(dates!=null)
                l.add(new Add(h, dates));
        }
        return l;
    }
    
    public User findOwnerById(int id){
        
        User f;
        
        String rq="select * FROM `house` where id =?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result =sta.executeQuery();
            if(result.next()){
                UserDAO ud=new UserDAO();
                f=ud.findbyID(result.getInt(2));
                 return f;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;
     }
    
}
