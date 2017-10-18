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
public class Message {
    
    private int id;
    private String text;
    private Forum forum;
    private User user;

    public Message(int id, String text, Forum forum) {
        this.id = id;
        this.text = text;
        this.forum = forum;
    }

    public Message(int id, String text, Forum forum, User user) {
        this.id = id;
        this.text = text;
        this.forum = forum;
        this.user = user;
    }

    
    
    

    public Message() {
    }

    public Message(String text, Forum forum) {
        this.text = text;
        this.forum = forum;
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }
    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
