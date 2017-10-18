/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Claim;
import entities.Comment;
import entities.Forum;
import entities.House;
import entities.Message;
import entities.User;
import entitiesDao.ClaimDAO;
import entitiesDao.CommentReportDAO;
import entitiesDao.ForumDAO;
import entitiesDao.ForumReportDAO;
import entitiesDao.MessageDAO;
import entitiesDao.MessageReportDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author grami
 */
public class AdminTasks {

    private MessageReportDAO mrdao;
    private ForumDAO fdao;
    private List<Forum> creationDemands;
    private List<Forum> forumsMessagesReports;
    private Map<Message, User> reportedMessages;
    private ForumsJob fj;
    private List<Claim> claims;
    private Map<Forum, User> forumReports;
    private List<House> HousesCommentsReports;
    private List<Comment> reportedComments;

    public AdminTasks() {
        HousesCommentsReports = new ArrayList<>();
        for (House h : new CommentReportDAO().findAllHouses()) {
            h.addComments();
            HousesCommentsReports.add(h);
            System.out.println(h);
        }
        reportedComments = new CommentReportDAO().findAll();

        mrdao = new MessageReportDAO();
        reportedMessages = new HashMap<>();

        reportedMessages = mrdao.findAll();
        fdao = new ForumDAO();
        creationDemands = new ArrayList<>();
        for (Forum creationDemand : fdao.findAll()) {
            if (!creationDemand.isCreated()) {
                creationDemands.add(creationDemand);
            }
        }

        forumsMessagesReports = new ArrayList<>();
        Set<Forum> ff = new HashSet<>();
        for (Map.Entry<Message, User> entry : reportedMessages.entrySet()) {
            Message key = entry.getKey();
            User value = entry.getValue();

            ff.add(new MessageDAO().findForumById(key.getId()));
        }
        for (Forum forum : ff) {
            forumsMessagesReports.add(forum);
        }

        claims = new ClaimDAO().findAll();
        List<Claim> c = new ArrayList<>();
        for (Claim claim : claims) {
            if (!claim.isIsTreated()) {
                c.add(claim);
            }
        }
        claims = c;
        forumReports = new HashMap<>();
        forumReports = new ForumReportDAO().findAll();
    }

    public void refresh() {
        //seting creation demends

        creationDemands.clear();

        for (Forum creationDemand : fdao.findAll()) {
            if (!creationDemand.isCreated()) {
                creationDemands.add(creationDemand);
            }
        }
        //seting messages reports
        forumsMessagesReports.clear();

        reportedMessages = mrdao.findAll();
        Set<Forum> ff = new HashSet<>();
        for (Map.Entry<Message, User> entry : reportedMessages.entrySet()) {
            Message key = entry.getKey();
            User value = entry.getValue();
            ff.add(new MessageDAO().findForumById(key.getId()));
        }
        for (Forum forum : ff) {
            forumsMessagesReports.add(forum);
        }
        //seting forum reports
        forumReports = new ForumReportDAO().findAll();

        //seting  Comment Reports
        HousesCommentsReports = new ArrayList<>();
        for (House h : new CommentReportDAO().findAllHouses()) {
            h.addComments();
            HousesCommentsReports.add(h);
        }
        reportedComments = new CommentReportDAO().findAll();

        //seting Claims
        claims = new ClaimDAO().findAll();
        List<Claim> c = new ArrayList<>();
        for (Claim claim : claims) {
            if (!claim.isIsTreated()) {
                c.add(claim);
            }
        }
        claims = c;
    }

    public List<Forum> getCreationDemands() {
        return creationDemands;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public List<Forum> getForumsMessagesReports() {
        return forumsMessagesReports;
    }

    public Map<Message, User> getReportedMessages() {
        return reportedMessages;
    }

    public Map<Forum, User> getForumReports() {
        return forumReports;
    }

    public void setForumReports(Map<Forum, User> forumReports) {
        this.forumReports = forumReports;
    }

    public List<House> getHousesCommentsReports() {
        return HousesCommentsReports;
    }

    public void setHousesCommentsReports(List<House> HousesCommentsReports) {
        this.HousesCommentsReports = HousesCommentsReports;
    }

    public List<Comment> getReportedComments() {
        return reportedComments;
    }

    public void setReportedComments(List<Comment> reportedComments) {
        this.reportedComments = reportedComments;
    }

}
