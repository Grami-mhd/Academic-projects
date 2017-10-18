/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Dialogs.web;
import entities.Comment;
import entities.House;
import entities.Rate;
import entities.User;
import entitiesDao.CommentDAO;
import entitiesDao.RateDAO;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public class RateStay extends GuestHouse {

    
    private int page = 0;

    public RateStay() {
        super();
        initComponents();

        if (u.getPicture() != null) {
            profilePicture.setIcon(new javax.swing.ImageIcon(u.getPicture().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH)));
        }

        userProfile.setText(u.getFirstName() + " " + u.getLastName());
        points.setText("" + u.getPoints());
        System.out.println("U1 is not null ;) ");
        u.addStays();
        if (!u.getMyStays().isEmpty()) {
            goHome.setVisible(false);
            setHouse(u.getMyStays().get(0));
        } else {
            body.setVisible(false);

        }
        if (u.getPicture() != null) {
            ppic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(150, 150, 4)));
        }

    }

    public void setHouse(House a) {
        owner.setText(u.getFirstName() + " " + u.getLastName());
        adress.setText(a.getCountry() + ", " + a.getTown());
        price.setText("" + a.getPrice());
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
        equipement.setText(ch);
        description.setText(a.getDescription());
        starWars(a);
        pagelab.setText(page + 1 + "/" + u.getMyStays().size());
        if (u.getMyRates() != null) {
            if (u.getMyRates().keySet().contains(a)) {
                setMyRate(u.getMyRates().get(a));
            } else {
                setMyRate(new Rate(0));
            }
        }
    }

    public void setMyRate(Rate a) {
        if (a.getRate() > 4) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRate() > 3) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRate() > 2) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRate() > 1) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRate() > 0) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));

        } else if (a.getRate() == 0) {
            star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        }
    }

    public void starWars(House a) {

        if (a.getRating() > 4) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
        } else if (a.getRating() > 3) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRating() > 2) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRating() > 1) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRating() > 0) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        } else if (a.getRating() == 0) {
            Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
            Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userMenuBar = new javax.swing.JToolBar();
        image = new javax.swing.JLabel();
        profilePicture = new javax.swing.JLabel();
        userProfile = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        forums = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        claims = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        points = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        sell = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        buy = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        goHome = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        ppic = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        owner = new javax.swing.JLabel();
        adress = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stayComment = new javax.swing.JTextArea();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        equipement = new javax.swing.JTextArea();
        Star1 = new javax.swing.JLabel();
        Star2 = new javax.swing.JLabel();
        Star3 = new javax.swing.JLabel();
        Star4 = new javax.swing.JLabel();
        Star5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pagelab = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        userMenuBar.setBackground(new java.awt.Color(204, 204, 204));
        userMenuBar.setFloatable(false);
        userMenuBar.setForeground(new java.awt.Color(0, 102, 102));
        userMenuBar.setRollover(true);
        userMenuBar.add(image);

        profilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        profilePicture.setText(" ");
        profilePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePictureMouseClicked(evt);
            }
        });
        userMenuBar.add(profilePicture);

        userProfile.setBackground(new java.awt.Color(102, 102, 102));
        userProfile.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        userProfile.setForeground(new java.awt.Color(204, 204, 204));
        userProfile.setText("user profile  ");
        userProfile.setFocusable(false);
        userProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userProfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userProfileActionPerformed(evt);
            }
        });
        userMenuBar.add(userProfile);

        jLabel19.setText("            ");
        userMenuBar.add(jLabel19);

        forums.setBackground(new java.awt.Color(255, 255, 255));
        forums.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        forums.setText("forums  ");
        forums.setFocusable(false);
        forums.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forums.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        forums.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forumsActionPerformed(evt);
            }
        });
        userMenuBar.add(forums);

        jLabel20.setText("     ");
        userMenuBar.add(jLabel20);

        claims.setBackground(new java.awt.Color(255, 255, 255));
        claims.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        claims.setText("claims  ");
        claims.setFocusable(false);
        claims.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        claims.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        claims.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimsActionPerformed(evt);
            }
        });
        userMenuBar.add(claims);

        jLabel21.setText("                  ");
        userMenuBar.add(jLabel21);

        jLabel22.setText("points = ");
        userMenuBar.add(jLabel22);

        points.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        points.setText("450");
        userMenuBar.add(points);

        jLabel23.setText("              ");
        userMenuBar.add(jLabel23);

        sell.setBackground(new java.awt.Color(255, 255, 255));
        sell.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        sell.setText("  Sell");
        sell.setFocusable(false);
        sell.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sell.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellActionPerformed(evt);
            }
        });
        userMenuBar.add(sell);

        jLabel24.setText("  ");
        userMenuBar.add(jLabel24);

        buy.setBackground(new java.awt.Color(255, 255, 255));
        buy.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        buy.setText("  Buy");
        buy.setFocusable(false);
        buy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyActionPerformed(evt);
            }
        });
        userMenuBar.add(buy);

        jLabel25.setText("        ");
        userMenuBar.add(jLabel25);

        jButton5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton5.setText("     Home    ");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        userMenuBar.add(jButton5);

        back.setText("<= BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        goHome.setText("you have no stays, Go home and search for one if you want");
        goHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N
        ppic.setText("jLabel1");

        jLabel2.setText("Owner :");

        jLabel3.setText("Adress :");

        jLabel4.setText("Price :");

        jLabel5.setText("Equipement :");

        jLabel6.setText("Description :");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Welcome back :");

        jLabel8.setText("Have you enjoyed your stay !!!");

        jLabel9.setText("Leave a note for others :");

        stayComment.setColumns(20);
        stayComment.setRows(5);
        jScrollPane1.setViewportView(stayComment);

        star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        star1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                star1MouseClicked(evt);
            }
        });

        star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        star2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                star2MouseClicked(evt);
            }
        });

        star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        star3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                star3MouseClicked(evt);
            }
        });

        star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        star4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                star4MouseClicked(evt);
            }
        });

        star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N
        star5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                star5MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N

        previous.setText("<<     Previous");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setText("Next   >>");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        jButton4.setText("Confirm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        description.setColumns(20);
        description.setRows(5);
        jScrollPane2.setViewportView(description);

        equipement.setColumns(20);
        equipement.setRows(5);
        jScrollPane3.setViewportView(equipement);

        Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        jLabel10.setText("Rating :");

        pagelab.setText("jLabel12");

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Claim about this stay ?");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(9, 9, 9)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(128, 128, 128)
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(price, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(adress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(owner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2)))
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addComponent(Star1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Star2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Star3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Star4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Star5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addGap(224, 224, 224)
                                        .addComponent(pagelab)))))
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addGroup(bodyLayout.createSequentialGroup()
                                                .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(star5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                        .addComponent(previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(ppic)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(adress, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                                        .addGap(14, 14, 14)
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(bodyLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel5)))
                                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bodyLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(bodyLayout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabel6)))
                                        .addGap(28, 28, 28))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)))
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Star2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Star3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Star4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Star5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Star1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bodyLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(star5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(jLabel1))
                .addGap(65, 65, 65)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(previous)
                    .addComponent(pagelab)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(248, 248, 248)
                    .addComponent(goHome, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(249, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(302, 302, 302)
                    .addComponent(goHome)
                    .addContainerGap(303, Short.MAX_VALUE)))
        );

        goHome.getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profilePictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePictureMouseClicked

    }//GEN-LAST:event_profilePictureMouseClicked

    private void userProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userProfileActionPerformed

    }//GEN-LAST:event_userProfileActionPerformed

    private void forumsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forumsActionPerformed

    }//GEN-LAST:event_forumsActionPerformed

    private void claimsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimsActionPerformed

    }//GEN-LAST:event_claimsActionPerformed

    private void sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellActionPerformed

    }//GEN-LAST:event_sellActionPerformed

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed

    }//GEN-LAST:event_buyActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

    }//GEN-LAST:event_backActionPerformed

    private void star1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_star1MouseClicked
        if (u.getMyRates().keySet().contains(u.getMyStays().get(page))) {
            Rate k = new Rate(u.getMyRates().get(u.getMyStays().get(page)).getId(), 1);

            new RateDAO().update(k);
        } else {
            new RateDAO().add(new Rate(1), u.getMyStays().get(page).getId(), u.getId());
        }
        u.addStays();
        u.addRates();
        starWars(u.getMyStays().get(page));
        setMyRate(u.getMyRates().get(u.getMyStays().get(page)));
    }//GEN-LAST:event_star1MouseClicked

    private void star2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_star2MouseClicked
        if (u.getMyRates().keySet().contains(u.getMyStays().get(page))) {

            Rate k = new Rate(u.getMyRates().get(u.getMyStays().get(page)).getId(), 2);

            new RateDAO().update(k);
        } else {
            new RateDAO().add(new Rate(2), u.getMyStays().get(page).getId(), u.getId());
        }
        u.addStays();
        u.addRates();
        starWars(u.getMyStays().get(page));
        setMyRate(u.getMyRates().get(u.getMyStays().get(page)));
    }//GEN-LAST:event_star2MouseClicked

    private void star3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_star3MouseClicked
        if (u.getMyRates().keySet().contains(u.getMyStays().get(page))) {
            Rate k = new Rate(u.getMyRates().get(u.getMyStays().get(page)).getId(), 3);

            new RateDAO().update(k);
        } else {
            new RateDAO().add(new Rate(3), u.getMyStays().get(page).getId(), u.getId());
        }
        u.addStays();
        u.addRates();
        starWars(u.getMyStays().get(page));
        setMyRate(u.getMyRates().get(u.getMyStays().get(page)));


    }//GEN-LAST:event_star3MouseClicked

    private void star4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_star4MouseClicked
        if (u.getMyRates().keySet().contains(u.getMyStays().get(page))) {
            // System.out.println(u.getMyRates().keySet().contains(u.getMyStays().get(page)));
            Rate k = new Rate(u.getMyRates().get(u.getMyStays().get(page)).getId(), 4);

            new RateDAO().update(k);
        } else {
            new RateDAO().add(new Rate(4), u.getMyStays().get(page).getId(), u.getId());
        }
        u.addStays();
        u.addRates();
        starWars(u.getMyStays().get(page));
        setMyRate(u.getMyRates().get(u.getMyStays().get(page)));

    }//GEN-LAST:event_star4MouseClicked

    private void star5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_star5MouseClicked
        if (u.getMyRates().keySet().contains(u.getMyStays().get(page))) {
            Rate k = new Rate(u.getMyRates().get(u.getMyStays().get(page)).getId(), 5);

            new RateDAO().update(k);
        } else {
            new RateDAO().add(new Rate(5), u.getMyStays().get(page).getId(), u.getId());
        }
        u.addStays();
        u.addRates();
        starWars(u.getMyStays().get(page));
        setMyRate(u.getMyRates().get(u.getMyStays().get(page)));

    }//GEN-LAST:event_star5MouseClicked

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if (!u.getMyStays().isEmpty()) {
            if (page == 0) {
                page = u.getMyStays().size() - 1;
            } else {
                page--;
            }
            setHouse(u.getMyStays().get(page));
        }
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (!u.getMyStays().isEmpty()) {
            if (page == u.getMyStays().size() - 1) {
                page = 0;
            } else {
                page++;
            }
            setHouse(u.getMyStays().get(page));
        }
    }//GEN-LAST:event_nextActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        new CommentDAO().add(new Comment(stayComment.getText()),u.getMyStays().get(page).getId() ,u.getId() );
        stayComment.setText("");
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       new MenageClaims(u.getMyStays().get(page)).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Star1;
    private javax.swing.JLabel Star2;
    private javax.swing.JLabel Star3;
    private javax.swing.JLabel Star4;
    private javax.swing.JLabel Star5;
    private javax.swing.JLabel adress;
    private javax.swing.JButton back;
    private javax.swing.JPanel body;
    private javax.swing.JButton buy;
    private javax.swing.JButton claims;
    private javax.swing.JTextArea description;
    private javax.swing.JTextArea equipement;
    private javax.swing.JButton forums;
    private javax.swing.JLabel goHome;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton next;
    private javax.swing.JLabel owner;
    private javax.swing.JLabel pagelab;
    private javax.swing.JLabel points;
    private javax.swing.JLabel ppic;
    private javax.swing.JButton previous;
    private javax.swing.JLabel price;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JButton sell;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JLabel star4;
    private javax.swing.JLabel star5;
    private javax.swing.JTextArea stayComment;
    private javax.swing.JToolBar userMenuBar;
    private javax.swing.JButton userProfile;
    // End of variables declaration//GEN-END:variables
}
