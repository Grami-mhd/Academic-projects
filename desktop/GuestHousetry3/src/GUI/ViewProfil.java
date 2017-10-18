/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Dialogs.changePassword;
import GUI.Dialogs.disableAccount;
import GUI.Dialogs.houseAdded;
import GUI.Dialogs.prixSet;
import GUI.Dialogs.userProfilUpdated;
import GUI.Panels.HousePanel1;

import Jobs.Penality;
import entities.Add;
import entities.House;
import entities.User;
import entitiesDao.AddDAO;
import entitiesDao.HouseDAO;
import entitiesDao.UserDAO;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import utils.ExtensionFileFilter;
import utils.Mail;

/**
 *
 * @author asus
 */
public class ViewProfil extends GuestHouse {

    public ViewProfil() {
        super();
        initComponents();
        otherEntry.setEnabled(false);
        if (u.getPicture() != null) {
            profilePicture.setIcon(new javax.swing.ImageIcon(u.getPicture().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH)));
        }

        udao = new UserDAO();
       
        firstName.setText(u.getFirstName());
        lastName.setText(u.getLastName());
        mail.setText(u.getEmail());
        townuser.setSelectedItem(u.getTown());
        phone.setValue(u.getPhone());
        if (u.getPicture() != null) {
            ppic.setIcon(new javax.swing.ImageIcon(u.getPicture().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        }

        showHouses();

    }

    public void refrech() {
        initComponents();
        u = udao.findbyID(u.getId());
        firstName.setText(u.getFirstName());
        lastName.setText(u.getLastName());
        mail.setText(u.getEmail());
        phone.setValue(u.getPhone());

    }

    public void showHouses() {
        u.addAdds();

        houseBar.removeAll();
        u.addHouses();
        for (House l :u.getMyHouses()) {
            if(u.getAdds().contains(l))
                
            houseBar.add(new HousePanel1(new AddDAO().findById(l.getId()), this));
            else
                houseBar.add(new HousePanel1(new Add(l), this));
        }
        houseBar.updateUI();
        houseBar.repaint();
        System.out.println("done reloading ");
    }

    public User getU() {
        return u;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        yourAdress = new javax.swing.JRadioButton();
        otherAdress = new javax.swing.JRadioButton();
        room = new javax.swing.JRadioButton();
        town = new javax.swing.JComboBox();
        country = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        house = new javax.swing.JRadioButton();
        appartement = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        equipmentPanel2 = new javax.swing.JPanel();
        airConditioner3 = new javax.swing.JCheckBox();
        firePlace3 = new javax.swing.JCheckBox();
        washingMachine3 = new javax.swing.JCheckBox();
        dishWasher3 = new javax.swing.JCheckBox();
        dvd3 = new javax.swing.JCheckBox();
        indoorPool3 = new javax.swing.JCheckBox();
        sauna3 = new javax.swing.JCheckBox();
        jacuzzi3 = new javax.swing.JCheckBox();
        tv3 = new javax.swing.JCheckBox();
        elevator3 = new javax.swing.JCheckBox();
        barbecue3 = new javax.swing.JCheckBox();
        garden3 = new javax.swing.JCheckBox();
        gardenForniture3 = new javax.swing.JCheckBox();
        childrenGames3 = new javax.swing.JCheckBox();
        terrace3 = new javax.swing.JCheckBox();
        indoorParking3 = new javax.swing.JCheckBox();
        outdoorParking3 = new javax.swing.JCheckBox();
        equipmentOther3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        addLocal = new javax.swing.JButton();
        otherNature = new javax.swing.JRadioButton();
        otherEntry = new javax.swing.JTextField();
        price = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        lab = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        disableAccount = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        townuser = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        ppic = new javax.swing.JLabel();
        phone = new javax.swing.JSpinner();
        Update = new javax.swing.JButton();
        userMenuBar = new javax.swing.JToolBar();
        image = new javax.swing.JLabel();
        profilePicture = new javax.swing.JLabel();
        userProfile = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        forums = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        claims = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        points = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        sell = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        buy = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        houseBar = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel8.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel8.setText("Add a local");

        jLabel9.setText("Adress:");

        buttonGroup1.add(yourAdress);
        yourAdress.setText("Your adress");
        yourAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yourAdressActionPerformed(evt);
            }
        });

        buttonGroup1.add(otherAdress);
        otherAdress.setText("Other");
        otherAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherAdressActionPerformed(evt);
            }
        });

        buttonGroup2.add(room);
        room.setText("Room");
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });

        town.setEditable(true);
        town.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Soussa", "Monastir", "Mahdia", "Sfax", "Tunis", "Ben Arous", "Manouba", "Ariana", "Bizert", "Nabel", "Kef", "Gabes", "Mednine", "Tatawin", "Sidi Bou Zid", "Zaghouan", "Tozer", "Kairouan", "Beja", "Gasrine", "Gafsa", "Siliana", "Gbelli", "Jandouba" }));
        town.setEnabled(false);
        town.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                townActionPerformed(evt);
            }
        });

        country.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tunisia" }));
        country.setEnabled(false);

        jLabel10.setText("Nature:");

        buttonGroup2.add(house);
        house.setText("House");
        house.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                houseActionPerformed(evt);
            }
        });

        buttonGroup2.add(appartement);
        appartement.setText("Appartment");
        appartement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appartementActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Equipments:");

        equipmentPanel2.setAutoscrolls(true);

        airConditioner3.setText("Air Conditioner");
        airConditioner3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airConditioner3ActionPerformed(evt);
            }
        });

        firePlace3.setText("fire place");
        firePlace3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firePlace3ActionPerformed(evt);
            }
        });

        washingMachine3.setText("Washing Machine");
        washingMachine3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                washingMachine3ActionPerformed(evt);
            }
        });

        dishWasher3.setText("Dish Washer");
        dishWasher3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dishWasher3ActionPerformed(evt);
            }
        });

        dvd3.setText("DVD");
        dvd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dvd3ActionPerformed(evt);
            }
        });

        indoorPool3.setText("Indoor Pool");
        indoorPool3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indoorPool3ActionPerformed(evt);
            }
        });

        sauna3.setText("Sauna");
        sauna3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauna3ActionPerformed(evt);
            }
        });

        jacuzzi3.setText("Jacuzzi");
        jacuzzi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jacuzzi3ActionPerformed(evt);
            }
        });

        tv3.setText("TV");
        tv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tv3ActionPerformed(evt);
            }
        });

        elevator3.setText("Elevator");
        elevator3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elevator3ActionPerformed(evt);
            }
        });

        barbecue3.setText("Barbecue");
        barbecue3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barbecue3ActionPerformed(evt);
            }
        });

        garden3.setText("Garden");
        garden3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garden3ActionPerformed(evt);
            }
        });

        gardenForniture3.setText("Garden Fourniture");
        gardenForniture3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gardenForniture3ActionPerformed(evt);
            }
        });

        childrenGames3.setText("Children Games");
        childrenGames3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childrenGames3ActionPerformed(evt);
            }
        });

        terrace3.setText("Terrace");

        indoorParking3.setText("Indoor Parking");

        outdoorParking3.setText("Outdoor Parking");
        outdoorParking3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outdoorParking3ActionPerformed(evt);
            }
        });

        equipmentOther3.setText("other...");
        equipmentOther3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentOther3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout equipmentPanel2Layout = new javax.swing.GroupLayout(equipmentPanel2);
        equipmentPanel2.setLayout(equipmentPanel2Layout);
        equipmentPanel2Layout.setHorizontalGroup(
            equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(airConditioner3)
                        .addComponent(firePlace3)
                        .addComponent(washingMachine3)
                        .addComponent(tv3)
                        .addComponent(elevator3)
                        .addComponent(barbecue3)
                        .addComponent(garden3)
                        .addComponent(gardenForniture3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(childrenGames3)
                        .addComponent(terrace3)
                        .addComponent(indoorParking3)
                        .addComponent(outdoorParking3)
                        .addComponent(equipmentOther3))
                    .addComponent(dishWasher3)
                    .addComponent(dvd3)
                    .addComponent(indoorPool3)
                    .addComponent(sauna3)
                    .addComponent(jacuzzi3)))
        );
        equipmentPanel2Layout.setVerticalGroup(
            equipmentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(airConditioner3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firePlace3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(washingMachine3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dishWasher3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dvd3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indoorPool3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sauna3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jacuzzi3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elevator3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barbecue3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(garden3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gardenForniture3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(childrenGames3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terrace3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indoorParking3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outdoorParking3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentOther3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Price:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("The estimation of your add ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Price =");

        addLocal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addLocal.setText("Add local");
        addLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLocalActionPerformed(evt);
            }
        });

        buttonGroup2.add(otherNature);
        otherNature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherNatureActionPerformed(evt);
            }
        });

        otherEntry.setText("other...");
        otherEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherEntryActionPerformed(evt);
            }
        });

        price.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        description.setColumns(20);
        description.setRows(5);
        jScrollPane3.setViewportView(description);

        lab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lab.setText("Description:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yourAdress)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(otherAdress)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(town, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(room)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(house)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appartement)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(otherNature)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(otherEntry))))
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(equipmentPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(addLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(yourAdress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otherAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(town, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(room)
                        .addComponent(jLabel10)
                        .addComponent(house)
                        .addComponent(appartement)
                        .addComponent(otherNature))
                    .addComponent(otherEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(equipmentPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(34, 34, 34)
                                .addComponent(lab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(addLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        jToolBar1.add(jScrollPane1);

        disableAccount.setText("Disable account");
        disableAccount.setFocusable(false);
        disableAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        disableAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        disableAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableAccountActionPerformed(evt);
            }
        });

        jLabel2.setText("First name:");

        jLabel3.setText("Last name:");

        jLabel4.setText("EMail:");

        jLabel5.setText("Adress:");

        jLabel6.setText("Phone");

        firstName.setEditable(false);
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });

        lastName.setEditable(false);
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });

        mail.setEditable(false);
        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tunisia" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        townuser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Soussa", "Monastir", "Mahdia", "Sfax", "Tunis", "Ben Arous", "Manouba", "Ariana", "Bizert", "Nabel", "Kef", "Gabes", "Mednine", "Tatawin", "Sidi Bou Zid", "Zaghouan", "Tozer", "Kairouan", "Beja", "Gasrine", "Gafsa", "Siliana", "Gbelli", "Jandouba" }));
        townuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                townuserActionPerformed(evt);
            }
        });

        jButton2.setText("Change password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N
        ppic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ppicMouseClicked(evt);
            }
        });

        phone.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(10000000L), Long.valueOf(10000000L), Long.valueOf(99999999L), Long.valueOf(1L)));
        phone.setEnabled(false);
        phone.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                phoneStateChanged(evt);
            }
        });

        Update.setText("Update informations");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(disableAccount))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstName)
                    .addComponent(lastName)
                    .addComponent(mail)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(townuser, 0, 1, Short.MAX_VALUE))
                    .addComponent(phone)
                    .addComponent(Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(disableAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(townuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ppic, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jLabel15.setText("            ");
        userMenuBar.add(jLabel15);

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

        jLabel16.setText("     ");
        userMenuBar.add(jLabel16);

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

        jLabel17.setText("                  ");
        userMenuBar.add(jLabel17);

        jLabel18.setText("points = ");
        userMenuBar.add(jLabel18);

        points.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        points.setText("450");
        userMenuBar.add(points);

        jLabel19.setText("              ");
        userMenuBar.add(jLabel19);

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

        jLabel20.setText("  ");
        userMenuBar.add(jLabel20);

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

        jLabel21.setText("        ");
        userMenuBar.add(jLabel21);

        jButton3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton3.setText("     Home    ");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        userMenuBar.add(jButton3);

        houseBar.setFloatable(false);
        houseBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        houseBar.setRollover(true);
        jScrollPane2.setViewportView(houseBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(userMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void townuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_townuserActionPerformed
        //u.setTown((String) townuser.getSelectedItem());
        //udao.update(u);
        //new userProfilUpdated(this, true).setVisible(true);

    }//GEN-LAST:event_townuserActionPerformed

    private void townActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_townActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_townActionPerformed

    private void airConditioner3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_airConditioner3ActionPerformed


    }//GEN-LAST:event_airConditioner3ActionPerformed

    private void firePlace3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firePlace3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_firePlace3ActionPerformed

    private void washingMachine3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_washingMachine3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_washingMachine3ActionPerformed

    private void dishWasher3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dishWasher3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_dishWasher3ActionPerformed

    private void dvd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvd3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_dvd3ActionPerformed

    private void indoorPool3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indoorPool3ActionPerformed

    }//GEN-LAST:event_indoorPool3ActionPerformed

    private void sauna3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauna3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_sauna3ActionPerformed

    private void jacuzzi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jacuzzi3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jacuzzi3ActionPerformed

    private void tv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tv3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tv3ActionPerformed

    private void elevator3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elevator3ActionPerformed

    }//GEN-LAST:event_elevator3ActionPerformed

    private void barbecue3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barbecue3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_barbecue3ActionPerformed

    private void garden3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garden3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_garden3ActionPerformed

    private void gardenForniture3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gardenForniture3ActionPerformed

    }//GEN-LAST:event_gardenForniture3ActionPerformed

    private void childrenGames3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_childrenGames3ActionPerformed

    }//GEN-LAST:event_childrenGames3ActionPerformed

    private void outdoorParking3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outdoorParking3ActionPerformed

    }//GEN-LAST:event_outdoorParking3ActionPerformed

    private void equipmentOther3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentOther3ActionPerformed

    }//GEN-LAST:event_equipmentOther3ActionPerformed

    private void otherAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherAdressActionPerformed
        town.setSelectedIndex(1);
        town.setEnabled(true);
        adress = 2;
    }//GEN-LAST:event_otherAdressActionPerformed

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        //u.setFirstName(firstName.getText());
        //udao.update(u);
        //new userProfilUpdated(this, true).setVisible(true);

    }//GEN-LAST:event_firstNameActionPerformed

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        //u.setLastName(lastName.getText());
        //udao.update(u);
        //new userProfilUpdated(this, true).setVisible(true);

    }//GEN-LAST:event_lastNameActionPerformed

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mailActionPerformed

    private void appartementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appartementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appartementActionPerformed

    private void otherNatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherNatureActionPerformed
        otherEntry.setText("");
        otherEntry.setEnabled(true);
    }//GEN-LAST:event_otherNatureActionPerformed

    private void houseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_houseActionPerformed
        otherEntry.setText("other...");
        otherEntry.setEnabled(false);
    }//GEN-LAST:event_houseActionPerformed

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed
        otherEntry.setText("other...");
        otherEntry.setEnabled(false);
    }//GEN-LAST:event_roomActionPerformed

    private void otherEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherEntryActionPerformed

    }//GEN-LAST:event_otherEntryActionPerformed

    private void yourAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yourAdressActionPerformed
        town.setSelectedIndex(1);
        town.setEnabled(false);
        adress = 1;
    }//GEN-LAST:event_yourAdressActionPerformed

    private void addLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLocalActionPerformed
        // TODO add your handling code here:
        House h = new House();
        if ((int) price.getValue() < 1000) {
            if (airConditioner3.isSelected()) {
                h.setAirconditioner(true);
            }
            if (dvd3.isSelected()) {
                h.setDvd(true);
            }
            if (firePlace3.isSelected()) {
                h.setFireplace(true);
            }
            if (elevator3.isSelected()) {
                h.setElevator(true);
            }
            if (washingMachine3.isSelected()) {
                h.setWashingmachine(true);
            }
            if (dishWasher3.isSelected()) {
                h.setDishwasher(true);
            }
            if (indoorPool3.isSelected()) {
                h.setIndoorpool(true);
            }
            if (sauna3.isSelected()) {
                h.setSauna(true);
            }
            if (jacuzzi3.isSelected()) {
                h.setJacuzzi(true);
            }
            if (tv3.isSelected()) {
                h.setTv(true);
            }
            if (barbecue3.isSelected()) {
                h.setBarbecue(true);
            }
            if (garden3.isSelected()) {
                h.setGarden(true);
            }
            if (gardenForniture3.isSelected()) {
                h.setGardenfurniture(true);
            }
            if (childrenGames3.isSelected()) {
                h.setChildrengames(true);
            }
            if (terrace3.isSelected()) {
                h.setTerrace(true);
            }
            if (indoorParking3.isSelected()) {
                h.setIndoorparking(true);
            }
            if (outdoorParking3.isSelected()) {
                h.setOutdoorparking(true);
            }
            if (equipmentOther3 == null) {
                h.setOther(equipmentOther3.getText());
            }

            if (adress == 2) {
                h.setCountry((String) country.getSelectedItem());
                h.setTown((String) town.getSelectedItem());
            }
            if (adress == 1) {
                h.setCountry(u.getCountry());
                h.setTown(u.getTown());
            }

            if (room.isSelected()) {
                h.setNature("room");
            }
            if (house.isSelected()) {
                h.setNature("house");
            }
            if (appartement.isSelected()) {
                h.setNature("appatment");
            }
            if (otherNature.isSelected()) {
                h.setNature(otherEntry.getText());
            }

            h.setPrice((int) price.getValue());
            h.setDescription(description.getText());

            HouseDAO hd = new HouseDAO();
            hd.add(h, u.getId());
            System.out.println("done");
            showHouses();
            System.out.println("done :)");
            new houseAdded(this, true).setVisible(true);
        } else {
            new prixSet(this, true).setVisible(true);
        }


    }//GEN-LAST:event_addLocalActionPerformed

    private void phoneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_phoneStateChanged
        //u.setPhone((long) phone.getValue());
        //udao.update(u);
        //new userProfilUpdated(this, true).setVisible(true);
    }//GEN-LAST:event_phoneStateChanged

    private void disableAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disableAccountActionPerformed
        disableAccount d = new disableAccount(this, true);
        d.setVisible(true);
        if (d.getReturnStatus() == 1) {
            String to[] = {u.getEmail()};
            try {
                Mail.sendFromGMail("guest.house.pi@gmail.com", "N.O-Society", to, "Deleting account", "your account has been succesefully deleted");
            } catch (MessagingException ex) {
                Logger.getLogger(Penality.class.getName()).log(Level.SEVERE, null, ex);
            }
            udao.removeById(u.getId());
            System.exit(0);
        }
    }//GEN-LAST:event_disableAccountActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        new changePassword(this, true, u).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed

        if (firstName.isEditable()) {
            u.setFirstName(firstName.getText());
            u.setLastName(lastName.getText());
            u.setTown((String) townuser.getSelectedItem());
            u.setPhone((long) phone.getValue());
            udao.update(u);
            new userProfilUpdated(this, true).setVisible(true);
            firstName.setEditable(false);
            lastName.setEditable(false);
            townuser.setEditable(false);
            phone.setEnabled(false);

        } else {
            firstName.setEditable(true);
            lastName.setEditable(true);
            townuser.setEditable(true);
            phone.setEnabled(true);
        }

    }//GEN-LAST:event_UpdateActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void ppicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ppicMouseClicked
        JFileChooser fileChooser = new JFileChooser(".");
        String[] filters = new String[]{"jpg", "bmp", "png"};
        ExtensionFileFilter fileFilter = new ExtensionFileFilter("images", filters);

        fileChooser.addChoosableFileFilter(fileFilter);
        int status = fileChooser.showDialog(null, "chose a profile picture");

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIcon m = new ImageIcon(file.getAbsolutePath());
            Image mm = m.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
            if (mm != null) {
                ppic.setIcon(new ImageIcon(mm));
                u.setPicture(mm);
                new UserDAO().updatePicture(u);
            }
        }
    }//GEN-LAST:event_ppicMouseClicked

    private int adress = 0;
    private UserDAO udao;

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Update;
    private javax.swing.JButton addLocal;
    private javax.swing.JCheckBox airConditioner3;
    private javax.swing.JRadioButton appartement;
    private javax.swing.JCheckBox barbecue3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buy;
    private javax.swing.JCheckBox childrenGames3;
    private javax.swing.JButton claims;
    private javax.swing.JComboBox country;
    private javax.swing.JTextArea description;
    private javax.swing.JButton disableAccount;
    private javax.swing.JCheckBox dishWasher3;
    private javax.swing.JCheckBox dvd3;
    private javax.swing.JCheckBox elevator3;
    private javax.swing.JTextField equipmentOther3;
    private javax.swing.JPanel equipmentPanel2;
    private javax.swing.JCheckBox firePlace3;
    private javax.swing.JTextField firstName;
    private javax.swing.JButton forums;
    private javax.swing.JCheckBox garden3;
    private javax.swing.JCheckBox gardenForniture3;
    private javax.swing.JRadioButton house;
    private javax.swing.JToolBar houseBar;
    private javax.swing.JLabel image;
    private javax.swing.JCheckBox indoorParking3;
    private javax.swing.JCheckBox indoorPool3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JCheckBox jacuzzi3;
    private javax.swing.JLabel lab;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField mail;
    private javax.swing.JRadioButton otherAdress;
    private javax.swing.JTextField otherEntry;
    private javax.swing.JRadioButton otherNature;
    private javax.swing.JCheckBox outdoorParking3;
    private javax.swing.JSpinner phone;
    private javax.swing.JLabel points;
    private javax.swing.JLabel ppic;
    private javax.swing.JSpinner price;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JRadioButton room;
    private javax.swing.JCheckBox sauna3;
    private javax.swing.JButton sell;
    private javax.swing.JCheckBox terrace3;
    private javax.swing.JComboBox town;
    private javax.swing.JComboBox townuser;
    private javax.swing.JCheckBox tv3;
    private javax.swing.JToolBar userMenuBar;
    private javax.swing.JButton userProfile;
    private javax.swing.JCheckBox washingMachine3;
    private javax.swing.JRadioButton yourAdress;
    // End of variables declaration//GEN-END:variables

}
