/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import entities.House;
import entities.User;
import entitiesDao.HouseDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nabtaaa
 */
public class HouseModel extends AbstractTableModel {

    private HouseDAO hdao;
    private List<House> l;
    private String[] Headers = {"Owner", "Country", "Town", "Price", "Rate", "Equipments", "Nature"};

    public HouseModel() {
        hdao = new HouseDAO();
        l = hdao.findAll();
    }

    @Override
    public int getRowCount() {
        return l.size();
    }

    public List<House> getL() {
        return l;
    }

    

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                User u = hdao.findOwnerById(l.get(rowIndex).getId());
                return u.getFirstName() + " " + u.getLastName();
            }
            case 1:
                return l.get(rowIndex).getCountry();
            case 2:
                return l.get(rowIndex).getTown();
            case 3:
                return l.get(rowIndex).getPrice();
            case 4:
                return l.get(rowIndex).getRating();
            case 5:
                return l.get(rowIndex).getEquipment();
            case 6:
                return l.get(rowIndex).getNature();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Headers[column];
    }
}
/**
 *
 * @author nabtaaa
 */
