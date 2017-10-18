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
public class User {
    private int id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String town;
    private long phone;
    private int points=0;
    private com.codename1.ui.Image picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public com.codename1.ui.Image getPicture() {
        return picture;
    }

    public void setPicture(com.codename1.ui.Image picture) {
        this.picture = picture;
    }
    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", town=" + town + ", phone=" + phone + ", points=" + points + ", picture=" + picture + '}';
    }
    
}
