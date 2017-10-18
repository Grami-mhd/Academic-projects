/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author grami
 */
public class Rate {
    private int id;
    private int rate;
    

    public Rate() {
    }

    public Rate(int id, int rate) {
        this.id = id;
        this.rate = rate;
        
    }

    public Rate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

 

    @Override
    public String toString() {
        return "Rate{" + "id=" + id + ", rate=" + rate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
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
        final Rate other = (Rate) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    
}
