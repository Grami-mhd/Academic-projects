/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialogs;

import GUI.GuestHouse;
import GUI.Panels.hostingDemand;
import entities.HostingDemande;
import entities.User;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class viewHostingDemands extends javax.swing.JDialog {

    /**
     * Creates new form viewHostingDemands
     */
    private Map<HostingDemande, User> hd;
    private User u;

    public viewHostingDemands(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.u = GuestHouse.u;

        u.addHostingDemands();

        setHostingDemands();

    }

    public void setHostingDemands() {

        hostDemand.removeAll();
        u.addHostingDemands();
        hd = u.getHostingDemands();
        for (Map.Entry<HostingDemande, User> entry : hd.entrySet()) {
            HostingDemande h = entry.getKey();
            User user = entry.getValue();
            hostDemand.add(new hostingDemand(user, h, this));

        }

        hostDemand.updateUI();
        hostDemand.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        hostDemand = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        hostDemand.setBackground(new java.awt.Color(255, 255, 255));
        hostDemand.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hosting Demands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 3, 14), new java.awt.Color(51, 153, 0))); // NOI18N
        hostDemand.setFloatable(false);
        hostDemand.setOrientation(javax.swing.SwingConstants.VERTICAL);
        hostDemand.setRollover(true);
        jScrollPane2.setViewportView(hostDemand);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar hostDemand;
    private javax.swing.JScrollPane jScrollPane2;
    
}
