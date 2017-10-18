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
public class Rate {
    
    private int id;
    private int rate;
    private int fk_rate_ownerid;
    private int fk_rate_houseid;

    public Rate() {
    }
    
    public Rate(int rate) {
        this.rate = rate;
    }
    
    public Rate(int id, int rate) {
        this.id = id;
        this.rate = rate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getFk_rate_ownerid() {
        return fk_rate_ownerid;
    }

    public void setFk_rate_ownerid(int fk_rate_ownerid) {
        this.fk_rate_ownerid = fk_rate_ownerid;
    }

    public int getFk_rate_houseid() {
        return fk_rate_houseid;
    }

    public void setFk_rate_houseid(int fk_rate_houseid) {
        this.fk_rate_houseid = fk_rate_houseid;
    }
    
    
    
}
