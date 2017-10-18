/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Dialogs.logIn;
import GUI.Panels.AddAvailableDatePanel;
import GUI.Panels.AddComment;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import entities.Add;
import entities.Comment;
import entities.HostingDemande;
import entities.Notification;
import entities.User;
import entitiesDao.AddDAO;
import entitiesDao.HostingDemandeDAO;
import entitiesDao.NotificationDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;
import javax.swing.ImageIcon;
import utils.SMS;

/**
 *
 * @author dell
 */
public class viewAdd extends GuestHouse {

    private User u1;
    private Add a;

    public viewAdd(Add a) {
        super();
        initComponents();
        this.a = a;

        if (u != null) {
            back.setVisible(false);
        }

        u1 = new AddDAO().findOwnerById(a.getId());
        if (u1.getPicture() != null) {
            ppic.setIcon(new ImageIcon(u1.getPicture().getScaledInstance(150, 150, 4)));
        }

        owner.setText(u1.getFirstName() + " " + u1.getLastName());
        adress.setText(a.getCountry() + ", " + a.getTown());
        price.setText("" + a.getPrice());
        nature.setText(a.getNature());

        if (a.getPicture1() != null) {
            pic1.setIcon(new ImageIcon(a.getPicture1().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        }
        if (a.getPicture2() != null) {
            pic2.setIcon(new ImageIcon(a.getPicture2().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        }
        if (a.getPicture3() != null) {
            pic3.setIcon(new ImageIcon(a.getPicture3().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        }

        String ch = "";

        if (a.isAirconditioner()) {
            ch += " Airconditioner ";
        }

        if (a.isBarbecue()) {
            ch += " barbebue ";
        }

        if (a.isChildrengames()) {
            ch += " Childrengames ";
        }

        if (a.isDishwasher()) {
            ch += " Dishwasher ";
        }

        if (a.isDvd()) {
            ch += " Dvd ";
        }

        if (a.isElevator()) {
            ch += " Elevator ";
        }

        if (a.isFireplace()) {
            ch += " Fireplace ";
        }

        if (a.isGarden()) {
            ch += " Garden ";
        }

        if (a.isGardenfurniture()) {
            ch += " Gardenfurniture ";
        }

        if (a.isIndoorparking()) {
            ch += " Indoorparking ";
        }

        if (a.isIndoorpool()) {
            ch += " Indoorpool ";
        }

        if (a.isJacuzzi()) {
            ch += " Jacuzzi ";
        }

        if (a.isOutdoorparking()) {
            ch += " Outdoorparking ";
        }

        if (a.isSauna()) {
            ch += " Sauna ";
        }

        if (a.isTv()) {
            ch += " Tv ";
        }

        if (a.isWashingmachine()) {
            ch += " Washingmachine ";
        }

        if (a.isTerrace()) {
            ch += " Terrace ";
        }
        //if(a!=null)
        //  ch+=a.getOther();

        equipement.setText(ch);

        description.setText(a.getDescription());
        mail.setText(u1.getEmail());
        phone.setText(
                "" + u1.getPhone());
        if (a.getRating()
                > 4) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRating()
                > 3) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRating()
                > 2) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRating()
                > 1) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRating()
                > 0) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        }
        setComments();

        for (Date d : a.getDates()) {
            dates.add(new AddAvailableDatePanel(d));
        }

       // setMap();

    }

    public void setMap() {
        String url = "https://www.google.tn/maps/place/" + a.getCountry() + "," + a.getTown();
        final Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        browser.loadURL(url);
        view.setSize(map.getWidth(),map.getHeight());
        map.add(view);
    }

    public void setComments() {
        if (u != null) {
            u.addReportedComments();
        }

        a.addComments();
        comments.removeAll();

        for (Map.Entry<Comment, User> en : a.getComments().entrySet()) {
            Comment key = en.getKey();
            User value = en.getValue();
            if (u != null) {
                if (!u.getReportedComments().contains(key)) {
                    comments.add(new AddComment(value, key, this));
                }
            } else {
                comments.add(new AddComment(value, key, this));
            }
        }
        comments.updateUI();
        comments.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ppic = new javax.swing.JLabel();
        ownerLabel = new javax.swing.JLabel();
        adressLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        natureLabel = new javax.swing.JLabel();
        equipementLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pic1 = new javax.swing.JLabel();
        pic2 = new javax.swing.JLabel();
        pic3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        mailLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        bmessage = new javax.swing.JButton();
        bhosting = new javax.swing.JButton();
        owner = new javax.swing.JLabel();
        adress = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        nature = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        equipement = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        back = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        comments = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dates = new javax.swing.JToolBar();
        map = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N

        ownerLabel.setText("Owner: ");

        adressLabel.setText("Adress:");

        priceLabel.setText("Price: ");

        natureLabel.setText("Nature:");

        equipementLabel.setText("Equipement:");

        descriptionLabel.setText("Description:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Available on:");

        pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic1.setText("jLabel4");

        pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic2.setText("jLabel5");

        pic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic3.setText("jLabel6");

        jLabel7.setText("Owner contact:");

        mailLabel.setText("Mail: ");

        phoneLabel.setText("Phone:");

        jScrollPane3.setToolTipText("");

        message.setColumns(20);
        message.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        message.setRows(5);
        message.setText("Type a message here ...");
        message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(message);

        bmessage.setText("Send as a message");
        bmessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmessageActionPerformed(evt);
            }
        });

        bhosting.setText("Send as a hosting");
        bhosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhostingActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        jToolBar1.add(star1);

        star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        jToolBar1.add(star2);

        star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        jToolBar1.add(star3);

        star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        jToolBar1.add(star4);

        star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        jToolBar1.add(star5);

        equipement.setEditable(false);
        equipement.setColumns(20);
        equipement.setRows(5);
        equipement.setAutoscrolls(false);
        jScrollPane1.setViewportView(equipement);

        description.setEditable(false);
        description.setColumns(20);
        description.setRows(5);
        description.setAutoscrolls(false);
        jScrollPane2.setViewportView(description);

        back.setText("<= BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        comments.setBackground(new java.awt.Color(255, 255, 255));
        comments.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comments.setFloatable(false);
        comments.setOrientation(javax.swing.SwingConstants.VERTICAL);
        comments.setRollover(true);
        jScrollPane5.setViewportView(comments);

        jLabel1.setText("Comments :");

        dates.setBackground(new java.awt.Color(255, 255, 255));
        dates.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        dates.setOrientation(javax.swing.SwingConstants.VERTICAL);
        dates.setRollover(true);
        jScrollPane4.setViewportView(dates);

        javax.swing.GroupLayout mapLayout = new javax.swing.GroupLayout(map);
        map.setLayout(mapLayout);
        mapLayout.setHorizontalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        mapLayout.setVerticalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(phoneLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(mailLabel)
                                        .addGap(27, 27, 27)
                                        .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bhosting, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(descriptionLabel)
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priceLabel)
                                    .addComponent(adressLabel)
                                    .addComponent(natureLabel)
                                    .addComponent(ownerLabel))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(owner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(adress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(equipementLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pic3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pic1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(back)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(9, 9, 9))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(pic1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pic3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pic2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ownerLabel)
                                            .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(adressLabel)
                                            .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(priceLabel)
                                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(natureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(11, 11, 11)
                                        .addComponent(equipementLabel))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(103, 103, 103)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(descriptionLabel)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mailLabel)
                            .addComponent(mail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneLabel)
                            .addComponent(phone)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(bmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bhosting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    private void messageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageMouseClicked
        message.setText("");
    }//GEN-LAST:event_messageMouseClicked

    private void bmessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmessageActionPerformed
        if (u != null) {
            new NotificationDAO().add(new Notification("New Message from " + u.getFirstName() + " " + u.getLastName() + ": '" + message.getText() + "'", new Date(Calendar.getInstance().getTimeInMillis()),null), u1.getId());
        } else {
            this.dispose();
            new logIn().setVisible(true);
        }
    }//GEN-LAST:event_bmessageActionPerformed

    private void bhostingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhostingActionPerformed
        if (u != null) {
            //SMS.sendSMS("you have a new Hosting Demande check it out !!", u1.getPhone());
            new HostingDemandeDAO().add(new HostingDemande(message.getText(), a.getHouse()), u.getId(), u1.getId());
        } else {
            this.dispose();
            new logIn().setVisible(true);
        }
    }//GEN-LAST:event_bhostingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adress;
    private javax.swing.JLabel adressLabel;
    private javax.swing.JButton back;
    private javax.swing.JButton bhosting;
    private javax.swing.JButton bmessage;
    private javax.swing.JToolBar comments;
    private javax.swing.JToolBar dates;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea equipement;
    private javax.swing.JLabel equipementLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel mailLabel;
    private javax.swing.JPanel map;
    private javax.swing.JTextArea message;
    private javax.swing.JLabel nature;
    private javax.swing.JLabel natureLabel;
    private javax.swing.JLabel owner;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel pic1;
    private javax.swing.JLabel pic2;
    private javax.swing.JLabel pic3;
    private javax.swing.JLabel ppic;
    private javax.swing.JLabel price;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JLabel star4;
    private javax.swing.JLabel star5;
    // End of variables declaration//GEN-END:variables
}
