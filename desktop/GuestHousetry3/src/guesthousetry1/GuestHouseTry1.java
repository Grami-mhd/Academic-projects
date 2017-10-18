/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guesthousetry1;

import GUI.home;
import entitiesDao.UserDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author grami
 */
public class GuestHouseTry1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       
        try {
            
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            new home().setVisible(true);
            
            
        } catch (Exception ex) {
            Logger.getLogger(GuestHouseTry1.class.getName()).log(Level.SEVERE, null, ex);
        }
        final String folderPath = "E:\\esprit\\Pidev 4infoB CC 16\\GuestHouseTry1";
        
        long totalLineCount = 0;
        final List<File> folderList = new LinkedList<>();
        folderList.add(new File(folderPath));
        while (!folderList.isEmpty()) {
        final File folder = folderList.remove(0);
        if (folder.isDirectory() && folder.exists()) {
        System.out.println("Scanning " + folder.getName());
        final File[] fileList = folder.listFiles();
        for (final File file : fileList) {
        if (file.isDirectory()) {
        folderList.add(file);
        } else if (file.getName().endsWith(".java")
        || file.getName().endsWith(".sql")) {
        try {
        long lineCount = 0;
        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
        scanner.nextLine();
        lineCount++;
        }
        totalLineCount += lineCount;
        final String lineCountString;
        if (lineCount > 99999) {
        lineCountString = "" + lineCount;
        } else {
        final String temp = ("     " + lineCount);
        lineCountString = temp.substring(temp.length() - 5);
        }
        System.out.println(lineCountString + " lines in " + file.getName());
        } catch (FileNotFoundException ex) {
        Logger.getLogger(GuestHouseTry1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        }
        }
        System.out.println("Scan Complete: " + totalLineCount + " lines total");
          
    }
}
