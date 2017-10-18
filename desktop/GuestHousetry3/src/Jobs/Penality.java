/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import GUI.AdminView;
import GUI.Dialogs.DeleteUser;
import GUI.Dialogs.Refund;
import entities.Claim;
import entities.House;
import entities.Notification;
import entities.User;
import entitiesDao.ClaimDAO;
import entitiesDao.HouseDAO;
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
public class Penality {

    private User traveler;
    private User host;
    private House h;
    private Claim c;
    private AdminView p;

    public Penality(Claim c, AdminView p) {
        this.c = c;
        this.p = p;
        traveler = new ClaimDAO().findOwnerById(c.getId());
        h = new ClaimDAO().findHouseById(c.getId());
        host = new HouseDAO().findOwnerById(h.getId());
    }

    public void refund(int x) {
        Refund rd = new Refund(p, true);
        rd.setVisible(true);
        if (rd.getReturnStatus() == 1) {
            new NotificationDAO().add(new Notification("your claim :'" + c.getText() + "' has been approved", new Date(Calendar.getInstance().getTimeInMillis()),"claim"+c.getId()), traveler.getId());
            new NotificationDAO().add(new Notification("we are sorry to tell you thet there was a claim about you and you lost " + x + " point", new Date(Calendar.getInstance().getTimeInMillis()),null), host.getId());

            traveler.setPoints(traveler.getPoints() + x);
            host.setPoints(host.getPoints() - x);
            new UserDAO().update(host);
            new UserDAO().update(traveler);
            c.setIsTreated(true);
            c.setStatus("we refund you " + x + " point");
            new ClaimDAO().update(c);
        }
    }

    public void deleteHost() {
        DeleteUser du = new DeleteUser(p, true);
        du.setVisible(true);
        if (du.getReturnStatus() == 1) {
            
            new NotificationDAO().add(new Notification("your claim :'" + c.getText() + "' has been approved", new Date(Calendar.getInstance().getTimeInMillis()),"claim"+c.getId()), traveler.getId());
            String to[]={host.getEmail()};
            try {
                Mail.sendFromGMail("guest.house.pi@gmail.com","N.O-Society",to, "Deleting account","we are sorry to tell you that your GuestHouse account was deleted due to a claim about you");
            } catch (MessagingException ex) {
                Logger.getLogger(Penality.class.getName()).log(Level.SEVERE, null, ex);
            }
            new UserDAO().removeById(host.getId());
            c.setIsTreated(true);
            c.setStatus("the host has been deleted");
            new ClaimDAO().update(c);
        }
    }

    public void deleteTraveler() {
        DeleteUser du = new DeleteUser(p, true);
        du.setVisible(true);
        if (du.getReturnStatus() == 1) {
            try {
                String to[]={traveler.getEmail()};
                Mail.sendFromGMail("guest.house.pi@gmail.com","N.O-Society",to, "Deleting account","we are sorry to tell you that your GuestHouse account was deleted due to a claim about you");
            } catch (MessagingException ex) {
                Logger.getLogger(Penality.class.getName()).log(Level.SEVERE, null, ex);
            }
            new UserDAO().removeById(traveler.getId());
        }
    }

    public void deleteHouse() {
        new NotificationDAO().add(new Notification("your house , the one located in " + h.getCountry() + " " + h.getTown() + ", has been deleted due to a claim", new Date(Calendar.getInstance().getTimeInMillis()),null), host.getId());
        new NotificationDAO().add(new Notification("your claim :'" + c.getText() + "' has been approved", new Date(Calendar.getInstance().getTimeInMillis()),"claim"+c.getId()), traveler.getId());

        new HouseDAO().removeById(h.getId());
        new ClaimDAO().removeById(c.getId());
        c.setIsTreated(true);
        c.setStatus("the house has been deleted");
        new ClaimDAO().update(c);
    }

}
