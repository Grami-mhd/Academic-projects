/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author grami
 */
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter
{

  String description; // description du filtre
  String[] extensions; // liste des extensions

  // le constructeur pour une seule extension

  public ExtensionFileFilter(String description, String[] extensions)
  {
    super();
    this.description = description;
    this.extensions = (String[]) extensions;
  }

  // le constructeur pour une liste d'extensions

  public ExtensionFileFilter(String description, String extension)
  {
    this(description,new String[]{extension});
  }

  // redéfinition de la méthode accept

  @Override
  public boolean accept(File file)
  {
    if(file.isDirectory())
    {
      return true;
    }

    String nomFichier = file.getPath();
    int n = extensions.length;
    for(int i=0; i<n; i++)
    {
      if(nomFichier.endsWith(extensions[i]))
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getDescription()
  {
    return description;
  }
}