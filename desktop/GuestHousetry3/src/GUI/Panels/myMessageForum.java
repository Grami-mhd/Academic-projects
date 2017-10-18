/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.forum;
import entities.Message;
import entities.User;
import entitiesDao.MessageDAO;
import javax.swing.ImageIcon;

public class myMessageForum extends javax.swing.JPanel {

    Message m;
    private forum p;

    public myMessageForum(Message m, User u,forum p) {
        this.m = m;
        this.p=p;
        initComponents();
        if(u.getPicture()!=null)
                pic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(40,40, 4)));
        firstname.setText(u.getFirstName());
        lastname.setText(u.getLastName());
        message.setText(m.getText());
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        pic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        firstname = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lastname = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        updateB = new javax.swing.JButton();
        deleteB = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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

        message.setEditable(false);
        message.setColumns(20);
        message.setRows(5);
        message.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(message);

        jToolBar1.add(jScrollPane2);

        updateB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        updateB.setText("Update");
        updateB.setFocusable(false);
        updateB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBActionPerformed(evt);
            }
        });
        jToolBar1.add(updateB);

        deleteB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        deleteB.setText("Delete");
        deleteB.setFocusable(false);
        deleteB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteB);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBActionPerformed
        if (message.isEditable()) {
            m.setText(message.getText());
            //update mesage pop??

            new MessageDAO().update(m.getId(), m.getText());
            
            message.setEditable(false);
            
           
            
        } else {
            message.setEditable(true);
            
        }
        
       


    }//GEN-LAST:event_updateBActionPerformed

    private void deleteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBActionPerformed

       new MessageDAO().removeById(m.getId());
       p.setMessages();

    }//GEN-LAST:event_deleteBActionPerformed

    private void messageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageMouseClicked

    }//GEN-LAST:event_messageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteB;
    private javax.swing.JLabel firstname;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextArea message;
    private javax.swing.JLabel pic;
    private javax.swing.JButton updateB;
    // End of variables declaration//GEN-END:variables
}
