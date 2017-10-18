/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author ROGUE_BIBO
 */
public class Claim {
    
    private int id;
    private String date;
    private String text;
    private String status ;
    private boolean isTreated;
    private House house;

    public Claim(int id, String date, String text, String status, boolean isTreated) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.status = status;
        this.isTreated = isTreated;
    }

    public Claim(int id, String date, String text, House house) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.house = house;
    }
    
    

    public Claim(int id, String date, String text, String status) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.status = status;
    }

    public Claim() {
    }

    public Claim(String date, String text) {
        this.date = date;
        this.text = text;
    }

    public Claim(int id, String date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsTreated() {
        return isTreated;
    }

    public void setIsTreated(boolean isTreated) {
        this.isTreated = isTreated;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    
    

    @Override
    public String toString() {
        return "Claim{" + "id=" + id + ", date=" + date + ", text=" + text + ", status=" + status + ", isTreated=" + isTreated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id;
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
        final Claim other = (Claim) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
