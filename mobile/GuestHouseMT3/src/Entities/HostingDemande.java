/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author grami
 */
public class HostingDemande {
     private int id;
    private String text;
    private int houseid;
    private Entities.User sender;

    public HostingDemande() {
    }

    public HostingDemande(String text, int houseid, User senderid) {
        this.text = text;
        this.houseid = houseid;
        this.sender = senderid;
    }

    public HostingDemande(int id, String text, int houseid, User senderid) {
        this.id = id;
        this.text = text;
        this.houseid = houseid;
        this.sender = senderid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User senderid) {
        this.sender = senderid;
    }

   
   

    @Override
    public String toString() {
        return "Hostingdemande{" + "id=" + id + ", text=" + text + '}';
    }
   
}
