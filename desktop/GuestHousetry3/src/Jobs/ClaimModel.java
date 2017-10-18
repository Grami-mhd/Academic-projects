/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import entities.Claim;
import entities.House;
import entitiesDao.ClaimDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author asus
 */
public class ClaimModel extends AbstractTableModel{
    
    private List<Claim> cla;
    private Map<Claim ,House> Claims;
    private String[] Headers = {"claim", "status","date"};
    private Claim C ;
   private ClaimDAO ud=new ClaimDAO();
    private int ownerId;

    public ClaimModel() {
        ClaimDAO ud=new ClaimDAO();
        Claims = ud.findbyOwnerID(ownerId);
        cla=new ArrayList<>();
    
        for (Map.Entry<Claim ,House> entry : Claims.entrySet()) {
            Claim claim = entry.getKey();
            cla.add(claim);
            }
    }
    
    public  ClaimModel(int ownerId) {
        cla=new ArrayList<>();
        ClaimDAO ud=new ClaimDAO();
        Claims = ud.findbyOwnerID(ownerId);
        this.ownerId= ownerId;
        for (Map.Entry<Claim ,House> entry : Claims.entrySet()) {
            Claim claim = entry.getKey();
            cla.add(claim);
        }
    }
    
    public String getColumnName( int column){
        return Headers [column];
         }   
    
    @Override
    public int getRowCount() {
        return Claims.size() ;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
    
    switch (columnIndex) {
            case 0: 
                return cla.get(rowIndex).getText();
            case 1:
                return cla.get(rowIndex).getStatus();
            case 2:
                return cla.get(rowIndex).getDate();
            default:
                return null;
    }
    }
}
