package utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileUtils {
    
    //отримати і'мя файлу без розширення
    public static String getFileNameWithoutExtension (String fileName){
        File file = new File(fileName);
        int index = file.getName().lastIndexOf('.');
        if (index > 0 && index <= file.getName().length() - 2){
            return file.getName().substring(0, index);
        }
        return "noname";
    }

    //отримати розширення файлу
    public static String getFileExtension(File f){
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        
        if(i > 0 && i < s.length() - 1){
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    //видалити даний файл-фільтр і встановити новий переданий
    public static void addFileFilter (JFileChooser jfc, FileFilter ff){
        jfc.removeChoosableFileFilter(jfc.getFileFilter());
        jfc.setFileFilter(ff);
        jfc.setSelectedFile(new File(""));//видалити останнє ім'я відкриваного/зберігаємого файлу
    }
    
    //збберегти об'єкт
    public static void serialize(Object obj, String fileName){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //відкрити об'єкт
    public static Object deserialize(String fileName){
         try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream oin = new ObjectInputStream(fis);
            Object ts = (Object) oin.readObject();
            fis.close();
            return ts;

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
}
