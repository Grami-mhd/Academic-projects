/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs;

import Jobs.MessageReportJobs;
import entities.Message;
import entities.User;
import javax.swing.ImageIcon;

/**
 *
 * @author grami
 */
public class MsgPenality extends javax.swing.JDialog {

    private MessageReportJobs mrj;

    /**
     * Creates new form MsgPenality
     */
    public MsgPenality(java.awt.Frame parent, boolean modal, User u, Message m) {
        super(parent, modal);
        initComponents();
        mrj = new MessageReportJobs(m, u);
        if (u.getPicture() != null) {
            ppic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(150, 150, 4)));
        }

        firstName.setText(u.getFirstName());
        lastName.setText(u.getLastName());
        email.setText(u.getEmail());
        nbreReport.setText("has been reported " + mrj.getNumberOfReports());
        textMessage.setText(m.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        banAccount = new javax.swing.JCheckBox();
        name = new javax.swing.JLabel();
        decline = new javax.swing.JButton();
        last = new javax.swing.JLabel();
        valider = new javax.swing.JButton();
        mail = new javax.swing.JLabel();
        firstName = new javax.swing.JLabel();
        lastName = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        nbreReport = new javax.swing.JLabel();
        Message = new javax.swing.JLabel();
        notify = new javax.swing.JCheckBox();
        textMessage = new javax.swing.JLabel();
        delete = new javax.swing.JCheckBox();
        ppic = new javax.swing.JLabel();
        banForum = new javax.swing.JCheckBox();
        Owner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        banAccount.setText("ban the account");

        name.setText("First Name :");

        decline.setBackground(new java.awt.Color(255, 255, 255));
        decline.setForeground(new java.awt.Color(255, 0, 0));
        decline.setText("Decline");
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });

        last.setText("Last Name :");

        valider.setText("Ok");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        mail.setText("Email :");

        firstName.setText("jLabel7");

        lastName.setText("jLabel8");

        email.setText("jLabel9");

        nbreReport.setText("jLabel2");

        Message.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Message.setForeground(new java.awt.Color(102, 102, 255));
        Message.setText("Message :");

        notify.setText("Notify the user  of transpassing rules");

        textMessage.setBackground(new java.awt.Color(255, 255, 255));

        delete.setText("delete message");

        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N

        banForum.setText("ban the access to this forum");
        banForum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banForumActionPerformed(evt);
            }
        });

        Owner.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Owner.setText("Owner :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(textMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(177, 177, 177)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(name)
                                                    .addComponent(mail)
                                                    .addComponent(last))
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(firstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                                    .addComponent(lastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(notify)
                                            .addComponent(delete)
                                            .addComponent(banForum)
                                            .addComponent(banAccount)
                                            .addComponent(nbreReport, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Owner)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(decline, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Owner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(last)
                            .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mail)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(nbreReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(notify)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(banForum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(banAccount)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        mrj.doNothing();
        this.setVisible(false);
    }//GEN-LAST:event_declineActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        if (notify.isSelected()) {

            mrj.notifyUser();
        }
        if (delete.isSelected()) {
            mrj.deleteMessage();

        }
        if (banForum.isSelected()) {
            mrj.banUser();
        }
        if (banAccount.isSelected()) {
            mrj.deleteAccount();
        }

        this.setVisible(false);
    }//GEN-LAST:event_validerActionPerformed

    private void banForumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banForumActionPerformed

    }//GEN-LAST:event_banForumActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Message;
    private javax.swing.JLabel Owner;
    private javax.swing.JCheckBox banAccount;
    private javax.swing.JCheckBox banForum;
    private javax.swing.JButton decline;
    private javax.swing.JCheckBox delete;
    private javax.swing.JLabel email;
    private javax.swing.JLabel firstName;
    private javax.swing.JLabel last;
    private javax.swing.JLabel lastName;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nbreReport;
    private javax.swing.JCheckBox notify;
    private javax.swing.JLabel ppic;
    private javax.swing.JLabel textMessage;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}