/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import GUI.Dialogs.finishTheReservation;
import java.sql.Date;

public class availableDate extends javax.swing.JPanel {

    private finishTheReservation p;
    private Date d;

    public availableDate(finishTheReservation p, Date d, boolean b) {
        initComponents();
        this.p = p;
        this.d = d;
        if (b) {
            date.setBackground(new java.awt.Color(102, 255, 0));
        }

        date.setText(d.toString());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        date.setBackground(new java.awt.Color(255, 255, 255));
        date.setFont(new java.awt.Font("Segoe Print", 3, 11)); // NOI18N
        date.setForeground(new java.awt.Color(255, 0, 0));
        date.setText("jButton1");
        date.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed

        if (p.getDates().contains(d)) {
            p.deleteDate(d);
            
        } else {
            p.addDate(d);
        }
    }//GEN-LAST:event_dateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton date;
    // End of variables declaration//GEN-END:variables
}
