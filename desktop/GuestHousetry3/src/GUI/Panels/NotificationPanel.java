/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.Dialogs.UserNotification;
import GUI.Dialogs.finishTheReservation;
import GUI.GuestHouse;
import GUI.MenageClaims;
import GUI.forum;
import entities.Notification;
import entitiesDao.AddDAO;
import entitiesDao.ForumDAO;
import entitiesDao.NotificationDAO;

/**
 *
 * @author asus
 */
public class NotificationPanel extends javax.swing.JPanel {

    private Notification n;
    private GuestHouse parent;
    private UserNotification parent2;
    
    public NotificationPanel(Notification n, GuestHouse p,UserNotification p2) {
        initComponents();
        parent=p;
        parent2=p2;
        this.n = n;
        date.setText(n.getDate().toString());
        text.setText(n.getText());

        if (n.isIsSeen()) {
            text.setBackground(new java.awt.Color(204, 204, 204));
        } else if (n.getType() != null) {
            if (n.getType().substring(0, 2).equals("hd")) {
                text.setBackground(new java.awt.Color(47, 244, 63));
            }
            if (n.getType().substring(0, 2).equals("fo")) {
                text.setBackground(new java.awt.Color(177, 243, 239));
            }
            if (n.getType().substring(0, 2).equals("cl")) {
                text.setBackground(new java.awt.Color(255, 177, 177));
            }
        } else {
            text.setBackground(new java.awt.Color(255, 255, 255));
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new javax.swing.JLabel();
        text = new javax.swing.JTextField();

        date.setText("Date: ");

        text.setEditable(false);
        text.setBackground(new java.awt.Color(153, 0, 153));
        text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textMouseExited(evt);
            }
        });
        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed
        if (n.getType().substring(0, 1).equals("fo")) {
            System.out.println("forum");
        } else if (n.getType().substring(0, 1).equals("cl")) {
            System.out.println("claim");
        } else if (n.getType().substring(0, 1).equals("hd")) {
            System.out.println("hosting demande");
        }
    }//GEN-LAST:event_textActionPerformed

    private void textMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseEntered
        if (!n.isIsSeen()) {
            text.setBackground(new java.awt.Color(247, 236, 203));
        }
    }//GEN-LAST:event_textMouseEntered

    private void textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseClicked

        if (!n.isIsSeen()) {
            n.setIsSeen(true);
            new NotificationDAO().setSeen(n);
        }
            if (n.getType() != null) {
                if (n.getType().substring(0, 2).equals("hd")) {
                    parent2.dispose();
                      new finishTheReservation(parent, true,new AddDAO().findById(Integer.parseInt(n.getType().substring(2,n.getType().length())))).setVisible(true);
                     
                }
                if (n.getType().substring(0, 2).equals("fo")) {
                    new forum(new ForumDAO().findById(Integer.parseInt(n.getType().substring(5,n.getType().length())))).setVisible(true);
                    parent.dispose();
                }
                if (n.getType().substring(0, 2).equals("cl")) {
                    new MenageClaims().setVisible(true);
                    parent.dispose();
                }
            } 
        
    }//GEN-LAST:event_textMouseClicked

    private void textMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseExited
        if (n.isIsSeen()) {
            text.setBackground(new java.awt.Color(204, 204, 204));
        } else if (n.getType() != null) {
            if (n.getType().substring(0, 2).equals("hd")) {
                text.setBackground(new java.awt.Color(47, 244, 63));
            }
            if (n.getType().substring(0, 2).equals("fo")) {
                text.setBackground(new java.awt.Color(177, 243, 239));
            }
            if (n.getType().substring(0, 2).equals("cl")) {
                text.setBackground(new java.awt.Color(255, 177, 177));
            }
        } else {
            text.setBackground(new java.awt.Color(255, 255, 255));
        }
    }//GEN-LAST:event_textMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
