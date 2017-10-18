/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ClaimsDAO;
import Entities.House;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ROGUE_BIBO
 */
public class ClaimStayGUI {
    
    public Container ajoutclaim(House c){
        
        Container all=new Container(BoxLayout.y());
        
        Label l=new Label("If you want to claim about your last stay");
        Label l1=new Label("tape your reason here and we will deal");
        Label l2=new Label("with it :");
        TextArea txt=new TextArea(5,30);
        Button b=new Button("Valider");
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ClaimsDAO().addClaim(c.getId(),txt.getText());
               all.removeAll();
               all.add( new ClaimStayGUI().ajoutclaim(c));
               all.repaint();
                Dialog.show("Add Claim","Your Claim was added successfully","OK",null);
            }
        });
        
        all.add(l);
        all.add(l1);
        all.add(l2);
        all.add(txt);
        all.add(b);
        
        
        
    return all;
}
    
}
