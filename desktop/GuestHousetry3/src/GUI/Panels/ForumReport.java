/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.Dialogs.AdminViewForum;
import GUI.Dialogs.DeleteUser;
import GUI.Dialogs.ViewUserAsAdmin;
import GUI.Dialogs.deleteForum;
import Jobs.Penality;
import entities.Forum;
import entities.Notification;
import entities.User;
import entitiesDao.ForumDAO;
import entitiesDao.ForumReportDAO;
import entitiesDao.NotificationDAO;
import entitiesDao.UserDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import utils.Mail;

/**
 *
 * @author grami
 */
public class ForumReport extends javax.swing.JPanel {

    private JFrame parent;
    private User reporter;
    private User Owner;
    private Forum f;

    public ForumReport(User u, Forum f, JFrame par) {

        initComponents();
        this.parent = par;
        this.f = f;
        this.reporter = u;
        this.Owner = new ForumDAO().findOwnerById(f.getId());
        text.setText(reporter.getFirstName() + " " + reporter.getLastName() + " has reported this forum");
        subject.setText(this.f.getSubject());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        decline = new javax.swing.JButton();
        deleteForum = new javax.swing.JButton();
        viewReportedForum = new javax.swing.JButton();
        viewForumOwner = new javax.swing.JButton();
        viewReporter = new javax.swing.JButton();
        text = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subject = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Report :"));
        setToolTipText("");

        decline.setText("Decline");
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });

        deleteForum.setText("Delete Forum");
        deleteForum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteForumActionPerformed(evt);
            }
        });

        viewReportedForum.setText("View Reported Forum");
        viewReportedForum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportedForumActionPerformed(evt);
            }
        });

        viewForumOwner.setText("View Forum Owner");
        viewForumOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewForumOwnerActionPerformed(evt);
            }
        });

        viewReporter.setText("View Reporter");
        viewReporter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReporterActionPerformed(evt);
            }
        });

        text.setText("jLabel1");

        subject.setEditable(false);
        subject.setColumns(20);
        subject.setRows(5);
        jScrollPane1.setViewportView(subject);

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Delete User ?");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(decline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(deleteForum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(106, 106, 106)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(viewReportedForum, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(viewForumOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addGap(43, 43, 43)
                        .addComponent(viewReporter, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewReportedForum)
                    .addComponent(viewForumOwner)
                    .addComponent(viewReporter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(decline)
                            .addComponent(deleteForum))
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewReportedForumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportedForumActionPerformed
        new AdminViewForum(null, true, f).setVisible(true);
    }//GEN-LAST:event_viewReportedForumActionPerformed

    private void viewForumOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewForumOwnerActionPerformed
        new ViewUserAsAdmin(null, true, Owner).setVisible(true);
    }//GEN-LAST:event_viewForumOwnerActionPerformed

    private void viewReporterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReporterActionPerformed
        new ViewUserAsAdmin(null, true, reporter).setVisible(true);
    }//GEN-LAST:event_viewReporterActionPerformed

    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        new NotificationDAO().add(new Notification("your report of the forum :'"+f.getSubject()+"' has been declined", new Date(Calendar.getInstance().getTimeInMillis()),"forum"+f.getId()), reporter.getId());
        new ForumReportDAO().setTreated(f.getId(), reporter.getId());
    }//GEN-LAST:event_declineActionPerformed

    private void deleteForumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteForumActionPerformed
        deleteForum df = new deleteForum(this.parent, true);
        df.setVisible(true);
        if (df.getReturnStatus() == 1) {
            new NotificationDAO().add(new Notification("your forum :'"+f.getSubject()+"' has been deleted", new Date(Calendar.getInstance().getTimeInMillis()),null), Owner.getId());
            new ForumDAO().removeById(f.getId());
        }
    }//GEN-LAST:event_deleteForumActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        DeleteUser du = new DeleteUser(parent, true);
        du.setVisible(true);
        if (du.getReturnStatus() == 1) {
            String to[]={Owner.getEmail()};
            try {
                Mail.sendFromGMail("guest.house.pi@gmail.com","N.O-Society",to, "Deleting account","we are sorry to tell you that your GuestHouse account was deleted due to a report about your forum named :"+f.getSubject());
            } catch (MessagingException ex) {
                Logger.getLogger(Penality.class.getName()).log(Level.SEVERE, null, ex);
            }
            new UserDAO().removeById(Owner.getId());
        }
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton decline;
    private javax.swing.JButton deleteForum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea subject;
    private javax.swing.JLabel text;
    private javax.swing.JButton viewForumOwner;
    private javax.swing.JButton viewReportedForum;
    private javax.swing.JButton viewReporter;
    // End of variables declaration//GEN-END:variables
}
