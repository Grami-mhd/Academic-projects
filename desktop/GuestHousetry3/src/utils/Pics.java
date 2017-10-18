/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entitiesDao.UserDAO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author grami
 */
public class Pics {

    public static Blob ImageToBlob(Image im) {
        if (im != null) {
            try {
                BufferedImage bfi = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
                bfi.getGraphics().drawImage(im, 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bfi, "jpg", baos);
                byte[] imageInByte = baos.toByteArray();
                Blob blob = DataSource.getData().getConnection().createBlob();
                blob.setBytes(1, imageInByte);
                return blob;
            } catch (IOException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Pics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static Image BlobToImage(Blob b) {

        if (b != null) {
            try {
                InputStream in = b.getBinaryStream();
                BufferedImage image = ImageIO.read(in);
                if (image != null) {
                   // System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                    return new ImageIcon(image).getImage();
                }

            } catch (SQLException | IOException ex) {
                Logger.getLogger(Pics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static Image ISToImage(InputStream is) {
        FileOutputStream fos = null;
        try {
            File image = new File("kjpg");
            fos = new FileOutputStream(image);
            byte[] buffer = new byte[1];
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            Image m= new ImageIcon(image.getAbsolutePath()).getImage();
            fos.close();
            return m;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
