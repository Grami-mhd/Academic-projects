/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Notification;
import entities.User;
import java.util.List;

/**
 *
 * @author asus
 */
public class MyNotifications {
    
    private List<Notification> l;
    private User u;
    
    public MyNotifications(User u) {
        l = u.getNotifications();
    }

        public List<Notification> getNotification() {
        return l;
    }
}
