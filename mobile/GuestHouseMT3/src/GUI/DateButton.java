/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author grami
 */
public class DateButton  extends Button{
    
    private Boolean selected = false;
    public  static int count =0;
    public static List<String> selectedDates= new ArrayList<>();
    
    public DateButton() {
    }

    public DateButton(String text) {
        super(text);
    }

    public DateButton(Command cmd) {
        super(cmd);
    }

    public DateButton(Image icon) {
        super(icon);
    }

    public DateButton(String text, Image icon) {
        super(text, icon);
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
        if(selected){
            count++;
            try {
                this.setIcon(Image.createImage("/selected.png").scaled(50, 50));
            } catch (IOException ex) {
            }
            selectedDates.add(this.getText());
        }else{
            count--;
            this.setIcon(null);
            selectedDates.remove(this.getText());
        }
        this.repaint();
    }
    
    
    
    
}
