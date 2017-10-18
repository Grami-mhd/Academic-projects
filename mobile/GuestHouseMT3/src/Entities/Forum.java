/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Mohamed
 */
public class Forum {
    
    private int id;
    private String subject;
    private boolean created;
    private User user;
    private int nbrMessages;

    public Forum() {
    }

    public Forum(int id, String subject, boolean created, User user) {
        this.id = id;
        this.subject = subject;
        this.created = created;
        this.user = user;
    }

    public Forum(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }
    
    

    public Forum(String subject) {
        this.subject = subject;
    }

    public Forum(int id, String subject, User user, int nbrMessages) {
        this.id = id;
        this.subject = subject;
        this.user = user;
        this.nbrMessages = nbrMessages;
    }
    
    

    public Forum(int id, String subject, User user) {
        this.id = id;
        this.subject = subject;
        this.user = user;
    }
    
    
    public Forum(int id, String subject, boolean created) {
        this.id = id;
        this.subject = subject;
        this.created = created;
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

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNbrMessages() {
        return nbrMessages;
    }

    public void setNbrMessages(int nbrMessages) {
        this.nbrMessages = nbrMessages;
    }
    
    
    
    
    
    
}
