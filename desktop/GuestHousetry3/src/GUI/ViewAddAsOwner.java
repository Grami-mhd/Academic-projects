/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Panels.AddAvailableDatePanel;
import GUI.Panels.AddCommentAsOwner;
import Jobs.CommentsByHouse;
import Jobs.PictureHome;
import entities.Add;
import entities.Comment;
import entities.User;
import entitiesDao.AddDAO;
import entitiesDao.HouseDAO;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import utils.ExtensionFileFilter;

/**
 *
 * @author ROGUE_BIBO
 */
public class ViewAddAsOwner extends GuestHouse {

    private CommentsByHouse x;
    private DateFormat df = DateFormat.getDateInstance();
    private Add a;

    public ViewAddAsOwner(Add ab) {
        super();
        initComponents();

        a = ab;
        a.addComments();
        setComments();
        afficheDate();
        u = new HouseDAO().findOwnerById(a.getId());

        if (u.getPicture() != null) {
            ppic.setIcon(new ImageIcon(u.getPicture().getScaledInstance(150, 250, 4)));
        }

        date.setMinSelectableDate(new Date(Calendar.getInstance().getTimeInMillis()));

        if (u != null) {
            owner1.setText(u.getLastName() + " " + u.getFirstName());
            town.setSelectedItem(a.getTown());
            price.setValue(a.getPrice());
            description.setText(a.getDescription());
            if (a.getNature().equals("room")) {
                room.setSelected(true);
            }
            if (a.getNature().equals("house")) {
                house.setSelected(true);
            }
            if (a.getNature().equals("appartment")) {
                appartment.setSelected(true);
            }
            if (a.getNature().equals("other")) {
                other.setSelected(true);
            }

            if (a.isAirconditioner()) {
                airConditioner2.setSelected(true);
            }
            if (a.isFireplace()) {
                firePlace2.setSelected(true);
            }
            if (a.isWashingmachine()) {
                washingMachine2.setSelected(true);
            }
            if (a.isDishwasher()) {
                dishWasher2.setSelected(true);
            }
            if (a.isDvd()) {
                dvd2.setSelected(true);
            }
            if (a.isDvd()) {
                dvd2.setSelected(true);
            }
            if (a.isIndoorpool()) {
                indoorPool2.setSelected(true);
            }

            if (a.isSauna()) {
                sauna2.setSelected(true);
            }

            if (a.isJacuzzi()) {
                jacuzzi2.setSelected(true);
            }
            if (a.isTv()) {
                tv2.setSelected(true);
            }
            if (a.isElevator()) {
                elevator2.setSelected(true);
            }
            if (a.isElevator()) {
                elevator2.setSelected(true);
            }
            if (a.isBarbecue()) {
                barbecue2.setSelected(true);
            }
            if (a.isGarden()) {
                garden2.setSelected(true);
            }
            if (a.isGardenfurniture()) {
                gardenForniture2.setSelected(true);
            }
            if (a.isChildrengames()) {
                childrenGames2.setSelected(true);
            }
            if (a.isTerrace()) {
                terrace2.setSelected(true);
            }
            if (a.isIndoorparking()) {
                indoorParking2.setSelected(true);
            }
            if (a.isIndoorparking()) {
                indoorParking2.setSelected(true);
            }
            if (a.isOutdoorparking()) {
                outdoorParking2.setSelected(true);
            }

            other.setText(a.getOther());

            if (a.getRating() > 4) {
                star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            } else if (a.getRating() > 3) {
                star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            } else if (a.getRating() > 2) {
                star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            } else if (a.getRating() > 1) {
                star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
                star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            } else if (a.getRating() == 1) {
                star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starSelected.png")));
            }

        }

    }

    public void afficheDate() {
        AncienDate.removeAll();
        a.setDates();
        if (a.getDates() != null) {
            for (Date d : a.getDates()) {
                AncienDate.add(new AddAvailableDatePanel(d));
            }
        }
        AncienDate.updateUI();
        AncienDate.repaint();
    }

    public void showPictures(PictureHome p) {

    }

    public void setComments() {
        if (!a.getComments().isEmpty()) {

            for (Map.Entry<Comment, User> en : a.getComments().entrySet()) {
                Comment key = en.getKey();
                User value = en.getValue();
                Comments.add(new AddCommentAsOwner(value, key, this));
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        ppic = new javax.swing.JLabel();
        owner = new javax.swing.JLabel();
        adress = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jToolBar4 = new javax.swing.JToolBar();
        owner1 = new javax.swing.JTextField();
        jToolBar6 = new javax.swing.JToolBar();
        jComboBox1 = new javax.swing.JComboBox<>();
        town = new javax.swing.JComboBox<>();
        price = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        house = new javax.swing.JRadioButton();
        appartment = new javax.swing.JRadioButton();
        room = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        equipmentPanel2 = new javax.swing.JPanel();
        airConditioner2 = new javax.swing.JCheckBox();
        firePlace2 = new javax.swing.JCheckBox();
        washingMachine2 = new javax.swing.JCheckBox();
        dishWasher2 = new javax.swing.JCheckBox();
        dvd2 = new javax.swing.JCheckBox();
        indoorPool2 = new javax.swing.JCheckBox();
        sauna2 = new javax.swing.JCheckBox();
        jacuzzi2 = new javax.swing.JCheckBox();
        tv2 = new javax.swing.JCheckBox();
        elevator2 = new javax.swing.JCheckBox();
        barbecue2 = new javax.swing.JCheckBox();
        garden2 = new javax.swing.JCheckBox();
        gardenForniture2 = new javax.swing.JCheckBox();
        childrenGames2 = new javax.swing.JCheckBox();
        terrace2 = new javax.swing.JCheckBox();
        indoorParking2 = new javax.swing.JCheckBox();
        outdoorParking2 = new javax.swing.JCheckBox();
        equipmentOther2 = new javax.swing.JTextField();
        date = new com.toedter.calendar.JDateChooser();
        Confirm = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        pic1 = new javax.swing.JLabel();
        pic2 = new javax.swing.JLabel();
        pic3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Comments = new javax.swing.JToolBar();
        update = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        AncienDate = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N

        owner.setText("Owner :");

        adress.setText("Adress :");

        jLabel5.setText("Price :");

        jLabel6.setText("Nature :");

        jLabel8.setText("Description :");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Set Availibility");

        star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star.png"))); // NOI18N

        description.setColumns(20);
        description.setRows(5);
        description.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descriptionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(description);

        jToolBar4.setFloatable(false);
        jToolBar4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar4.setRollover(true);

        owner1.setEditable(false);
        owner1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                owner1ActionPerformed(evt);
            }
        });
        jToolBar4.add(owner1);

        jToolBar6.setFloatable(false);
        jToolBar6.setRollover(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunisia" }));
        jToolBar6.add(jComboBox1);

        town.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soussa", "Monastir", "Mahdia", "Sfax", "Tunis", "Ben Arous", "Manouba", "Ariana", "Bizert", "Nabel", "Kef", "Gabes", "Mednine", "Tatawin", "Sidi Bou Zid", "Zaghouan", "Tozer", "Kairouan", "Beja", "Gasrine", "Gafsa", "Siliana", "Gbelli", "Jandouba" }));
        town.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                townActionPerformed(evt);
            }
        });
        jToolBar6.add(town);

        jToolBar4.add(jToolBar6);

        price.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        price.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        price.setEnabled(false);
        price.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                priceStateChanged(evt);
            }
        });
        jToolBar4.add(price);

        buttonGroup1.add(house);
        house.setText("House");
        house.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                houseActionPerformed(evt);
            }
        });

        buttonGroup1.add(appartment);
        appartment.setSelected(true);
        appartment.setText("Appartment");
        appartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appartmentActionPerformed(evt);
            }
        });

        buttonGroup1.add(room);
        room.setText("Room");
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });

        buttonGroup1.add(other);
        other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherActionPerformed(evt);
            }
        });

        jTextField1.setText("Other...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(other)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(room, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(house, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(appartment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(house)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(other, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar4.add(jPanel4);

        equipmentPanel2.setAutoscrolls(true);

        airConditioner2.setText("Air Conditioner");

        firePlace2.setText("fire place");

        washingMachine2.setText("Washing Machine");

        dishWasher2.setText("Dish Washer");

        dvd2.setText("DVD");

        indoorPool2.setText("Indoor Pool");
        indoorPool2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indoorPool2ActionPerformed(evt);
            }
        });

        sauna2.setText("Sauna");
        sauna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauna2ActionPerformed(evt);
            }
        });

        jacuzzi2.setText("Jacuzzi");
        jacuzzi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jacuzzi2ActionPerformed(evt);
            }
        });

        tv2.setText("TV");
        tv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tv2ActionPerformed(evt);
            }
        });

        elevator2.setText("Elevator");
        elevator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elevator2ActionPerformed(evt);
            }
        });

        barbecue2.setText("Barbecue");
        barbecue2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barbecue2ActionPerformed(evt);
            }
        });

        garden2.setText("Garden");
        garden2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garden2ActionPerformed(evt);
            }
        });

        gardenForniture2.setText("Garden Fourniture");
        gardenForniture2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gardenForniture2ActionPerformed(evt);
            }
        });

        childrenGames2.setText("Children Games");
        childrenGames2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childrenGames2ActionPerformed(evt);
            }
        });

        terrace2.setText("Terrace");

        indoorParking2.setText("Indoor Parking");

        outdoorParking2.setText("Outdoor Parking");
        outdoorParking2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outdoorParking2ActionPerformed(evt);
            }
        });

        equipmentOther2.setText("other...");
        equipmentOther2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentOther2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout equipmentPanel2Layout = new javax.swing.GroupLayout(equipmentPanel2);
        equipmentPanel2.setLayout(equipmentPanel2Layout);
        equipmentPanel2Layout.setHorizontalGroup(
            equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(airConditioner2)
                        .addComponent(firePlace2)
                        .addComponent(washingMachine2)
                        .addComponent(tv2)
                        .addComponent(elevator2)
                        .addComponent(barbecue2)
                        .addComponent(garden2)
                        .addComponent(gardenForniture2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(childrenGames2)
                        .addComponent(terrace2)
                        .addComponent(indoorParking2)
                        .addComponent(outdoorParking2)
                        .addComponent(equipmentOther2))
                    .addComponent(dishWasher2)
                    .addComponent(dvd2)
                    .addComponent(indoorPool2)
                    .addComponent(sauna2)
                    .addComponent(jacuzzi2))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        equipmentPanel2Layout.setVerticalGroup(
            equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(airConditioner2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firePlace2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(washingMachine2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dishWasher2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dvd2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indoorPool2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sauna2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jacuzzi2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elevator2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barbecue2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(garden2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gardenForniture2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(childrenGames2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terrace2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indoorParking2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outdoorParking2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentOther2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(equipmentPanel2);

        jToolBar4.add(jScrollPane1);

        Confirm.setText("Confirm Date");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pic1MouseClicked(evt);
            }
        });
        jToolBar1.add(pic1);

        pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pic2MouseClicked(evt);
            }
        });
        jToolBar1.add(pic2);

        pic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseicon.png"))); // NOI18N
        pic3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pic3MouseClicked(evt);
            }
        });
        jToolBar1.add(pic3);

        jScrollPane4.setViewportView(jToolBar1);

        Comments.setBackground(new java.awt.Color(255, 255, 255));
        Comments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Comments.setFloatable(false);
        Comments.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Comments.setRollover(true);
        jScrollPane5.setViewportView(Comments);

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        AncienDate.setBackground(new java.awt.Color(255, 255, 255));
        AncienDate.setOrientation(javax.swing.SwingConstants.VERTICAL);
        AncienDate.setRollover(true);
        jScrollPane3.setViewportView(AncienDate);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(adress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Confirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(35, 35, 35)
                                                        .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(3, 3, 3)
                                                        .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(star5)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                            .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(Confirm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void indoorPool2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indoorPool2ActionPerformed


    }//GEN-LAST:event_indoorPool2ActionPerformed

    private void sauna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauna2ActionPerformed


    }//GEN-LAST:event_sauna2ActionPerformed

    private void jacuzzi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jacuzzi2ActionPerformed


    }//GEN-LAST:event_jacuzzi2ActionPerformed

    private void tv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tv2ActionPerformed


    }//GEN-LAST:event_tv2ActionPerformed

    private void elevator2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elevator2ActionPerformed


    }//GEN-LAST:event_elevator2ActionPerformed

    private void barbecue2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barbecue2ActionPerformed


    }//GEN-LAST:event_barbecue2ActionPerformed

    private void garden2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garden2ActionPerformed


    }//GEN-LAST:event_garden2ActionPerformed

    private void gardenForniture2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gardenForniture2ActionPerformed


    }//GEN-LAST:event_gardenForniture2ActionPerformed

    private void childrenGames2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_childrenGames2ActionPerformed


    }//GEN-LAST:event_childrenGames2ActionPerformed

    private void outdoorParking2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outdoorParking2ActionPerformed


    }//GEN-LAST:event_outdoorParking2ActionPerformed

    private void equipmentOther2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentOther2ActionPerformed


    }//GEN-LAST:event_equipmentOther2ActionPerformed

    private void houseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_houseActionPerformed

    }//GEN-LAST:event_houseActionPerformed

    private void appartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appartmentActionPerformed

    }//GEN-LAST:event_appartmentActionPerformed

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed

    }//GEN-LAST:event_roomActionPerformed

    private void otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherActionPerformed

    }//GEN-LAST:event_otherActionPerformed

    private void owner1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_owner1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_owner1ActionPerformed

    private void townActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_townActionPerformed


    }//GEN-LAST:event_townActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        if (date.getDate() != null) {
            a.addDate(new Date(date.getDate().getTime()));
            System.out.println(new Date(date.getDate().getTime()));
            new AddDAO().update(a);
            afficheDate();
        }
    }//GEN-LAST:event_ConfirmActionPerformed

    private void priceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_priceStateChanged

    }//GEN-LAST:event_priceStateChanged

    private void descriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descriptionMouseClicked
        //description.setEditable(true);
    }//GEN-LAST:event_descriptionMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (town.isEditable()) {

            a.setTown((String) town.getSelectedItem());
            a.setPrice((int) price.getValue());
            if (airConditioner2.isSelected()) {
                a.setAirconditioner(true);
            }
            if (firePlace2.isSelected()) {
                a.setFireplace(true);
            }
            if (washingMachine2.isSelected()) {
                a.setWashingmachine(true);
            }
            if (dishWasher2.isSelected()) {
                a.setDishwasher(true);
            }
            if (dvd2.isSelected()) {
                a.setDvd(true);
            }
            if (indoorPool2.isSelected()) {
                a.setIndoorpool(true);
            }
            if (sauna2.isSelected()) {
                a.setSauna(true);
            }
            if (jacuzzi2.isSelected()) {
                a.setJacuzzi(true);
            }
            if (tv2.isSelected()) {
                a.setTv(true);
            }
            if (elevator2.isSelected()) {
                a.setElevator(true);
            }
            if (elevator2.isSelected()) {
                a.setElevator(true);
            }
            if (garden2.isSelected()) {
                a.setGarden(true);
            }
            if (gardenForniture2.isSelected()) {
                a.setGardenfurniture(true);
            }
            if (childrenGames2.isSelected()) {
                a.setChildrengames(true);
            }
            if (outdoorParking2.isSelected()) {
                a.setOutdoorparking(true);
            }
            if (equipmentOther2.getText() != null) {
                a.setOutdoorparking(true);
            }
            if (house.isSelected()) {
                a.setNature("house");
            }
            if (appartment.isSelected()) {
                a.setNature("appartment");

            }
            if (room.isSelected()) {
                a.setNature("room");

            }
            if (other.isSelected()) {

                a.setOther(other.getText());
            }

            a.setDescription(description.getText());
            new HouseDAO().update(a);
            //  new userProfilUpdated(this, true).setVisible(true);
            town.setEditable(false);
            price.setEnabled(false);
            appartment.setEnabled(false);
            room.setEnabled(false);
            house.setEnabled(false);
            other.setEnabled(false);

            firePlace2.setEnabled(false);
            washingMachine2.setEnabled(false);
            dishWasher2.setEnabled(false);
            dvd2.setEnabled(false);
            indoorPool2.setEnabled(false);
            sauna2.setEnabled(false);
            jacuzzi2.setEnabled(false);
            tv2.setEnabled(false);
            elevator2.setEnabled(false);
            garden2.setEnabled(false);
            gardenForniture2.setEnabled(false);
            childrenGames2.setEnabled(false);
            outdoorParking2.setEnabled(false);
            equipmentOther2.setEnabled(false);
            refrech();
        } else {
            town.setEditable(true);
            price.setEnabled(true);
            appartment.setEnabled(true);
            room.setEnabled(true);
            house.setEnabled(true);
            other.setEnabled(true);

            firePlace2.setEnabled(true);
            washingMachine2.setEnabled(true);
            dishWasher2.setEnabled(true);
            dvd2.setEnabled(true);
            indoorPool2.setEnabled(true);
            sauna2.setEnabled(true);
            jacuzzi2.setEnabled(true);
            tv2.setEnabled(true);
            elevator2.setEnabled(true);
            garden2.setEnabled(true);
            gardenForniture2.setEnabled(true);
            childrenGames2.setEnabled(true);
            outdoorParking2.setEnabled(true);
            equipmentOther2.setEnabled(true);

        }
    }//GEN-LAST:event_updateActionPerformed

    private void pic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pic1MouseClicked
        JFileChooser fileChooser = new JFileChooser(".");
        String[] filters = new String[]{"jpg", "bmp", "png"};
        ExtensionFileFilter fileFilter = new ExtensionFileFilter("images", filters);

        fileChooser.addChoosableFileFilter(fileFilter);
        int status = fileChooser.showDialog(null, "chose a profile picture");

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIcon m = new ImageIcon(file.getAbsolutePath());
            Image mm = m.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            if (mm != null) {
                pic1.setIcon(new ImageIcon(mm.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
                // a.setPicture1(mm);
                // new HouseDAO().setPicture(a,1);
            }
        }


    }//GEN-LAST:event_pic1MouseClicked

    private void pic2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pic2MouseClicked
        JFileChooser fileChooser = new JFileChooser(".");
        String[] filters = new String[]{"jpg", "bmp", "png"};
        ExtensionFileFilter fileFilter = new ExtensionFileFilter("images", filters);

        fileChooser.addChoosableFileFilter(fileFilter);
        int status = fileChooser.showDialog(null, "chose a profile picture");

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIcon m = new ImageIcon(file.getAbsolutePath());
            Image mm = m.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            if (mm != null) {
                pic2.setIcon(new ImageIcon(mm.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
                // a.setPicture2(mm.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
                // new HouseDAO().setPicture(a,2);
            }
        }
    }//GEN-LAST:event_pic2MouseClicked

    private void pic3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pic3MouseClicked
        JFileChooser fileChooser = new JFileChooser(".");
        String[] filters = new String[]{"jpg", "bmp", "png"};
        ExtensionFileFilter fileFilter = new ExtensionFileFilter("images", filters);

        fileChooser.addChoosableFileFilter(fileFilter);
        int status = fileChooser.showDialog(null, "chose a profile picture");

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIcon m = new ImageIcon(file.getAbsolutePath());
            Image mm = m.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            if (mm != null) {
                pic3.setIcon(new ImageIcon(mm.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
                //a.setPicture3(mm);
                //new HouseDAO().setPicture(a,3);
            }
        }
    }//GEN-LAST:event_pic3MouseClicked
    public void refrech() {
        initComponents();

        town.setSelectedItem(a.getTown());

        price.setValue(a.getPrice());
        description.setText(a.getDescription());
        if (a.getNature().equals("room")) {
            room.setSelected(true);
        }
        if (a.getNature().equals("house")) {
            house.setSelected(true);
        }
        if (a.getNature().equals("appartment")) {
            appartment.setSelected(true);
        }
        if (a.getNature().equals("other")) {
            other.setSelected(true);
        }

        if (a.isAirconditioner()) {
            airConditioner2.setSelected(true);
        }
        if (a.isFireplace()) {
            firePlace2.setSelected(true);
        }
        if (a.isWashingmachine()) {
            washingMachine2.setSelected(true);
        }
        if (a.isDishwasher()) {
            dishWasher2.setSelected(true);
        }
        if (a.isDvd()) {
            dvd2.setSelected(true);
        }
        if (a.isDvd()) {
            dvd2.setSelected(true);
        }
        if (a.isIndoorpool()) {
            indoorPool2.setSelected(true);
        }

        if (a.isSauna()) {
            sauna2.setSelected(true);
        }

        if (a.isJacuzzi()) {
            jacuzzi2.setSelected(true);
        }
        if (a.isTv()) {
            tv2.setSelected(true);
        }
        if (a.isElevator()) {
            elevator2.setSelected(true);
        }
        if (a.isElevator()) {
            elevator2.setSelected(true);
        }
        if (a.isBarbecue()) {
            barbecue2.setSelected(true);
        }
        if (a.isGarden()) {
            garden2.setSelected(true);
        }
        if (a.isGardenfurniture()) {
            gardenForniture2.setSelected(true);
        }
        if (a.isChildrengames()) {
            childrenGames2.setSelected(true);
        }
        if (a.isTerrace()) {
            terrace2.setSelected(true);
        }
        if (a.isIndoorparking()) {
            indoorParking2.setSelected(true);
        }
        if (a.isIndoorparking()) {
            indoorParking2.setSelected(true);
        }
        if (a.isOutdoorparking()) {
            outdoorParking2.setSelected(true);
        }

        other.setText(a.getOther());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar AncienDate;
    private javax.swing.JToolBar Comments;
    private javax.swing.JButton Confirm;
    private javax.swing.JLabel adress;
    private javax.swing.JCheckBox airConditioner2;
    private javax.swing.JRadioButton appartment;
    private javax.swing.JCheckBox barbecue2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox childrenGames2;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextArea description;
    private javax.swing.JCheckBox dishWasher2;
    private javax.swing.JCheckBox dvd2;
    private javax.swing.JCheckBox elevator2;
    private javax.swing.JTextField equipmentOther2;
    private javax.swing.JPanel equipmentPanel2;
    private javax.swing.JCheckBox firePlace2;
    private javax.swing.JCheckBox garden2;
    private javax.swing.JCheckBox gardenForniture2;
    private javax.swing.JRadioButton house;
    private javax.swing.JCheckBox indoorParking2;
    private javax.swing.JCheckBox indoorPool2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JCheckBox jacuzzi2;
    private javax.swing.JRadioButton other;
    private javax.swing.JCheckBox outdoorParking2;
    private javax.swing.JLabel owner;
    private javax.swing.JTextField owner1;
    private javax.swing.JLabel pic1;
    private javax.swing.JLabel pic2;
    private javax.swing.JLabel pic3;
    private javax.swing.JLabel ppic;
    private javax.swing.JSpinner price;
    private javax.swing.JRadioButton room;
    private javax.swing.JCheckBox sauna2;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JLabel star4;
    private javax.swing.JLabel star5;
    private javax.swing.JCheckBox terrace2;
    private javax.swing.JComboBox<String> town;
    private javax.swing.JCheckBox tv2;
    private javax.swing.JButton update;
    private javax.swing.JCheckBox washingMachine2;
    // End of variables declaration//GEN-END:variables
}
