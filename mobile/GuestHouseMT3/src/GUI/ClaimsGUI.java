/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Claim;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ROGUE_BIBO
 */
public class ClaimsGUI {

    public Container showClaims( List<Claim> lc ) {
        
        Container all = new Container(BoxLayout.y());

        Container claim ;
        
        Container claimContainer ;

        Container housepic;
        
        Container loctextdate ;

        Container location ;

        Container textdate ;

        for(Claim c : lc){
            
         claimContainer = new Container(BoxLayout.y());   
            
         claim = new Container(BoxLayout.x());

         housepic = new Container();
        
         loctextdate = new Container(BoxLayout.y());

        location = new Container(BoxLayout.x());

         textdate = new Container(BoxLayout.x());
        
        try {
            housepic.add(new ImageViewer(Image.createImage("/house.png").scaled(70,70)));
        } catch (IOException ex) {
        }
        
        Label l=new Label(c.getHouse().getCountry()+" "+c.getHouse().getTown());
        l.getStyle().setFgColor(0x1153E1);
        location.add(l);


        SpanLabel t = new SpanLabel(c.getText());
        Label d=new Label(c.getDate());
        
        textdate.add(t);
        textdate.add(d);

        loctextdate.add(location);
        loctextdate.add(textdate);
        
        claim.add(housepic);
        claim.add(loctextdate);
        
        claimContainer.add(claim);
        claimContainer.add("________________________________________________________________");
        
        all.add(claimContainer);
        
 
        }
        
        return all;
    }

}
