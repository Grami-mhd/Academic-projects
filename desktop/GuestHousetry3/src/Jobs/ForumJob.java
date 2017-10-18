/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import entities.Forum;
import entities.User;
import entitiesDao.ForumDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nabtaaa
 */
public class ForumJob {

    private ForumDAO fdao;
    private List<Forum> lf;
    
    

   

    public ForumJob(User u) {
        fdao = new ForumDAO();
        lf=fdao.findByOwnerId(u.getId());
        List<Forum> ff=new ArrayList<>();
        for (Forum forum : lf) {
            if(forum.isCreated())
               ff.add(forum);
        }
        lf=ff;
    }

    public List<Forum> getLf() {
        return lf;
    }

    
  
    
    
    
    
    
}
