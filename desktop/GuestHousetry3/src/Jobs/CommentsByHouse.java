/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Comment;
import entities.House;
import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ROGUE_BIBO
 */
public class CommentsByHouse {
   
    Map<Comment,User> m;
    private List<Comment> lc;
    
    public CommentsByHouse(House h){
        
        m =new HashMap<>();
        lc=new ArrayList<>();
        m=h.getComments();
        for(Comment e : m.keySet()){
            lc.add(e);
        
    }
    
}

    
 public List<Comment> getLc() {
        return lc;
    }
    
    
    



}