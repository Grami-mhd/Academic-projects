/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Dialogs.UserNotification;
import GUI.Dialogs.logIn;
import GUI.Dialogs.viewHostingDemands;
import GUI.Dialogs.web;
import entities.Notification;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author grami
 */
public class GuestHouse extends javax.swing.JFrame implements Runnable {

    public static User u = null;

    public GuestHouse() {

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (u == null) {
            userMenuBar.setVisible(false);
        } else {
            userMenuBar.setVisible(true);
            points.setText("" + u.getPoints());
            userProfile.setText(u.getFirstName() + " " + u.getLastName());
            if (u.getPicture() != null) {
                profilePicture.setIcon(new javax.swing.ImageIcon(u.getPicture().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH)));
            }
            thread = new Thread(this, "notifications");
            thread.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        userMenuBar = new javax.swing.JToolBar();
        image = new javax.swing.JLabel();
        profilePicture = new javax.swing.JLabel();
        userProfile = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        notifpic = new javax.swing.JLabel();
        notifications = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dems = new javax.swing.JLabel();
        hds = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        forums = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        claims = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        stays = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        points = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sell = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        buy = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        userMenuBar.setBackground(new java.awt.Color(204, 204, 204));
        userMenuBar.setFloatable(false);
        userMenuBar.setForeground(new java.awt.Color(0, 102, 102));
        userMenuBar.setRollover(true);
        userMenuBar.add(image);

        profilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        profilePicture.setText(" ");
        profilePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePictureMouseClicked(evt);
            }
        });
        userMenuBar.add(profilePicture);

        userProfile.setBackground(new java.awt.Color(102, 102, 102));
        userProfile.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        userProfile.setForeground(new java.awt.Color(204, 204, 204));
        userProfile.setText("user profile  ");
        userProfile.setFocusable(false);
        userProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userProfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userProfileActionPerformed(evt);
            }
        });
        userMenuBar.add(userProfile);

        jLabel5.setText("   ");
        userMenuBar.add(jLabel5);

        notifpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notif.png"))); // NOI18N
        notifpic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notifpicMouseClicked(evt);
            }
        });
        userMenuBar.add(notifpic);

        notifications.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        notifications.setForeground(new java.awt.Color(255, 0, 0));
        notifications.setText("5");
        notifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationsMouseClicked(evt);
            }
        });
        userMenuBar.add(notifications);

        jLabel3.setText("        ");
        userMenuBar.add(jLabel3);

        dems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dem.png"))); // NOI18N
        dems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                demsMouseClicked(evt);
            }
        });
        userMenuBar.add(dems);

        hds.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        hds.setForeground(new java.awt.Color(255, 0, 0));
        hds.setText("5");
        hds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hdsMouseClicked(evt);
            }
        });
        userMenuBar.add(hds);

        jLabel4.setText("    ");
        userMenuBar.add(jLabel4);

        forums.setBackground(new java.awt.Color(255, 255, 255));
        forums.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        forums.setText("forums  ");
        forums.setFocusable(false);
        forums.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forums.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        forums.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forumsActionPerformed(evt);
            }
        });
        userMenuBar.add(forums);

        jLabel14.setText("     ");
        userMenuBar.add(jLabel14);

        claims.setBackground(new java.awt.Color(255, 255, 255));
        claims.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        claims.setText("claims  ");
        claims.setFocusable(false);
        claims.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        claims.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        claims.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimsActionPerformed(evt);
            }
        });
        userMenuBar.add(claims);

        jLabel13.setText("    ");
        userMenuBar.add(jLabel13);

        stays.setBackground(new java.awt.Color(255, 255, 255));
        stays.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        stays.setText("Stays");
        stays.setFocusable(false);
        stays.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stays.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staysActionPerformed(evt);
            }
        });
        userMenuBar.add(stays);

        jLabel15.setText("                  ");
        userMenuBar.add(jLabel15);

        jLabel1.setText("points = ");
        userMenuBar.add(jLabel1);

        points.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        points.setText("450");
        userMenuBar.add(points);

        jLabel11.setText("              ");
        userMenuBar.add(jLabel11);

        sell.setBackground(new java.awt.Color(255, 255, 255));
        sell.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        sell.setText("  Sell");
        sell.setFocusable(false);
        sell.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sell.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellActionPerformed(evt);
            }
        });
        userMenuBar.add(sell);

        jLabel9.setText("  ");
        userMenuBar.add(jLabel9);

        buy.setBackground(new java.awt.Color(255, 255, 255));
        buy.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        buy.setText("  Buy");
        buy.setFocusable(false);
        buy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyActionPerformed(evt);
            }
        });
        userMenuBar.add(buy);

        jLabel6.setText("        ");
        userMenuBar.add(jLabel6);

        jButton1.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton1.setText("     Home    ");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        userMenuBar.add(jButton1);

        jLabel2.setText("                                                                 ");
        userMenuBar.add(jLabel2);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/entities/logout.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        userMenuBar.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(userMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 315, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profilePictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePictureMouseClicked
        new ViewProfil().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_profilePictureMouseClicked

    private void userProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userProfileActionPerformed
        new ViewProfil().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_userProfileActionPerformed

    private void forumsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forumsActionPerformed
        new myForums().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_forumsActionPerformed

    private void claimsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimsActionPerformed

        new MenageClaims().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_claimsActionPerformed

    private void staysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staysActionPerformed

        new RateStay().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_staysActionPerformed

    private void sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellActionPerformed

        new web(this, true).setVisible(true);
    }//GEN-LAST:event_sellActionPerformed

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed
        new web(this, true).setVisible(true);

    }//GEN-LAST:event_buyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void notifpicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifpicMouseClicked
        if (uns == null) {
            uns = new UserNotification(this, true);
        }
        uns.setVisible(true);
    }//GEN-LAST:event_notifpicMouseClicked

    private void notificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsMouseClicked
        if (uns == null) {
            uns = new UserNotification(this, true);
        }
        uns.setVisible(true);
    }//GEN-LAST:event_notificationsMouseClicked

    private void demsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_demsMouseClicked
        hdsd = new viewHostingDemands(this, true);
        hdsd.setVisible(true);
    }//GEN-LAST:event_demsMouseClicked

    private void hdsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hdsMouseClicked
        hdsd = new viewHostingDemands(this, true);
        hdsd.setVisible(true);
    }//GEN-LAST:event_hdsMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (hdsd != null) {
            hdsd.dispose();
        }
        if (uns != null) {
            uns.dispose();
        }
    }//GEN-LAST:event_formMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       new logIn().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    public void disposeNotifications(){
        uns.setVisible(false);
        
    }
    public void resetPoints(){
         points.setText("" + u.getPoints());
    }
    protected viewHostingDemands hdsd;
    protected UserNotification uns;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buy;
    private javax.swing.JButton claims;
    private javax.swing.JLabel dems;
    private javax.swing.JButton forums;
    private javax.swing.JLabel hds;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel notifications;
    private javax.swing.JLabel notifpic;
    private javax.swing.JLabel points;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JButton sell;
    private javax.swing.JButton stays;
    private javax.swing.JToolBar userMenuBar;
    private javax.swing.JButton userProfile;
    // End of variables declaration//GEN-END:variables

    private Thread thread;

    /*@Override
    public void dispose(){
    thread.stop();
    super.dispose();
    }*/
    @Override
    public void run() {
        while (true) {
            try {
                int n = 0;

                u.addNotifications();
                u.addHostingDemands();
                if (u.getNotifications() != null) {
                    for (Notification object : u.getNotifications()) {
                        if (!object.isIsSeen()) {
                            n++;
                        }
                    }
                    notifications.setText("   " + n + "   ");
                } else {
                    notifications.setText("   " + 0 + "   ");
                }
                if (u.getHostingDemands() != null) {
                    hds.setText("  " + u.getHostingDemands().size() + "  ");
                } else {
                    hds.setText("  " + 0 + "  ");
                }
                thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(GuestHouse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
