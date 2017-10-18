/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.Dialogs.CommentReportConfirmation;
import GUI.Dialogs.logIn;
import GUI.GuestHouse;
import GUI.viewAdd;
import entities.Comment;
import entities.User;
import entitiesDao.CommentReportDAO;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public class AddComment extends javax.swing.JPanel {

    private viewAdd parent;
    private Comment c;

    public AddComment(User u, Comment c, viewAdd parent) {
        initComponents();
        this.c = c;
        this.parent = parent;
         if(u.getPicture()!=null)
                pic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(50,50, 4)));
          
        firstname.setText(u.getFirstName());
        lastname.setText(u.getLastName());
        text.setText(c.getComment());

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
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        breport = new javax.swing.JButton();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserMiniImage.png"))); // NOI18N
        jToolBar1.add(pic);

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

        text.setEditable(false);
        text.setColumns(20);
        text.setRows(5);
        jScrollPane1.setViewportView(text);

        jToolBar1.add(jScrollPane1);

        breport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/messagereport.png"))); // NOI18N
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
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void breportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breportActionPerformed
        if (GuestHouse.u != null) {
            CommentReportConfirmation conf = new CommentReportConfirmation(parent, true);
            conf.setVisible(true);
            if (conf.getReturnStatus() == 1) {
                new CommentReportDAO().add(GuestHouse.u.getId(), c.getId());
                parent.setComments();
            }
        } else {
            new logIn().setVisible(true);
            parent.dispose();
        }
    }//GEN-LAST:event_breportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton breport;
    private javax.swing.JLabel firstname;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lastname;
    private javax.swing.JLabel pic;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables
}
