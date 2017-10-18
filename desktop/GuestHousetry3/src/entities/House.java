/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entitiesDao.CommentDAO;
import entitiesDao.RateDAO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grami
 */
public class House {
    
   protected int id;
   protected String country;
   protected String town;
   protected String nature;
   protected int price;
   protected String description;
   protected int rating;
   protected boolean airconditioner;
   protected boolean fireplace;
   protected boolean washingmachine;
   protected boolean dishwasher;
   protected boolean dvd;
   protected boolean indoorpool;
   protected boolean sauna;
   protected boolean jacuzzi;
   protected boolean tv;
   protected boolean elevator;
   protected boolean barbecue;
   protected boolean garden;
   protected boolean childrengames;
   protected boolean outdoorparking;
   protected boolean indoorparking;
   protected boolean gardenfurniture;
   protected boolean terrace;
   protected String other;
   protected int equipment =0;
   protected Image picture1;
   protected Image picture2;
   protected Image picture3;

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment() {
        if(isAirconditioner())
            equipment++;
        if(isBarbecue())
            equipment++;
        if(isChildrengames())
            equipment++;
        if(isDishwasher())
            equipment++;
        if(isDvd())
            equipment++;
        if(isElevator())
            equipment++;
        if(isFireplace())
            equipment++;
        if(isGarden())
            equipment++;
        if(isGardenfurniture())
            equipment++;
        if(isIndoorparking())
            equipment++;
        if(isIndoorpool())
            equipment++;
        if(isJacuzzi())
            equipment++;
        if(isOutdoorparking())
            equipment++;
        if(isSauna())
            equipment++;
        if(isTerrace())
            equipment++;
        if(isTv())
            equipment++;
        if(isWashingmachine())
            equipment++;
        if(other!=null)
            equipment++;
    }
   
   protected List<Rate> rates;
   protected Map<Comment,User> comments;
   
   public void addRates(){
       
       RateDAO rd=new RateDAO();
       if(rates==null)
           rates=new ArrayList<>();
       rates=rd.findbyHouseID(id);   
}
   
   public void addComments(){
       CommentDAO rd=new CommentDAO();
       if(comments==null)
           comments=new HashMap<>();
       comments=rd.findbyHouseID(id);       
   }
    public House() {
        
    }

    public List<Rate> getRates() {
        return rates;
    }

    public Map<Comment, User> getComments() {
        return comments;
    }

    public House(String country, String town, String nature, int price, String description) {
        this.country = country;
        this.town = town;
        this.nature = nature;
        this.price = price;
        this.description = description;
        setEquipment();
    }

    public House(String country, String town, String nature, int price) {
        this.country = country;
        this.town = town;
        this.nature = nature;
        this.price = price;
        setEquipment();
    }
        
    public House(House h){
        this(h.id, h.country, h.town, h.nature, h.price, h.description, h.rating,
                h.airconditioner, h.fireplace, h.washingmachine, h.dishwasher, h.dvd,
                h.indoorpool, h.sauna, h.jacuzzi, h.tv, h.elevator, h.barbecue, h.garden,
                h.childrengames, h.outdoorparking, h.indoorparking, h.gardenfurniture, h.terrace, h.other);
        setEquipment();
    }
    
    public House(int id, String country, String town, String nature, int price, int rating) {
        this.id = id;
        this.country = country;
        this.town = town;
        this.nature = nature;
        this.price = price;
        this.rating = rating;
        setEquipment();
    }

    public Image getPicture1() {
        return picture1;
    }

    public void setPicture1(Image picture1) {
        this.picture1 = picture1;

    }

    public Image getPicture2() {
        return picture2;
    }

    public void setPicture2(Image picture2) {
        this.picture2 = picture2;
    }

    public Image getPicture3() {
        return picture3;
    }

    public void setPicture3(Image picture3) {
        this.picture3 = picture3;
    }

    public House(int id, String country, String town, String nature, int price, String description, int rating, boolean airconditioner, boolean fireplace, boolean washingmachine, boolean dishwasher, boolean dvd, boolean indoorpool, boolean sauna, boolean jacuzzi, boolean tv, boolean elevator, boolean barbecue, boolean garden, boolean childrengames, boolean outdoorparking, boolean indoorparking, boolean gardenfurniture, boolean terrace, String other, Image picture1, Image picture2,Image picture3) {
        this.id = id;
        this.country = country;
        this.town = town;
        this.nature = nature;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.airconditioner = airconditioner;
        this.fireplace = fireplace;
        this.washingmachine = washingmachine;
        this.dishwasher = dishwasher;
        this.dvd = dvd;
        this.indoorpool = indoorpool;
        this.sauna = sauna;
        this.jacuzzi = jacuzzi;
        this.tv = tv;
        this.elevator = elevator;
        this.barbecue = barbecue;
        this.garden = garden;
        this.childrengames = childrengames;
        this.outdoorparking = outdoorparking;
        this.indoorparking = indoorparking;
        this.gardenfurniture = gardenfurniture;
        this.terrace = terrace;
        this.other = other;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 =picture3;
    }

    
    
    public House(int id, String country, String town, String nature, int price,
            String description, int rating, boolean airconditioner, boolean fireplace,
            boolean washingmachine, boolean dishwasher, boolean dvd, boolean indoorpool,
            boolean sauna, boolean jacuzzi, boolean tv, boolean elevator, boolean barbecue,
            boolean garden, boolean childrengames, boolean outdoorparking,
            boolean indoorparking, boolean gardenfurniture, boolean terrace, String other) {
        this.id = id;
        this.country = country;
        this.town = town;
        this.nature = nature;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.airconditioner = airconditioner;
        this.fireplace = fireplace;
        this.washingmachine = washingmachine;
        this.dishwasher = dishwasher;
        this.dvd = dvd;
        this.indoorpool = indoorpool;
        this.sauna = sauna;
        this.jacuzzi = jacuzzi;
        this.tv = tv;
        this.elevator = elevator;
        this.barbecue = barbecue;
        this.garden = garden;
        this.childrengames = childrengames;
        this.outdoorparking = outdoorparking;
        this.indoorparking = indoorparking;
        this.gardenfurniture = gardenfurniture;
        this.terrace = terrace;
        this.other = other;
        setEquipment();
    }

    
    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    public String getNature() {
        return nature;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public boolean isAirconditioner() {
        return airconditioner;
    }

    public boolean isFireplace() {
        return fireplace;
    }

    public boolean isWashingmachine() {
        return washingmachine;
    }

    public boolean isDishwasher() {
        return dishwasher;
    }

    public boolean isDvd() {
        return dvd;
    }

    public boolean isIndoorpool() {
        return indoorpool;
    }

    public boolean isSauna() {
        return sauna;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isElevator() {
        return elevator;
    }

    public boolean isBarbecue() {
        return barbecue;
    }

    public boolean isGarden() {
        return garden;
    }

    public boolean isChildrengames() {
        return childrengames;
    }

    public boolean isOutdoorparking() {
        return outdoorparking;
    }

    public boolean isIndoorparking() {
        return indoorparking;
    }

    public boolean isGardenfurniture() {
        return gardenfurniture;
    }

    public boolean isTerrace() {
        return terrace;
    }

    public String getOther() {
        return other;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setAirconditioner(boolean airconditioner) {
        this.airconditioner = airconditioner;
    }

    public void setFireplace(boolean fireplace) {
        this.fireplace = fireplace;
    }

    public void setWashingmachine(boolean washingmachine) {
        this.washingmachine = washingmachine;
    }

    public void setDishwasher(boolean dishwasher) {
        this.dishwasher = dishwasher;
    }

    public void setDvd(boolean dvd) {
        this.dvd = dvd;
    }

    public void setIndoorpool(boolean indoorpool) {
        this.indoorpool = indoorpool;
    }

    public void setSauna(boolean sauna) {
        this.sauna = sauna;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public void setBarbecue(boolean barbecue) {
        this.barbecue = barbecue;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public void setChildrengames(boolean childrengames) {
        this.childrengames = childrengames;
    }

    public void setOutdoorparking(boolean outdoorparking) {
        this.outdoorparking = outdoorparking;
    }

    public void setIndoorparking(boolean indoorparking) {
        this.indoorparking = indoorparking;
    }

    public void setGardenfurniture(boolean gardenfurniture) {
        this.gardenfurniture = gardenfurniture;
    }

    public void setTerrace(boolean terrace) {
        this.terrace = terrace;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final House other = (House) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "House{" + "id=" + id + ", country=" + country + ", town=" + town + ", nature=" + nature + ", price=" + price + ", description=" + description + ", rating=" + rating + ", airconditioner=" + airconditioner + ", fireplace=" + fireplace + ", washingmachine=" + washingmachine + ", dishwasher=" + dishwasher + ", dvd=" + dvd + ", indoorpool=" + indoorpool + ", sauna=" + sauna + ", jacuzzi=" + jacuzzi + ", tv=" + tv + ", elevator=" + elevator + ", barbecue=" + barbecue + ", garden=" + garden + ", childrengames=" + childrengames + ", outdoorparking=" + outdoorparking + ", indoorparking=" + indoorparking + ", gardenfurniture=" + gardenfurniture + ", terrace=" + terrace + ", other=" + other + ", equipment=" + equipment + ", picture1=" + picture1 + ", picture2=" + picture2 + ", picture3=" + picture3 + ", rates=" + rates + ", comments=" + comments + '}';
    }

 
   
}
