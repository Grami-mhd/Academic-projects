/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Message;
import GUI.singleForumGUI;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Mohamed
 */
public class MessageDAO {

    public void addMessage(Message m) {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "messages/addmessage.php?msgforumid=" + m.getForum().getId() + "&msgownerid=" + m.getUser().getId() + "&txt=" + m.getText());
        NetworkManager.getInstance().addToQueue(cnx);

    }

    public void deleteMesssage(Message m) {

        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

            }

            @Override
            protected void postResponse() {
                
                Form frm = guesthouse.getF();
                frm.removeAll();
                frm.add(new singleForumGUI().viewSingleForum(m.getForum()));
                new ForumDAO().getForumMessages(m.getForum().getId());
                frm.repaint();
            }

        };

        connectionRequest.setUrl(guesthouse.localhost + "messages/deletemessage.php?id=" + m.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    public void reportMessage(Message m){
        
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest(); 
        
        connectionRequest.setUrl(guesthouse.localhost + "messages/reportmessage.php?reporterid=" + guesthouse.user.getId()+"&msgid="+m.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
        
    }
    
    public void updatemessage(Message m) {

        ConnectionRequest cnx;
        cnx = new ConnectionRequest(guesthouse.localhost + "messages/updatemesage.php?text="+m.getText()+ "&id=" + m.getId());
        
        NetworkManager.getInstance().addToQueue(cnx);

    }

}
