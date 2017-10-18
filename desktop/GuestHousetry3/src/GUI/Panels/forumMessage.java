/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.forum;
import entities.Message;
import entities.User;
import entitiesDao.MessageReportDAO;
import javax.swing.ImageIcon;


public class forumMessage extends javax.swing.JPanel {
    
    Message c;
    
    forum p;
    public forumMessage(User u,Message c,forum f) {
        this.c=c;
        p=f;
        initComponents();
        if(u.getPicture()!=null)
                pic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(40,40, 4)));
        firstname.setText(u.getFirstName());
        lastname.setText(u.getLastName());
        msg.setText(c.getText());
               
    }

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        pic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        firstname = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lastname = new javax.swing.JLabel();
        msg = new javax.swing.JTextArea();
        breport = new javax.swing.JButton();

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserMiniImage.png"))); // NOI18N
        jToolBar1.add(pic);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(100, 100));

        firstname.setText("name");

        lastname.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstname)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastname))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(firstname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastname)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel1);

        msg.setEditable(false);
        msg.setColumns(20);
        msg.setRows(5);
        msg.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 255, 255), null, null));
        jToolBar1.add(msg);

        breport.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        breport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reportmsg.png"))); // NOI18N
        breport.setText("Report");
        breport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                breportMouseClicked(evt);
            }
        });
        breport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breportActionPerformed(evt);
            }
        });
        jToolBar1.add(breport);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void breportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breportActionPerformed
        
        new MessageReportDAO().add(this.c.getId(),p.getU().getId());
        p.setMessages();
        
    }//GEN-LAST:event_breportActionPerformed

    private void breportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_breportMouseClicked
        
        
        
    }//GEN-LAST:event_breportMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton breport;
    private javax.swing.JLabel firstname;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextArea msg;
    private javax.swing.JLabel pic;
    // End of variables declaration//GEN-END:variables
}
