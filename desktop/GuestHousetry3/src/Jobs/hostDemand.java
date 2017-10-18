/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import entities.HostingDemande;
import entities.Notification;
import entities.User;
import entitiesDao.HostingDemandeDAO;
import entitiesDao.NotificationDAO;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Mohamed
 */
public class hostDemand {
    private NotificationDAO ndao;
    private HostingDemandeDAO hdao;
    
    private User u;
    private HostingDemande hd;

    public hostDemand(User u, HostingDemande hd) {
        ndao = new NotificationDAO();
        hdao = new HostingDemandeDAO();
        this.u = u;
        this.hd = hd;
        
    }

    public void addAcceptNotification(){
        
        ndao.add(new Notification("Hosting Demand: your hosting demand has been accepted!!",new Date(Calendar.getInstance().getTimeInMillis()), "hd"+hd.getHouse().getId()) ,u.getId());
    
    }
    
    public void addDeclineNotification(){
        
        ndao.add(new Notification("Sorry : your hosting demand has been declined!!",new Date(Calendar.getInstance().getTimeInMillis()),null),u.getId() );
    
    }
    
    public void removeHostingDemand(){
        
        hdao.removeById(hd.getId());
    }
    
    
    
    
        
  
}
