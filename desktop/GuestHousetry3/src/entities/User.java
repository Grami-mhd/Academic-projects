/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entitiesDao.*;
import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author grami
 */
public class User {
    
    private int id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String town;
    private long phone;
    private int points=0;
    private Image picture;
    private boolean isAdmin = false;
    
    private List<Forum> myForums; 
    private List<House> myHouses;
    private List<House> myStays;
    private Map<Claim,House> myClaims;
    private Map<Message,Forum> myMessages;
    private List<Forum> bannedFrom;
    private Map<Comment,House> myComments;
    private  Map< House,Rate> myRates;
    private List<Message> reportedMessages;
    private List<Forum> reportedForums;
    private List<Comment> reportedComments;
    private List<Add> adds;
    private List<Notification> notifications;
    private Map<HostingDemande,User> hostingDemands;

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public void addForums(){
        ForumDAO fd=new ForumDAO();
        myForums=fd.findByOwnerId(id);
        
    } 
    public void addHouses(){
        HouseDAO hd=new HouseDAO();
        myHouses=hd.findbyOwnerID(id);
    }    
    public void addStays(){
        StayDAO sd=new StayDAO();
        myStays =sd.findByUserID(id);
    }    
    public void addClaims(){
        ClaimDAO cd=new ClaimDAO();
        myClaims =cd.findbyOwnerID(id);
    }    
    public void addMessages(){
        MessageDAO md=new MessageDAO();
        myMessages= md.findbyOwnerId(id);       
    }   
    public void addBannedFromForums(){
        BanDAO fbd=new BanDAO();
        bannedFrom= fbd.findByUserID(id);
    }  
    public void addComments(){
        CommentDAO cd =new CommentDAO();
        myComments= cd.findbyOwnerID(id);
    }    
    public void addRates(){
        RateDAO rd=new RateDAO();
        myRates=rd.findbyOwnerID(id);
    }   
    public void addReportedMessages(){
        MessageReportDAO mrd=new MessageReportDAO();
        reportedMessages = mrd.findByOwnerId(id);
    }    
    public void addReportedForums(){
        ForumReportDAO frd =new ForumReportDAO();
        reportedForums =frd.findbyUserId(id);
    }   
    public void addReportedComments(){
        CommentReportDAO crd=new CommentReportDAO();
        reportedComments =crd.findbyOwnerID(id);
    }   
    public void addAdds(){
        AddDAO ad=new AddDAO();
       adds=ad.findByOwnerId(id);
    }  
    public void addNotifications(){
        NotificationDAO nd=new NotificationDAO();
        notifications=nd.findbyUserID(id);
    }
    public void addHostingDemands(){
        HostingDemandeDAO nd=new HostingDemandeDAO();
        hostingDemands =nd.findbyReceiverID(id);
    }

    public void arm(){
        addForums();
        addHouses();
        addMessages();
        addComments();
        addRates();
        addReportedMessages();
        addReportedForums();
        addReportedComments();
        addNotifications();
        addAdds();
        addBannedFromForums();
        addClaims();
        addStays();
        addHostingDemands();
    }
    
    public User() {
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Map<HostingDemande, User> getHostingDemands() {
        return hostingDemands;
    }

    public void setHostingDemands(Map<HostingDemande, User> hostingDemands) {
        this.hostingDemands = hostingDemands;
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

    @Override
    public int hashCode() {
        int hash = 35;
       
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
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" + "mail=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", town=" + town + ", phone=" + phone + ", points=" + points + ", picture=" + picture + ", isAdmin=" + isAdmin + ", myForums=" + myForums + ", myHouses=" + myHouses + ", myStays=" + myStays + ", myClaims=" + myClaims + ", myMessages=" + myMessages + ", bannedFrom=" + bannedFrom + ", myComments=" + myComments + ", myRates=" + myRates + ", reportedMessages=" + reportedMessages + ", reportedForums=" + reportedForums + ", reportedComments=" + reportedComments + ", adds=" + adds + ", notifications=" + notifications + ", hostingDemands=" + hostingDemands + '}';
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public List<Forum> getMyForums() {
        return myForums;
    }

    public List<House> getMyHouses() {
        return myHouses;
    }

    public List<House> getMyStays() {
        return myStays;
    }

    public Map<Claim, House> getMyClaims() {
        return myClaims;
    }

    public Map<Message, Forum> getMyMessages() {
        return myMessages;
    }

    public List<Forum> getBannedFrom() {
        return bannedFrom;
    }

    public Map<Comment, House> getMyComments() {
        return myComments;
    }

    public Map< House,Rate> getMyRates() {
        return myRates;
    }

    public List<Message> getReportedMessages() {
        return reportedMessages;
    }

    public List<Forum> getReportedForums() {
        return reportedForums;
    }

    public List<Comment> getReportedComments() {
        return reportedComments;
    }

    public List<Add> getAdds() {
        return adds;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

   
    
    
}
