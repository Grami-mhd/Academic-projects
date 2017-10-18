/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.Forum;
import entities.Notification;
import entities.User;
import entitiesDao.ForumDAO;
import entitiesDao.NotificationDAO;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author grami
 */
public class ForumsJob {

    private Forum f;
    private User owner;
    private int members;

    public ForumsJob(Forum f) {

        ForumDAO fd = new ForumDAO();
        this.f = f;
        owner = fd.findOwnerById(f.getId());
        this.f.setMessages();
        members = f.getMessages().size();

    }

    public ForumsJob(int forumID) {

        ForumDAO fd = new ForumDAO();
        f = fd.findById(forumID);
        owner = fd.findOwnerById(forumID);
        f.setMessages();

    }

    public User getOwner() {
        return owner;
    }

    public int getMembers() {
        return members;
    }

    public void setF(Forum f) {
        this.f = f;
    }

    public void updateForum() {
        new NotificationDAO().add(new Notification("your demand to create a forum named :'" + f.getSubject() + "' has been approved", new Date(Calendar.getInstance().getTimeInMillis()),"forum"+f.getId()), owner.getId());
        new ForumDAO().update(f);
    }

    public void deleteForum() {
        new NotificationDAO().add(new Notification("we are sorry ri tell you that your demand to create a forum named :'" + f.getSubject() + "' has been declined", new Date(Calendar.getInstance().getTimeInMillis()),null), owner.getId());
        new ForumDAO().removeById(f.getId());
    }

    public Forum getForum() {
        return f;
    }

}
