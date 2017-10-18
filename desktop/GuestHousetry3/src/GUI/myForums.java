/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Panels.ForumCreate;
import GUI.Panels.OldDiscussion;
import GUI.Panels.myForum;
import Jobs.ForumJob;
import Jobs.OldDiscussions;
import entities.Forum;
import entitiesDao.ForumDAO;
import java.awt.Label;
import java.util.List;
import javax.swing.JToolBar;

/**
 *
 * @author Mohamed
 */
public class myForums extends GuestHouse {
    
    private ForumJob fm;
    private OldDiscussions od;
    List<Forum> l;
    
    public myForums() {
        
        fm = new ForumJob(u);
        od = new OldDiscussions(u);
        initComponents();
        odb.setVisible(false);
        
        setMyForums();
        setOldDiscussions();
    }
    
    public void setOldDiscussions() {
        od = new OldDiscussions(u);
        oldDis.removeAll();
        u.addBannedFromForums();
        for (Forum f : od.getLf()) {
            if (!u.getBannedFrom().contains(f)) {
                oldDis.add(new OldDiscussion(f, u, this));
            }
        }
        oldDis.updateUI();
        oldDis.repaint();
        
    }
    
    public JToolBar getMyForumsBar() {
        return myForumsBar;
    }
    
    public void setMyForumsBar(JToolBar myForumsBar) {
        this.myForumsBar = myForumsBar;
    }
    
    public void setMyForums() {
        myForumsBar.removeAll();
        fm = new ForumJob(u);//refrech
        for (Forum object : fm.getLf()) {
            myForumsBar.add(new myForum(object, this));
        }
        myForumsBar.add(new Label("   "));
        myForumsBar.add(new Label("   "));
        
        myForumsBar.add(new ForumCreate(u));
        myForumsBar.updateUI();
        myForumsBar.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        myForumsBar = new javax.swing.JToolBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        oldDis = new javax.swing.JToolBar();
        keyWords = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        oldDisc = new javax.swing.JLabel();
        odb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        myForumsBar.setBackground(new java.awt.Color(255, 255, 255));
        myForumsBar.setFloatable(false);
        myForumsBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        myForumsBar.setRollover(true);
        jScrollPane1.setViewportView(myForumsBar);

        oldDis.setBackground(new java.awt.Color(255, 255, 255));
        oldDis.setFloatable(false);
        oldDis.setOrientation(javax.swing.SwingConstants.VERTICAL);
        oldDis.setRollover(true);
        jScrollPane2.setViewportView(oldDis);

        keyWords.setText("Type key words here ...");
        keyWords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keyWordsMouseClicked(evt);
            }
        });
        keyWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyWordsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("My Forums :");

        oldDisc.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        oldDisc.setForeground(new java.awt.Color(0, 0, 153));
        oldDisc.setText("Old Discussions :");

        odb.setText("Old Discussions");
        odb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(439, 439, 439)
                .addComponent(keyWords, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(oldDisc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(odb)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyWords, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oldDisc)
                    .addComponent(odb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void keyWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyWordsActionPerformed
        u.addBannedFromForums();
        l = new ForumDAO().findbyKeyWord(keyWords.getText());
        oldDis.removeAll();
        oldDisc.setText(keyWords.getText() + " :");
        for (Forum f : l) {
            if (!u.getBannedFrom().contains(f)) {
                oldDis.add(new OldDiscussion(f, u, this));
            }
        }
        oldDis.updateUI();
        oldDis.repaint();
        odb.setVisible(true);
    }//GEN-LAST:event_keyWordsActionPerformed

    private void keyWordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keyWordsMouseClicked
        keyWords.setText("");
    }//GEN-LAST:event_keyWordsMouseClicked

    private void odbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odbActionPerformed
        
        oldDisc.setText("Old Discussions :");
        oldDis.removeAll();
        
        setOldDiscussions();
        keyWords.setText("Type your key words here...");
        oldDis.updateUI();
        oldDis.repaint();
        
        odb.setVisible(false);
        

    }//GEN-LAST:event_odbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField keyWords;
    private javax.swing.JToolBar myForumsBar;
    private javax.swing.JButton odb;
    private javax.swing.JToolBar oldDis;
    private javax.swing.JLabel oldDisc;
    // End of variables declaration//GEN-END:variables
}
