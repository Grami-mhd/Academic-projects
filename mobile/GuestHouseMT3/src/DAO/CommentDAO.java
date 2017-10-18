/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.guesthouse;

/**
 *
 * @author ROGUE_BIBO
 */
public class CommentDAO {
     
    public void addComment(int c,String txt){
        
        ConnectionRequest cr;
        cr = new ConnectionRequest(guesthouse.localhost+"stay/addcomment.php?comment="+txt+"&commenthouseid="+c+"&commentownerid="+guesthouse.user.getId());
        NetworkManager.getInstance().addToQueue(cr);
        
    }
}
