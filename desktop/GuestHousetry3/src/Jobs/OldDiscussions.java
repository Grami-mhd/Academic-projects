/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Forum;
import entities.Message;
import entities.User;
import entitiesDao.ForumDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mohamed
 */
public class OldDiscussions {

    private ForumDAO fdao;
    private List<Forum> lf;
   

    public OldDiscussions(User u) {
        fdao = new ForumDAO();
        Set<Forum> sf = new HashSet<>();

        lf = new ArrayList<>();
     

        Map<Message, Forum> m = new HashMap<>();
        m = u.getMyMessages();
        if(m!=null)
        for (Map.Entry<Message, Forum> entry : m.entrySet()) {
            Message message = entry.getKey();
            Forum forum = entry.getValue();
            sf.add(forum);
            
        }
        for (Forum forum : sf) {
            lf.add(forum);
        }

    }

    public List<Forum> getLf() {
        return lf;
    }
    
    

}
