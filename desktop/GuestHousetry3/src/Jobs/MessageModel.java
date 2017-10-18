/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import entities.Message;
import entities.User;
import entitiesDao.MessageDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author asus
 */
public class MessageModel extends AbstractTableModel{

    
    private List<Message> mes;
    private List<User> users;
    private Map<Message,User> messages;
    private String[] Headers = {"user", "message"};
    private  Message m ;
   private MessageDAO ud=new MessageDAO();
    private int forumId;
    public MessageModel() {
        
        MessageDAO ud=new MessageDAO();
        messages = ud.findbyForumID(forumId);
    mes=new ArrayList<>();
    users=new ArrayList<>();
        for (Map.Entry<Message, User> entry : messages.entrySet()) {
            Message message = entry.getKey();
            User user = entry.getValue();
            mes.add(message);
            users.add(user);
        }
    }

    public MessageModel(int forumId) {
    mes=new ArrayList<>();
    users=new ArrayList<>();
        MessageDAO ud=new MessageDAO();
        messages = ud.findbyForumID(forumId);
        this.forumId = forumId;
        for (Map.Entry<Message, User> entry : messages.entrySet()) {
            Message message = entry.getKey();
            User user = entry.getValue();
            mes.add(message);
            users.add(user);
        }
    }
    
     public String getColumnName( int column){
        return Headers [column];
        
    }
    @Override
    public int getRowCount() {
        return messages.size() ;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
        
            return users.get(rowIndex).getFirstName()+" "+users.get(rowIndex).getLastName();
            
        }else{
            return mes.get(rowIndex).getText();
        }
       
    }
    
}
