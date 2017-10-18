/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jobs;

import comparation.AddComparationEquipments;
import comparation.AddComparationPrice;
import comparation.AddComparationRate;
import entities.*;
import entitiesDao.AddDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author grami
 */
public class AddModel {

    private List<Add> adds;
    private AddDAO a;

    public AddModel() {
        a = new AddDAO();
        adds = a.findAll();
    }

    public void findByDate(Date d){
        List<Add> e = new ArrayList<>();
        for (Add add : adds) {
            
            for (Date d1 : add.getDates()) {
                if(d1.toString().equals(d.toString()))
                    e.add(add);
            }
            
        }
        adds = e;
    }
    
    public List<Add> getAdds() {
        return adds;
    }

    public void setAdds(List<Add> adds) {
        this.adds = adds;
    }

    public void setPriceMin(int x) {
        List<Add> e = new ArrayList<>();

        for (Add add : adds) {
            if (add.getPrice() > x) {
                e.add(add);
            }
        }
        adds = e;
    }

    public void SortByPrice() {
        this.adds.sort(new AddComparationPrice());

    }

    public void SortByrating() {
        this.adds.sort(new AddComparationRate());
    }

    public void SortByEquipments() {
        this.adds.sort(new AddComparationEquipments());
    }

    public void findByTown(String c, String t) {
        List<Add> e = new ArrayList<>();

        if (!t.equals("...")) {
            for (Add add : adds) {
                if (add.getTown().equals(t) && add.getCountry().equals(c)) {
                    e.add(add);
                }
            }
            adds = e;
        }
    }

    public void findByRating(int x) {
        List<Add> e = new ArrayList<>();

        for (Add add : adds) {
            if (add.getRating() > x) {
                e.add(add);
            }
        }
        adds = e;
    }

    public void searchByNature(String s) {
        List<Add> e = new ArrayList<>();
        for (Add add : adds) {
            if (add.getNature().equals(s)) {
                e.add(add);
            }
            System.out.println(add.getNature());
        }
        adds = e;
    }

    public void findByEquipments(List<String> l) {
        if(l.isEmpty()){
            adds=a.findAll();
            return;
        }
        System.out.println(l);
        List<Add> e = new ArrayList<>();
        List<List<Add>> le = new ArrayList<>();
        
        for (String s : l) {
            e.clear();
            switch (s) {

                case "airconditioner":

                    for (Add add : adds) {
                        if (add.isAirconditioner()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "fireplace":
                    for (Add add : adds) {
                        if (add.isFireplace()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "washingmachine":
                    for (Add add : adds) {
                        if (add.isWashingmachine()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "dishwasher":
                    for (Add add : adds) {
                        if (add.isDishwasher()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "dvd":
                    for (Add add : adds) {
                        if (add.isDvd()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "indoorpool":
                    for (Add add : adds) {
                        if (add.isIndoorpool()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "sauna":
                    for (Add add : adds) {
                        if (add.isSauna()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "tv":
                    for (Add add : adds) {
                        if (add.isTv()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "jacuzzi":
                    for (Add add : adds) {
                        if (add.isJacuzzi()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "elevator":
                    for (Add add : adds) {
                        if (add.isElevator()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "barbecue":

                    for (Add add : adds) {
                        if (add.isBarbecue()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "garden":
                    adds.stream().filter((add) -> (add.isGarden())).forEach((add) -> {
                        e.add(add);
            });
                    le.add(e);
                    break;
                case "childrengames":
                    for (Add add : adds) {
                        if (add.isChildrengames()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "outdoorparking":
                    for (Add add : adds) {
                        if (add.isOutdoorparking()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "indoorparking":
                    for (Add add : adds) {
                        if (add.isIndoorparking()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "gardenfurniture":
                    for (Add add : adds) {
                        if (add.isGardenfurniture()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                case "terrace":
                    for (Add add : adds) {
                        if (add.isTerrace()) {
                            e.add(add);
                        }
                    }
                    le.add(e);
                    break;
                default:
                    break;
            }

        }

        Set<Add> s = new HashSet<>();
        if (!le.isEmpty()) {
            for (Add add : le.get(0)) {
                int i = 0;
                while ((i < le.size()) && le.get(i).contains(add)) {
                    i++;
                }
                if (i == le.size()) {
                    s.add(add);
                }
            }
        }

        List<Add> e1 = new ArrayList<>();
        for (Add add : s) {
            e1.add(add);
        }
        adds = e1;

    }
    
    public void findByOtherEquipment(String s){
        List<Add> d=new ArrayList<>();
        for (Add add : adds) {
            if(add.getOther().equals(s))
                d.add(add);
        }
        adds=d;
    }
}   
