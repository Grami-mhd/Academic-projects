/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;

/**
 *
 * @author grami
 */

public class Add extends House{

    private List<String> alldates;

    public List<String> getAlldates() {
        return alldates;
    }

    public void setAlldates(List<String> alldates) {
        this.alldates = alldates;
    }
    
    private String dates;

    public Add(String dates, String country, String town, String nature, int price, String description,User fk_user) {
        super(country, town, nature, price, description,fk_user);
        this.dates = dates;
    }

    public Add(String dates, String country, String town, String nature, int price, String description) {
        super(country, town, nature, price, description);
        this.dates = dates;
    }

    public Add(String dates, String country, String town, String nature, int price) {
        super(country, town, nature, price);
        this.dates = dates;
    }   

    public Add(String dates, int id, String country, String town, String nature, int price) {
        super(id, country, town, nature, price);
        this.dates = dates;
    }

    public Add(String dates, int id, String country, String town, String nature, int price, int rating) {
        super(id, country, town, nature, price, rating);
        this.dates = dates;
    }

    public Add(String dates, int id, String country, String town, String nature, int price, String description, int rating) {
        super(id, country, town, nature, price, description, rating);
        this.dates = dates;
    }

    
    public Add(String dates, int id, String country, String town, String nature, int price, String description, int rating, boolean airconditioner, boolean fireplace, boolean washingmachine, boolean dishwasher, boolean dvd, boolean indoorpool, boolean sauna, boolean jacuzzi, boolean tv, boolean elevator, boolean barbecue, boolean garden, boolean childrengames, boolean outdoorparking, boolean indoorparking, boolean gardenfurniture, boolean terrace, String other) {
        super(id, country, town, nature, price, description, rating, airconditioner, fireplace, washingmachine, dishwasher, dvd, indoorpool, sauna, jacuzzi, tv, elevator, barbecue, garden, childrengames, outdoorparking, indoorparking, gardenfurniture, terrace, other);
        this.dates = dates;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
    
}