/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Forum;
import entities.Message;
import entities.Notification;
import entities.User;
import entitiesDao.BanDAO;
import entitiesDao.MessageDAO;
import entitiesDao.MessageReportDAO;
import entitiesDao.NotificationDAO;
import entitiesDao.UserDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import utils.Mail;

/**
 *
 * @author grami
 */
public class MessageReportJobs {
    private User u;
    private Message m;
    private MessageReportDAO mrdao;
    private Forum f;
    public MessageReportJobs(Message m,User u){
        this.m=m;
        this.u=u;
        f=new MessageDAO().findForumById(m.getId());
       mrdao=new MessageReportDAO();
        
    }
    public int getNumberOfReports(){
        return mrdao.numberOfReports(u);
    }
    
    public void notifyUser(){
        Notification n = new Notification("you have been reported due to bad language", new Date(Calendar.getInstance().getTimeInMillis()),"forum"+f.getId());
        new NotificationDAO().add(n, u.getId());
        mrdao.setTreated(m.getId());
    }
    public void deleteMessage(){
        Notification n = new Notification("your message "+m.getText()+" have been deleted after it was reported", new Date(Calendar.getInstance().getTimeInMillis()),"forum"+f.getId());
        new NotificationDAO().add(n, u.getId());
        new MessageDAO().removeById(m.getId());      
        mrdao.setTreated(m.getId());
    }
    public void banUser(){
        Notification n = new Notification("you have been baned from the forum :"+f.getSubject(), new Date(Calendar.getInstance().getTimeInMillis()),null);
        new NotificationDAO().add(n, u.getId());
        new BanDAO().add(u.getId(), f.getId());
        mrdao.setTreated(m.getId());
    }
    public void deleteAccount(){
         new UserDAO().removeById(u.getId());
         String to[]={u.getEmail()};
            try {
                Mail.sendFromGMail("guest.house.pi@gmail.com","N.O-Society",to, "Deleting account","we are sorry to tell you that your GuestHouse account was deleted due to a claim about you");
            } catch (MessagingException ex) {
                Logger.getLogger(Penality.class.getName()).log(Level.SEVERE, null, ex);
            }
         mrdao.setTreated(m.getId());
    }
    public void doNothing(){
        mrdao.setTreated(m.getId());
    }
}
