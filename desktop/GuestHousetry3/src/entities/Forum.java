/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entitiesDao.MessageDAO;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author grami
 */
public class Forum {
    
    private int id;
    private String subject;
    private Map<Message, User> messages;
    private boolean created;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
    
    public Forum(String subject) {
        
        this.subject = subject;
    }

    public Forum(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }
    
    public Forum() {
    }

    public Map<Message, User> getMessages() {
        return messages;
    }

    public void setMessages() {
       
        messages=new TreeMap<>();
        MessageDAO md=new MessageDAO();
        this.messages = md.findbyForumID(id);
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 76;
       
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Forum other = (Forum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", subject=" + subject + ", messages=" + messages + '}';
    }




}

