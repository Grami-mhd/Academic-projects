package GUI.Panels;

import GUI.ViewAddAsOwner;
import GUI.ViewProfil;
import entities.Add;
import entities.User;
import entitiesDao.HouseDAO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author grami
 */
public class HousePanel1 extends javax.swing.JPanel {

    private final Add p;

    private final ViewProfil parent;
    
    public HousePanel1(Add p,ViewProfil u1) {
        this.p = p;
        parent=u1;
        initComponents();
        starWars();
        User u = new HouseDAO().findOwnerById(p.getId());
        //owner.setText(u.getFirstName() + " " + u.getLastName());
        if(p.getPicture1()!=null)
                pic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(50,50, 4)));
        price.setText("" + p.getPrice());
        nature.setText(p.getNature());
        adress.setText(p.getCountry() + " " + p.getTown());
        if (p.getPicture1() != null) {        
            pic.setIcon(new ImageIcon(p.getPicture1().getScaledInstance(150, 150,java.awt.Image.SCALE_SMOOTH)));
        }
    }
    public void starWars(){
        
        if (p.getRating()
                > 4) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (p.getRating()
                > 3) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (p.getRating()
                > 2) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (p.getRating()
                > 1) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (p.getRating()
                > 0) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        pic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        adress = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        nature = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        viewhouse = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        pic.setBackground(new java.awt.Color(255, 255, 255));
        pic.setForeground(new java.awt.Color(255, 255, 255));
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        jToolBar1.add(pic);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("adress :");

        jLabel5.setText("price :");

        jLabel6.setText("nature :");

        adress.setText("adress here");

        price.setText("xxxpt");

        nature.setText("house");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        star1.setBackground(new java.awt.Color(255, 255, 255));
        star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star2.setBackground(new java.awt.Color(255, 255, 255));
        star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star3.setBackground(new java.awt.Color(255, 255, 255));
        star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star4.setBackground(new java.awt.Color(255, 255, 255));
        star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star5.setBackground(new java.awt.Color(255, 255, 255));
        star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(star1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(star2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(star3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(star4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(star5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(star1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(star5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(star4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(star3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(star2, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adress))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(price))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nature)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(adress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nature))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jToolBar1.add(jPanel1);

        viewhouse.setBackground(new java.awt.Color(153, 153, 153));
        viewhouse.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        viewhouse.setForeground(new java.awt.Color(51, 51, 51));
        viewhouse.setText("view House");
        viewhouse.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 4, true));
        viewhouse.setFocusable(false);
        viewhouse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewhouse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewhouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewhouseActionPerformed(evt);
            }
        });
        jToolBar1.add(viewhouse);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewhouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewhouseActionPerformed
        new ViewAddAsOwner(p).setVisible(true);
        parent.dispose();

    }//GEN-LAST:event_viewhouseActionPerformed


    public JButton getViewAdd() {
        return viewhouse;
    }

    public void setViewAdd(JButton viewhouse) {
        this.viewhouse = viewhouse;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adress;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel nature;
    private javax.swing.JLabel pic;
    private javax.swing.JLabel price;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JLabel star4;
    private javax.swing.JLabel star5;
    private javax.swing.JButton viewhouse;
    // End of variables declaration//GEN-END:variables
}
