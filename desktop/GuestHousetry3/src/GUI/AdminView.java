/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Panels.AddCommentAdmin;
import GUI.Panels.ForumCreationDemand;
import GUI.Panels.ForumReport;
import GUI.Panels.MessageView;
import Jobs.AdminTasks;
import Jobs.HouseModel;
import Jobs.Penality;
import entities.Claim;
import entities.Comment;
import entities.Forum;
import entities.House;
import entities.Message;
import entities.Notification;
import entities.Rate;
import entities.User;
import entitiesDao.ClaimDAO;
import entitiesDao.CommentReportDAO;
import entitiesDao.ForumDAO;
import entitiesDao.ForumReportDAO;
import entitiesDao.HouseDAO;
import entitiesDao.MessageReportDAO;
import entitiesDao.NotificationDAO;
import entitiesDao.RateDAO;
import entitiesDao.StayDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nabtaaa
 */
public class AdminView extends javax.swing.JFrame implements Runnable {

    private User u;
    private AdminTasks tasks;
    private RateDAO r;
    private int messageReportPage = 0;
    private JFreeChart Data;
    private DefaultCategoryDataset dataset;
    private ChartPanel dataPanel;
    private int[] notes;
    private CategoryPlot data;
    private int commentReportsPage = 0;
    private Thread thread;

    public AdminView(User u) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        notes = new int[5];
        r = new RateDAO();
        setHouseStatistics(0);
        this.u = u;
        tasks = new AdminTasks();

        //setting forum creation demands
        if (!tasks.getCreationDemands().isEmpty()) {
            for (Forum f : tasks.getCreationDemands()) {
                demends.add(new ForumCreationDemand(this, f));
            }
        } else {
            demends.add(new Label("no demends for today go and have fun \n or just wait for it for all i care ;)"));
        }
        //setting Message reports 
        setMessageReportsPage();

        setForumReports();
        setCommentReports();
        setNotifications();
        setClaim();
        this.start();//starting the thread

    }

    private void setNotifications() {

        AdminPane.setTitleAt(0, "Forum Creation '" + tasks.getCreationDemands().size() + "'");

        AdminPane.setTitleAt(2, "Message Reports '" + tasks.getReportedMessages().size() + "'");

        AdminPane.setTitleAt(1, "Forum Reports '" + tasks.getForumReports().size() + "'");

        AdminPane.setTitleAt(3, "Comments Reports '" + tasks.getReportedComments().size() + "'");
        AdminPane.repaint();

    }

    public void setStatistics() {
        dataset = new DefaultCategoryDataset();
        dataset.setValue(notes[0], "NUMBER OF RATES", "1 STAR");
        dataset.setValue(notes[1], "NUMBER OF RATES", "2 STARS");
        dataset.setValue(notes[2], "NUMBER OF RATES", "3 STARS");
        dataset.setValue(notes[3], "NUMBER OF RATES", "4 STARS");
        dataset.setValue(notes[4], "NUMBER OF RATES", "5 STARS");
        Data = ChartFactory.createBarChart("House Rating", "Number Of Stars", "Number Of Rates ", dataset, PlotOrientation.VERTICAL, false, true, false);
        data = Data.getCategoryPlot();
        data.setRangeGridlinePaint(Color.BLUE);
        panelChart.removeAll();
        dataPanel = new ChartPanel(Data);
        panelChart.add(dataPanel, BorderLayout.CENTER);
        panelChart.validate();
    }

    public int getMessageReportPage() {
        return messageReportPage;
    }

    public void setMessageReportPage(int messageReportPage) {
        this.messageReportPage = messageReportPage;
    }

    public void setForumReports() {
        forumReports.removeAll();
        for (Map.Entry<Forum, User> entry : tasks.getForumReports().entrySet()) {
            forumReports.add(new ForumReport(entry.getValue(), entry.getKey(), this));
        }
        forumReports.updateUI();
        forumReports.repaint();
    }

    public void setCommentReports() {
        CommentsReportsBar.removeAll();
        if (tasks.getHousesCommentsReports().size() != 0) {
            comp.setText(commentReportsPage + 1 + "/" + tasks.getHousesCommentsReports().size());

            System.out.println(tasks.getHousesCommentsReports());
            System.out.println(tasks.getReportedComments());
            House h = tasks.getHousesCommentsReports().get(commentReportsPage);
            for (Map.Entry<Comment, User> entry : h.getComments().entrySet()) {
                Comment key = entry.getKey();
                User value = entry.getValue();
                if (tasks.getReportedComments().contains(key)) {
                    CommentsReportsBar.add(new AddCommentAdmin(this, value, key, true));
                } else {
                    CommentsReportsBar.add(new AddCommentAdmin(this, value, key, false));
                }
            }
        } else {
            comp.setText("No reports");
        }
        CommentsReportsBar.updateUI();
        CommentsReportsBar.repaint();
    }

    public JToolBar getDemends() {
        return demends;
    }

    public void setForumDemands() {
        demends.removeAll();
        if (!tasks.getCreationDemands().isEmpty()) {
            for (Forum f : tasks.getCreationDemands()) {
                demends.add(new ForumCreationDemand(this, f));
            }
        } else {
            demends.add(new Label("no demends for today go and have fun or just wait for it for all i care ;)"));
        }
        demends.updateUI();
        demends.repaint();
    }

    private void setMessageReportsPage() {
        messagesReportsMessages.removeAll();

        if (tasks.getForumsMessagesReports().size() != 0) {
            page.setText(messageReportPage + 1 + "/" + tasks.getForumsMessagesReports().size());
            if (messageReportPage >= tasks.getForumsMessagesReports().size()) {
                messageReportPage--;
            }
            Forum f = tasks.getForumsMessagesReports().get(messageReportPage);
            f.setMessages();
            messageReportsSubject.setText(f.getSubject());
            for (Map.Entry<Message, User> en : f.getMessages().entrySet()) {
                Message key = en.getKey();
                User value = en.getValue();
                if (tasks.getReportedMessages().containsKey(key)) {
                    messagesReportsMessages.add(new MessageView(key, value, true));
                } else {
                    messagesReportsMessages.add(new MessageView(key, value, false));
                }
            }
        } else {
            page.setText("No Reports");
            messageReportsSubject.setText("");
        }

        messagesReportsMessages.updateUI();
        messagesReportsMessages.repaint();

    }

    private void setHouseStatistics(int x) {
        HouseModel hmd = new HouseModel();
        if (!hmd.getL().isEmpty()) {
            List<Rate> lr;
            House h = hmd.getL().get(x);
            country.setText(h.getCountry());
            price.setText("" + h.getPrice());
            ownerId.setText("" + houses.getValueAt(x, 0));
            Town.setText(h.getTown());

            lr = r.findbyHouseID(h.getId());
            for (int i = 0; i < 5; i++) {
                notes[i] = 0;
            }
            for (Rate rate : lr) {
                switch (rate.getRate()) {
                    case 1:
                        notes[0]++;
                        break;
                    case 2:
                        notes[1]++;
                        break;
                    case 3:
                        notes[2]++;
                        break;
                    case 4:
                        notes[3]++;
                        break;
                    case 5:
                        notes[4]++;
                        break;
                    default:
                        break;
                }
            }
            setStatistics();
        }
    }

    private void setClaim() {
        tasks.refresh();
        if (tasks.getClaims().size() > 0) {
            penalityPanel.setVisible(true);
            hostPanel.setVisible(true);
            travelerPanel.setVisible(true);
            claimPanel.setVisible(true);
            Claim c = tasks.getClaims().get(0);
            User traveler = new ClaimDAO().findOwnerById(c.getId());
            traveler.addStays();
            traveler.addClaims();
            User host = new HouseDAO().findOwnerById(new ClaimDAO().findHouseById(c.getId()).getId());
            host.addHouses();
            //setting traveler panel
            tName.setText(traveler.getFirstName() + " " + traveler.getLastName());
            tAdress.setText(traveler.getCountry() + " " + traveler.getTown());
            tPhone.setText(traveler.getPhone() + "");
            tPoints.setText("" + traveler.getPoints());
            nbrStays.setText(traveler.getMyStays().size() + "");
            nbrClaims.setText(traveler.getMyClaims().size() + "");
            if (traveler.getPicture() != null) {
                tpic.setIcon(new ImageIcon(traveler.getPicture().getScaledInstance(150, 150, 4)));
            }
            //setting Host Panel
            hName.setText(host.getFirstName() + " " + host.getLastName());
            hAdress.setText(host.getCountry() + " " + host.getTown());
            hPhone.setText(host.getPhone() + "");
            hPoints.setText(host.getPoints() + "");
            nbrHouses.setText(host.getMyHouses().size() + "");

            if (host.getPicture() != null) {
                hpic.setIcon(new ImageIcon(host.getPicture().getScaledInstance(150, 150, 4)));
            }

            int n = 0;
            for (House h : host.getMyHouses()) {
                n += new StayDAO().findByHouseID(h.getId()).size();
            }
            nbrHosting.setText("" + n);
            n = 0;
            for (House h : host.getMyHouses()) {
                n += new ClaimDAO().findByHouseId(h.getId()).size();
            }
            nbrClaimedAbout.setText("" + n);

            //seting the claim
            date.setText(c.getDate().toString());
            text.setText(c.getText());
            refundPoints.setModel(new SpinnerNumberModel(0, 0, host.getPoints(), 1));

            claims.updateUI();
            claims.repaint();
        } else {
            penalityPanel.setVisible(false);
            hostPanel.setVisible(false);
            travelerPanel.setVisible(false);
            claimPanel.setVisible(false);

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

        AdminPane = new javax.swing.JTabbedPane();
        ForumsCreation = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        demends = new javax.swing.JToolBar();
        ForumsReports = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        forumReports = new javax.swing.JToolBar();
        MessagesReports = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        messageReportsSubject = new javax.swing.JEditorPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        messagesReportsMessages = new javax.swing.JToolBar();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        page = new javax.swing.JLabel();
        commentsReports = new javax.swing.JPanel();
        CRPrevious = new javax.swing.JButton();
        CRNext = new javax.swing.JButton();
        comp = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        CommentsReportsBar = new javax.swing.JToolBar();
        Statistics = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        country = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Town = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        ownerId = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        houses = new javax.swing.JTable();
        panelChart = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        price = new javax.swing.JTextPane();
        claims = new javax.swing.JPanel();
        travelerPanel = new javax.swing.JPanel();
        tpic = new javax.swing.JLabel();
        tName = new javax.swing.JLabel();
        tAdress = new javax.swing.JLabel();
        tPhone = new javax.swing.JLabel();
        tPoints = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nbrStays = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        nbrClaims = new javax.swing.JLabel();
        claimPanel = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        date = new javax.swing.JLabel();
        hostPanel = new javax.swing.JPanel();
        hpic = new javax.swing.JLabel();
        hName = new javax.swing.JLabel();
        hAdress = new javax.swing.JLabel();
        hPhone = new javax.swing.JLabel();
        hPoints = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nbrHouses = new javax.swing.JLabel();
        nbrHosting = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nbrClaimedAbout = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        penalityPanel = new javax.swing.JPanel();
        declineClaim = new javax.swing.JButton();
        validatePenality = new javax.swing.JButton();
        deleteHost = new javax.swing.JCheckBox();
        deleteHouse = new javax.swing.JCheckBox();
        deleteTraveler = new javax.swing.JCheckBox();
        refund = new javax.swing.JCheckBox();
        refundPoints = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        AdminPane.setForeground(new java.awt.Color(102, 102, 0));
        AdminPane.setToolTipText("");
        AdminPane.setFocusable(false);
        AdminPane.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        AdminPane.setOpaque(true);

        demends.setBackground(new java.awt.Color(255, 255, 255));
        demends.setOrientation(javax.swing.SwingConstants.VERTICAL);
        demends.setRollover(true);
        jScrollPane6.setViewportView(demends);

        javax.swing.GroupLayout ForumsCreationLayout = new javax.swing.GroupLayout(ForumsCreation);
        ForumsCreation.setLayout(ForumsCreationLayout);
        ForumsCreationLayout.setHorizontalGroup(
            ForumsCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
        );
        ForumsCreationLayout.setVerticalGroup(
            ForumsCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );

        AdminPane.addTab("Forums creation", ForumsCreation);

        ForumsReports.setBackground(new java.awt.Color(255, 255, 255));
        ForumsReports.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        ForumsReports.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ForumsReportsFocusGained(evt);
            }
        });

        forumReports.setBackground(new java.awt.Color(255, 255, 255));
        forumReports.setFloatable(false);
        forumReports.setOrientation(javax.swing.SwingConstants.VERTICAL);
        forumReports.setRollover(true);
        jScrollPane9.setViewportView(forumReports);

        javax.swing.GroupLayout ForumsReportsLayout = new javax.swing.GroupLayout(ForumsReports);
        ForumsReports.setLayout(ForumsReportsLayout);
        ForumsReportsLayout.setHorizontalGroup(
            ForumsReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        ForumsReportsLayout.setVerticalGroup(
            ForumsReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        AdminPane.addTab("Forums Reports", ForumsReports);

        MessagesReports.setBackground(new java.awt.Color(255, 255, 255));

        messageReportsSubject.setEditable(false);
        jScrollPane7.setViewportView(messageReportsSubject);

        messagesReportsMessages.setBackground(new java.awt.Color(255, 255, 255));
        messagesReportsMessages.setFloatable(false);
        messagesReportsMessages.setOrientation(javax.swing.SwingConstants.VERTICAL);
        messagesReportsMessages.setRollover(true);
        jScrollPane8.setViewportView(messagesReportsMessages);

        previous.setText("<< previous");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setBackground(new java.awt.Color(0, 0, 0));
        next.setText("next >>");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        page.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        page.setText("jLabel6");

        javax.swing.GroupLayout MessagesReportsLayout = new javax.swing.GroupLayout(MessagesReports);
        MessagesReports.setLayout(MessagesReportsLayout);
        MessagesReportsLayout.setHorizontalGroup(
            MessagesReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
            .addComponent(jScrollPane8)
            .addGroup(MessagesReportsLayout.createSequentialGroup()
                .addComponent(previous)
                .addGap(194, 194, 194)
                .addComponent(page)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 693, Short.MAX_VALUE)
                .addComponent(next))
        );
        MessagesReportsLayout.setVerticalGroup(
            MessagesReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessagesReportsLayout.createSequentialGroup()
                .addGroup(MessagesReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previous)
                    .addComponent(next)
                    .addComponent(page))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
        );

        AdminPane.addTab("Messages Reports", MessagesReports);

        commentsReports.setBackground(new java.awt.Color(255, 255, 255));

        CRPrevious.setText("<< Previous");
        CRPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CRPreviousActionPerformed(evt);
            }
        });

        CRNext.setText("Next >>");
        CRNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CRNextActionPerformed(evt);
            }
        });

        comp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comp.setText("jLabel6");

        CommentsReportsBar.setBackground(new java.awt.Color(255, 255, 255));
        CommentsReportsBar.setFloatable(false);
        CommentsReportsBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        CommentsReportsBar.setRollover(true);
        jScrollPane10.setViewportView(CommentsReportsBar);

        javax.swing.GroupLayout commentsReportsLayout = new javax.swing.GroupLayout(commentsReports);
        commentsReports.setLayout(commentsReportsLayout);
        commentsReportsLayout.setHorizontalGroup(
            commentsReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, commentsReportsLayout.createSequentialGroup()
                .addComponent(CRPrevious)
                .addGap(216, 216, 216)
                .addComponent(comp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(233, 233, 233)
                .addComponent(CRNext))
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        commentsReportsLayout.setVerticalGroup(
            commentsReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentsReportsLayout.createSequentialGroup()
                .addGroup(commentsReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CRPrevious)
                    .addComponent(CRNext)
                    .addComponent(comp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
        );

        AdminPane.addTab("Comments Reports", commentsReports);

        Statistics.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Owner id :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Country :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Price :");

        country.setPreferredSize(new java.awt.Dimension(10, 20));
        jScrollPane1.setViewportView(country);

        Town.setPreferredSize(new java.awt.Dimension(10, 20));
        jScrollPane2.setViewportView(Town);

        ownerId.setPreferredSize(new java.awt.Dimension(10, 20));
        jScrollPane3.setViewportView(ownerId);

        houses.setModel(new HouseModel());
        houses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                housesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(houses);

        panelChart.setBackground(new java.awt.Color(153, 255, 255));
        panelChart.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Town :");

        price.setPreferredSize(new java.awt.Dimension(10, 20));
        jScrollPane5.setViewportView(price);

        javax.swing.GroupLayout StatisticsLayout = new javax.swing.GroupLayout(Statistics);
        Statistics.setLayout(StatisticsLayout);
        StatisticsLayout.setHorizontalGroup(
            StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticsLayout.createSequentialGroup()
                .addGroup(StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StatisticsLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StatisticsLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StatisticsLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StatisticsLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        StatisticsLayout.setVerticalGroup(
            StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticsLayout.createSequentialGroup()
                .addGroup(StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(StatisticsLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(StatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        AdminPane.addTab("Statistics", Statistics);

        claims.setBackground(new java.awt.Color(255, 255, 255));

        travelerPanel.setBackground(new java.awt.Color(255, 255, 255));
        travelerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Traveler"));

        tpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N

        tName.setText("name");

        tAdress.setText("Adress");

        tPhone.setText("Phone");

        tPoints.setText("Points");

        jLabel16.setText("Number of stays    :");

        nbrStays.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nbrStays.setForeground(new java.awt.Color(255, 0, 0));
        nbrStays.setText("jLabel18");

        jLabel21.setText("Number of Claims   :");

        nbrClaims.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nbrClaims.setForeground(new java.awt.Color(255, 0, 0));
        nbrClaims.setText("jLabel18");

        javax.swing.GroupLayout travelerPanelLayout = new javax.swing.GroupLayout(travelerPanel);
        travelerPanel.setLayout(travelerPanelLayout);
        travelerPanelLayout.setHorizontalGroup(
            travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(travelerPanelLayout.createSequentialGroup()
                .addComponent(tpic, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tName)
                    .addComponent(tAdress)
                    .addComponent(tPhone)
                    .addComponent(tPoints))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(travelerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(travelerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrStays))
                    .addGroup(travelerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrClaims)))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        travelerPanelLayout.setVerticalGroup(
            travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(travelerPanelLayout.createSequentialGroup()
                .addGroup(travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpic)
                    .addGroup(travelerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tAdress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tPoints)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(nbrStays))
                .addGap(29, 29, 29)
                .addGroup(travelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(nbrClaims))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        claimPanel.setBackground(new java.awt.Color(255, 255, 255));
        claimPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Claim :"));

        text.setEditable(false);
        text.setColumns(20);
        text.setRows(5);
        jScrollPane11.setViewportView(text);

        date.setText("jLabel23");

        javax.swing.GroupLayout claimPanelLayout = new javax.swing.GroupLayout(claimPanel);
        claimPanel.setLayout(claimPanelLayout);
        claimPanelLayout.setHorizontalGroup(
            claimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
            .addGroup(claimPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        claimPanelLayout.setVerticalGroup(
            claimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, claimPanelLayout.createSequentialGroup()
                .addComponent(date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        hostPanel.setBackground(new java.awt.Color(255, 255, 255));
        hostPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Host"));

        hpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserImage.png"))); // NOI18N

        hName.setText("name");

        hAdress.setText("Adress");

        hPhone.setText("Phone");

        hPoints.setText("Points");

        jLabel18.setText("Hosted ");

        jLabel17.setText("Number of Houses :");

        nbrHouses.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nbrHouses.setForeground(new java.awt.Color(255, 0, 0));
        nbrHouses.setText("jLabel18");

        nbrHosting.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nbrHosting.setForeground(new java.awt.Color(255, 0, 0));
        nbrHosting.setText("jLabel18");

        jLabel19.setText("Persons");

        jLabel20.setText("Has been Claimed about ");

        nbrClaimedAbout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nbrClaimedAbout.setForeground(new java.awt.Color(255, 0, 0));
        nbrClaimedAbout.setText("jLabel18");

        jLabel22.setText("times");

        javax.swing.GroupLayout hostPanelLayout = new javax.swing.GroupLayout(hostPanel);
        hostPanel.setLayout(hostPanelLayout);
        hostPanelLayout.setHorizontalGroup(
            hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hostPanelLayout.createSequentialGroup()
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(hostPanelLayout.createSequentialGroup()
                        .addComponent(hpic, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hName)
                            .addComponent(hAdress)
                            .addComponent(hPhone)
                            .addComponent(hPoints)))
                    .addGroup(hostPanelLayout.createSequentialGroup()
                        .addComponent(nbrHouses)
                        .addGap(4, 4, 4)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(hostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(hostPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrHosting, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19))
                    .addGroup(hostPanelLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrClaimedAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        hostPanelLayout.setVerticalGroup(
            hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hostPanelLayout.createSequentialGroup()
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hpic)
                    .addGroup(hostPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(hName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hAdress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hPoints)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(nbrHouses))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(nbrHosting)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(nbrClaimedAbout)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        penalityPanel.setBackground(new java.awt.Color(255, 255, 255));
        penalityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("penality"));

        declineClaim.setBackground(new java.awt.Color(255, 255, 255));
        declineClaim.setText("Decline Claim");
        declineClaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineClaimActionPerformed(evt);
            }
        });

        validatePenality.setBackground(new java.awt.Color(255, 255, 255));
        validatePenality.setForeground(new java.awt.Color(153, 0, 0));
        validatePenality.setText("Validate Penality");
        validatePenality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validatePenalityActionPerformed(evt);
            }
        });

        deleteHost.setBackground(new java.awt.Color(255, 255, 255));
        deleteHost.setText("Delete Host");
        deleteHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHostActionPerformed(evt);
            }
        });

        deleteHouse.setBackground(new java.awt.Color(255, 255, 255));
        deleteHouse.setText("Delete House");

        deleteTraveler.setBackground(new java.awt.Color(255, 255, 255));
        deleteTraveler.setText("Delete Traveler");

        refund.setBackground(new java.awt.Color(255, 255, 255));
        refund.setText("refund");
        refund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundActionPerformed(evt);
            }
        });

        refundPoints.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        refundPoints.setEnabled(false);

        javax.swing.GroupLayout penalityPanelLayout = new javax.swing.GroupLayout(penalityPanel);
        penalityPanel.setLayout(penalityPanelLayout);
        penalityPanelLayout.setHorizontalGroup(
            penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(penalityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(penalityPanelLayout.createSequentialGroup()
                        .addComponent(deleteTraveler)
                        .addGap(79, 79, 79)
                        .addComponent(refund)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refundPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(validatePenality))
                    .addGroup(penalityPanelLayout.createSequentialGroup()
                        .addComponent(deleteHost)
                        .addGap(90, 90, 90)
                        .addComponent(deleteHouse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(declineClaim)))
                .addContainerGap())
        );

        penalityPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {declineClaim, validatePenality});

        penalityPanelLayout.setVerticalGroup(
            penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(penalityPanelLayout.createSequentialGroup()
                .addGroup(penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(penalityPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteHost)
                            .addComponent(deleteHouse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, penalityPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(declineClaim)
                        .addGap(18, 18, 18)))
                .addGroup(penalityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validatePenality)
                    .addComponent(deleteTraveler)
                    .addComponent(refundPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refund))
                .addContainerGap())
        );

        javax.swing.GroupLayout claimsLayout = new javax.swing.GroupLayout(claims);
        claims.setLayout(claimsLayout);
        claimsLayout.setHorizontalGroup(
            claimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, claimsLayout.createSequentialGroup()
                .addComponent(travelerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(claimsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(claimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(claimsLayout.createSequentialGroup()
                        .addComponent(penalityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(claimPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        claimsLayout.setVerticalGroup(
            claimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(claimsLayout.createSequentialGroup()
                .addGroup(claimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(travelerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hostPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(penalityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        AdminPane.addTab("Claims", claims);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/houseIconMini.png"))); // NOI18N
        jLabel1.setText("Guest House");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Connect as a normal user");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
            .addComponent(AdminPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdminPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ForumsReportsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ForumsReportsFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ForumsReportsFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
        
        u.arm();
        GuestHouse.u = this.u;
        new home().setVisible(true);

        kill();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void housesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_housesMouseClicked
        setHouseStatistics(houses.getSelectedRow());
    }//GEN-LAST:event_housesMouseClicked

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if (messageReportPage == 0) {
            messageReportPage = tasks.getForumsMessagesReports().size() - 1;
        } else {
            messageReportPage--;
        }
        setMessageReportsPage();
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (messageReportPage == 0) {
            messageReportPage = tasks.getForumsMessagesReports().size() - 1;
        } else {
            messageReportPage--;
        }
        setMessageReportsPage();
    }//GEN-LAST:event_nextActionPerformed

    private void CRPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CRPreviousActionPerformed
        if (commentReportsPage == tasks.getHousesCommentsReports().size() - 1) {
            commentReportsPage = 0;
        } else {
            commentReportsPage++;
        }
        setCommentReports();
    }//GEN-LAST:event_CRPreviousActionPerformed

    private void CRNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CRNextActionPerformed
        if (commentReportsPage == tasks.getHousesCommentsReports().size() - 1) {
            commentReportsPage = 0;
        } else {
            commentReportsPage++;
        }
        setCommentReports();
    }//GEN-LAST:event_CRNextActionPerformed

    private void deleteHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteHostActionPerformed

    private void declineClaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineClaimActionPerformed
        Claim c = tasks.getClaims().get(0);
        c.setIsTreated(true);
        c.setStatus("Claim refused");
        new NotificationDAO().add(new Notification("your claim :'" + c.getText() + "' has been refused", new Date(Calendar.getInstance().getTimeInMillis()), null), new ClaimDAO().findOwnerById(c.getId()).getId());

        new ClaimDAO().update(c);
        setClaim();
    }//GEN-LAST:event_declineClaimActionPerformed

    private void validatePenalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validatePenalityActionPerformed
        Penality p = new Penality(tasks.getClaims().get(0), this);
        if (refund.isSelected()) {
            p.refund((Integer) refundPoints.getValue());
        }
        if (deleteHost.isSelected()) {
            p.deleteHost();
        }
        if (deleteTraveler.isSelected()) {
            p.deleteTraveler();
        }
        if (deleteHouse.isSelected()) {
            p.deleteHouse();
        }
        setClaim();

    }//GEN-LAST:event_validatePenalityActionPerformed

    private void refundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundActionPerformed
        if (refundPoints.isEnabled()) {
            refundPoints.setEnabled(false);
        } else {
            refundPoints.setEnabled(true);
        }
    }//GEN-LAST:event_refundActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane AdminPane;
    private javax.swing.JButton CRNext;
    private javax.swing.JButton CRPrevious;
    private javax.swing.JToolBar CommentsReportsBar;
    private javax.swing.JPanel ForumsCreation;
    private javax.swing.JPanel ForumsReports;
    private javax.swing.JPanel MessagesReports;
    private javax.swing.JPanel Statistics;
    private javax.swing.JTextPane Town;
    private javax.swing.JPanel claimPanel;
    private javax.swing.JPanel claims;
    private javax.swing.JPanel commentsReports;
    private javax.swing.JLabel comp;
    private javax.swing.JTextPane country;
    private javax.swing.JLabel date;
    private javax.swing.JButton declineClaim;
    private javax.swing.JCheckBox deleteHost;
    private javax.swing.JCheckBox deleteHouse;
    private javax.swing.JCheckBox deleteTraveler;
    private javax.swing.JToolBar demends;
    private javax.swing.JToolBar forumReports;
    private javax.swing.JLabel hAdress;
    private javax.swing.JLabel hName;
    private javax.swing.JLabel hPhone;
    private javax.swing.JLabel hPoints;
    private javax.swing.JPanel hostPanel;
    private javax.swing.JTable houses;
    private javax.swing.JLabel hpic;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JEditorPane messageReportsSubject;
    private javax.swing.JToolBar messagesReportsMessages;
    private javax.swing.JLabel nbrClaimedAbout;
    private javax.swing.JLabel nbrClaims;
    private javax.swing.JLabel nbrHosting;
    private javax.swing.JLabel nbrHouses;
    private javax.swing.JLabel nbrStays;
    private javax.swing.JButton next;
    private javax.swing.JTextPane ownerId;
    private javax.swing.JLabel page;
    private javax.swing.JPanel panelChart;
    private javax.swing.JPanel penalityPanel;
    private javax.swing.JButton previous;
    private javax.swing.JTextPane price;
    private javax.swing.JCheckBox refund;
    private javax.swing.JSpinner refundPoints;
    private javax.swing.JLabel tAdress;
    private javax.swing.JLabel tName;
    private javax.swing.JLabel tPhone;
    private javax.swing.JLabel tPoints;
    private javax.swing.JTextArea text;
    private javax.swing.JLabel tpic;
    private javax.swing.JPanel travelerPanel;
    private javax.swing.JButton validatePenality;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        int k = 0;
        int msgrp = 0;
        int frnbr = 0;
        int comRp = 0;
        int cl = 0;
        while (true) {
            try {
                if (new ForumDAO().findAllNotCreated() != k) {
                    tasks.refresh();
                    setNotifications();
                    setForumDemands();
                    k = new ForumDAO().findAllNotCreated();
                }
                if (new MessageReportDAO().getnbr() != msgrp) {
                    tasks.refresh();
                    setNotifications();
                    setMessageReportsPage();
                    msgrp = new MessageReportDAO().getnbr();
                }
                if (frnbr != new ForumReportDAO().getNbrReports()) {
                    tasks.refresh();
                    setNotifications();
                    setForumReports();
                    frnbr = new ForumReportDAO().getNbrReports();
                }
                if (comRp != new CommentReportDAO().findAll().size()) {
                    tasks.refresh();
                    setNotifications();
                    setCommentReports();
                    comRp = new CommentReportDAO().findAll().size();

                }
                if (cl != new ClaimDAO().findNbr()) {
                    tasks.refresh();
                    setClaim();
                    cl = new ClaimDAO().findNbr();

                }

                thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "Admin tasks");
        }
        thread.start();
    }

    public void kill() {
        thread.stop();
    }
}
