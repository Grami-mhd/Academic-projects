/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Panels.forumMessage;
import GUI.Panels.myMessageForum;
import entities.Forum;
import entities.Message;
import entities.User;
import entitiesDao.ForumReportDAO;
import entitiesDao.MessageDAO;
import java.util.Map;

/**
 *
 * @author asus
 */
public class forum extends GuestHouse {

    private Message m1;
    private Forum f;

  

    
    public forum(Forum f) {

        this.f = f;
        this.f.setMessages();
       
       
        initComponents();
        forumSub.setText(this.f.getSubject());
       

    }

    public User getU() {
        return u;
    }

    public void setMessages() {

        u.addBannedFromForums();
        if (u.getBannedFrom().contains(f)) {
            new home().setVisible(true);
            this.dispose();
            this.dispose();
        } else {
            messages.removeAll();
            u.addMessages();
            u.addReportedMessages();
            f.setMessages();
            for (Map.Entry<Message, User> en : f.getMessages().entrySet()) {
                Message m = en.getKey();
                User user = en.getValue();
                if (!u.getReportedMessages().contains(m)) {
                    if (u.getMyMessages().containsKey(m)) {
                        messages.add(new myMessageForum(m, user, this));
                    } else {
                        messages.add(new forumMessage(user, m, this));
                    }
                }

            }
            messages.updateUI();
            messages.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        reportForum = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        forumSub = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        messages = new javax.swing.JToolBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        msgToPost = new javax.swing.JTextArea();
        postMessageB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Subject :");

        reportForum.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reportForum.setForeground(new java.awt.Color(255, 0, 0));
        reportForum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reportForum.setText("Report");
        reportForum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportForumMouseClicked(evt);
            }
        });

        forumSub.setEditable(false);
        forumSub.setColumns(20);
        forumSub.setRows(5);
        jScrollPane1.setViewportView(forumSub);

        messages.setBackground(new java.awt.Color(255, 255, 255));
        messages.setFloatable(false);
        messages.setOrientation(javax.swing.SwingConstants.VERTICAL);
        messages.setRollover(true);
        messages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messagesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(messages);

        msgToPost.setColumns(20);
        msgToPost.setRows(5);
        jScrollPane3.setViewportView(msgToPost);

        postMessageB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        postMessageB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/postMessage.png"))); // NOI18N
        postMessageB.setText("Post message");
        postMessageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postMessageBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportForum, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postMessageB))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(reportForum, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(postMessageB)))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reportForumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportForumMouseClicked

        new ForumReportDAO().add(f.getId(), u.getId());


    }//GEN-LAST:event_reportForumMouseClicked

    private void postMessageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postMessageBActionPerformed

        MessageDAO mdao = new MessageDAO();
        m1 = new Message(msgToPost.getText());
        mdao.add(m1, f.getId(), u.getId());

        setMessages();
        msgToPost.setText("");

    }//GEN-LAST:event_postMessageBActionPerformed

    private void messagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messagesMouseClicked
       setMessages();
    }//GEN-LAST:event_messagesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea forumSub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar messages;
    private javax.swing.JTextArea msgToPost;
    private javax.swing.JButton postMessageB;
    private javax.swing.JButton reportForum;
    // End of variables declaration//GEN-END:variables
}
