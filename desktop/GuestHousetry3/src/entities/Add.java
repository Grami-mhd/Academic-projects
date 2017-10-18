/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entitiesDao.DateDAO;
import java.sql.Date;
import java.util.TreeSet;

/**
 *
 * @author grami
 */
public class Add extends House{

    TreeSet<Date> dates;

    public Add() {
        
    } 
    public Add(House h){
        super(h);
         
    }
    public Add(int id, String country, String town, String nature, int price, int rating) {
        super(id, country, town, nature, price, rating);
    }
    public Add(int id, String country, String town, String nature, int price, String description, int rating, boolean airconditioner, boolean fireplace, boolean washingmachine, boolean dishwasher, boolean dvd, boolean indoorpool, boolean sauna, boolean jacuzzi, boolean tv, boolean elevator, boolean barbecue, boolean garden, boolean childrengames, boolean outdoorparking, boolean indoorparking, boolean gardenfurniture, boolean terrace, String other) {
        super(id, country, town, nature, price, description, rating, airconditioner, fireplace, washingmachine, dishwasher, dvd, indoorpool, sauna, jacuzzi, tv, elevator, barbecue, garden, childrengames, outdoorparking, indoorparking, gardenfurniture, terrace, other);
    }
    public Add(String country, String town, String nature, int price, String description) {
        super(country, town, nature, price, description);
    }
    public Add(String country, String town, String nature, int price) {
        super(country, town, nature, price);
    }
    public Add( House h,TreeSet<Date> dates) {
        super(h);
        this.dates = dates;
    }
   
    
    
    
    public House getHouse() {
        return (House)this;
    }

    public void deleteDate(Date d){
        dates.remove(d);
    }
    
    public void addDate(Date d){
        if(dates==null)
            dates=new TreeSet<>();
        dates.add(d);
    }
    
    public TreeSet<Date> getDates() {
        return dates;
    }
   
    public void setDates() {
        if(dates==null)
            dates=new TreeSet<>();
        
        DateDAO dd=new DateDAO();
        this.dates = dd.findByAddId(id);
    }

    @Override
    public String toString() {
        return "Add{" +super.toString()+ "dates=" + dates + '}';
    }


    
    
}
