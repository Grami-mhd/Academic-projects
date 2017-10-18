/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author asus
 */
public class HostingDemande {
    private int id;
    private String text;
    private House house;

    public HostingDemande() {
    }

    public HostingDemande(String text, House house) {
        this.text = text;
        this.house = house;
    }
    
    
    public HostingDemande(int id, String text, House add) {
        this.id = id;
        this.text = text;
        this.house = add;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Hostingdemande{" + "id=" + id + ", text=" + text + '}';
    }
    
    
}
