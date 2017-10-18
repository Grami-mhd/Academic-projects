/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import DAO.HostingDemandDAO;
import DAO.NotificationDAO;

/**
 *
 * @author grami
 */
public class notifsThread implements Runnable{
    private Thread thread;

    @Override
    public void run() {
        
        while (true) {
            try {
                System.out.println("threding for notifications");
                new NotificationDAO().countNotifications(guesthouse.user.getId());
                Thread.sleep(4000);
                new HostingDemandDAO().count();
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
           }
        }
    }
    
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this, "notifications_count");
        }
        thread.start();
    }

    public void kill() {
        thread.interrupt();
    }
}
