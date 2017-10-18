/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparation;

import entities.Add;
import java.util.Comparator;

/**
 *
 * @author grami
 */
public class AddComparationRate implements Comparator<Add>{

    @Override
    public int compare(Add o1, Add o2) {
        return o2.getRating()-o1.getRating();
    }
    
}
