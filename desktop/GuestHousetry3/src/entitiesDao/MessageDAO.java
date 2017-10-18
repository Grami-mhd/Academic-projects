package entitiesDao;


import entities.Forum;
import entities.Message;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author grami
 */
public class MessageDAO {
    
    private Connection connection;
    private PreparedStatement sta;
    
     public MessageDAO() {
        connection=DataSource.getData().getConnection();
    }

    
    
    public void add(Message m,int fid,int oid) {
        try {
            String rq="INSERT INTO `message` (`text`, `fk_message_forumid`, `fk_message_ownerid`) VALUES (?,?,?);";
            
            sta =connection.prepareStatement(rq);
            sta.setString(1,m.getText());
            sta.setInt(2,fid);
            sta.setInt(3,oid);
            
            sta.executeUpdate();
           // System.out.println("don editing data base");
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    
    public void update(int x,String s) {
        
        try {
        
        String rq="UPDATE `message` SET `text` = ? WHERE `message`.`id` = ?;";
       
            sta =connection.prepareStatement(rq);
            sta.setString(1,s);
            sta.setInt(2,x);
            
            sta.executeUpdate();
           // System.out.println("don updating message");
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }

    
    
    public void removeById(int id) {
       String rq= "DELETE FROM `message` WHERE `message`.`id` = ?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            sta.executeUpdate();
         //   System.out.println("don deleting user :"+id);
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    public Message findById(int id) {
        
        
        Message m;
        String rq="select * FROM `message` where id =?";
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            ResultSet result =sta.executeQuery();
            if(result.next()){
                m=new Message();
                m.setId(result.getInt(1));
                m.setText(result.getString(4));
                return m;     
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    
    public List<Message> findAll() {
         List<Message> l=new ArrayList<>();
        Message m;
        String rq="select * FROM `message`";
        try {            
            sta =connection.prepareStatement(rq);
           
            ResultSet result =sta.executeQuery();
            while(result.next()){
                m=new Message();
                m.setId(result.getInt(1));
                m.setText(result.getString(4));
                
                l.add(m);
                
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

 
    
    public Map<Message,Forum> findbyOwnerId(int Ownerid) {
        String rq="SELECT * FROM `message` WHERE `fk_message_ownerid` = ?";
        Message m;
        try {            
            sta =connection.prepareStatement(rq);
            sta.setInt(1,Ownerid);
            ForumDAO fd=new ForumDAO();
            ResultSet result =sta.executeQuery();
            Map <Message,Forum> H = new TreeMap<>();
            while(result.next()){
                m=new Message(result.getInt(1),result.getString(4));                
                H.put(m,fd.findById(result.getInt(2)));
            }
            return H; 
            } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
      return null;
    }
    
    
    public Forum findForumById(int id){
        String rq="SELECT * FROM `message` WHERE `id` = ?";
        Forum f;
        Message p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, id);
            
            ResultSet result =sta.executeQuery();
            UserDAO u =new UserDAO();
            if(result.next()){
                f=(new ForumDAO()).findById(result.getInt(2));
                return f;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
    public Map<Message,User> findbyForumID(int forumid) {
        String rq="SELECT * FROM `message` WHERE `fk_message_forumid` = ?";
        
        Message p;
         try {     
            sta =connection.prepareStatement(rq);
            sta.setInt(1, forumid);        
            ResultSet result =sta.executeQuery();
            Map <Message,User> H = new TreeMap<>();
            UserDAO u =new UserDAO();
            while(result.next()){
                p=new Message(result.getInt(1),result.getString(4));
                H.put(p, u.findbyID(result.getInt(3)));                
            }return H;
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
          
    
    public User findOwnerById(int id){
        
        User f;
        
        String rq="select * FROM `message` where id =?";
        try {            
            sta =connection.prepareStatement(rq);
           sta.setInt(1, id);
            ResultSet result =sta.executeQuery();
            if(result.next()){
                UserDAO ud=new UserDAO();
                f=ud.findbyID(result.getInt(3));
                 return f;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;
     }
}
