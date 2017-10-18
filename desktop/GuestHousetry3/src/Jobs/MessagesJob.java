/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Message;
import entities.User;
import entitiesDao.MessageDAO;

/**
 *
 * @author grami
 */
public class MessagesJob {

    private Message m;
    private User u;
    public MessagesJob(Message m) {
        MessageDAO md=new MessageDAO();
        u=md.findOwnerById(m.getId());
        this.m=m;
    }

    public User getUser() {
        return u;
    }
    
    
}
