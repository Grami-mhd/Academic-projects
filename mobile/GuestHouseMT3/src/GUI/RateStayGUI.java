/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.CommentDAO;
import DAO.RateDAO;
import Entities.House;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.guesthouse;
import java.io.IOException;


/**
 *
 * @author ROGUE_BIBO
 */
public class RateStayGUI {
    
     public Container RateStay(House c, int r, boolean israted){
         
     Container all=new Container(BoxLayout.y());    
     Container ratestay = new Container(BoxLayout.y());
     Container starsbutton = new Container(BoxLayout.x());
    
    Label l3=new Label("Welcome Back:");
    l3.getStyle().setFgColor(0xFF0000); 
         
         Label l4=new Label("Have you enjoy your last Stay :");
         
         Button b=new Button();
         Button b1=new Button();
         Button b2=new Button();
         Button b3=new Button();
         Button b4=new Button();
         
            try {
                b.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
            } catch (IOException ex) {
                
            }
            
            starsbutton.add(b);
            starsbutton.add(b1);
            starsbutton.add(b2);
            starsbutton.add(b3);
            starsbutton.add(b4);
            
            try {
                
           if((israted == true)&&(r==0)){
               
                b.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
            
           } else if ((israted == true)&&(r==1)) {
                
                
                b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));

                
                
           }else if ((israted == true)&&(r==2)) {
                
                b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));

                  
           } else if ((israted == true)&&(r==3)) {
                
                
                b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));

                        
            
           }else if ((israted == true)&&(r==4)) {
                
                
                b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));

                
           }else if ((israted == true)&&(r==5)) {
                
                
                b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b3.setIcon(Image.createImage("/star.png").scaled(30, 30));
                b4.setIcon(Image.createImage("/star.png").scaled(30, 30));

               
           }
           } catch (IOException ex) {    
            }
            
            b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             
                 if(!israted)
                 {
                     new RateDAO().addRate(1, c);
                 }
                 if(israted){
                     new RateDAO().updateRate(1, c);
                 }
               try {  
                 b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b1.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
             } catch (IOException ex) {
             }
               all.repaint();
         }
     });
              b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             
              if(!israted)
             {
                 new RateDAO().addRate(2, c);
             }
             if(israted){
                 new RateDAO().updateRate(2, c);
             }
             
              try {  
                 b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b2.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
             } catch (IOException ex) {
             }
               all.repaint();
         }
     });
                b2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             
              if(!israted)
             {
                 new RateDAO().addRate(3, c);
             }
             if(israted){
                 new RateDAO().updateRate(3, c);
             }
             
              try {  
                 b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b3.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
                 b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
             } catch (IOException ex) {
             }
               all.repaint();
         }
     });
                  b3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                
                 if(!israted)
             {
                 new RateDAO().addRate(4, c);
             }
             if(israted){
                 new RateDAO().updateRate(4, c);
             }
             try {
                 b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b3.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b4.setIcon(Image.createImage("/starblanc.png").scaled(30, 30));
             } catch (IOException ex) {
             }
               all.repaint();
         }
     });
                    b4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                
              if(!israted)
             {
                 new RateDAO().addRate(5, c);
             }
             if(israted){
                 new RateDAO().updateRate(5, c);
             }
             
              try {  
                 b.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b1.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b2.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b3.setIcon(Image.createImage("/star.png").scaled(30, 30));
                 b4.setIcon(Image.createImage("/star.png").scaled(30, 30));
             } catch (IOException ex) {
             }
               all.repaint();
         }
     });
            
             Label l5=new Label("Leave a note :");
             l5.getStyle().setFgColor(0x1153E1);
             
             TextArea txt=new TextArea(5,30);
             
             Button b5=new Button("Valider");
             b5.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             
                new CommentDAO().addComment(c.getId(),txt.getText());
                Dialog.show("Add Comment","Your Comment was added successfully","OK",null);
         }
     });
             Button b6=new Button("Claim about this stay !!!");
             
             b6.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                Form lf = guesthouse.getF();
                lf.removeAll();
                lf.add(new ClaimStayGUI().ajoutclaim(c));
                lf.repaint();
                 
             }
         });
             
            b6.getAllStyles().setBorder(Border.createEmpty());
            b6.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            
         ratestay.add(l3);
         ratestay.add(l4);
         ratestay.add(starsbutton);
         ratestay.add(l5);
         ratestay.add(txt);
         ratestay.add(b5);
         ratestay.add(b6);
         
         all.add(ratestay);
         
         return all;
         
     }         
    
}
