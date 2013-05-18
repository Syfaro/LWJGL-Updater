package net.syfaro.lwjgl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdaterFunctions {
    public static boolean DownloadFile(String name, String uri) {
        URL link;
        try {
            link = new URL(uri);
        } catch (MalformedURLException e) {
            return false;
        }

        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(link.openStream());
        } catch (IOException e) {
            return false;
        }

        FileOutputStream fox;
        try {
            fox = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            fox.getChannel().transferFrom(rbc, 0, 1 << 24);
        } catch (IOException e) {
            return false;
        }
        
        try {
            fox.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdaterFunctions.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        System.out.println(uri + " -> " + name);

        return true;
    }

    public static boolean MoveFile(String oldFile, String newFile) {
        File oldF = new File(oldFile);
        File newF = new File(newFile);

        try {
            oldF.renameTo(newF);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public static boolean CopyFile(String file, String newFile) throws IOException {
        FileChannel source = null;
        FileChannel dest = null;

        try {
            source = new FileInputStream(new File(file)).getChannel();
            dest = new FileOutputStream(new File(newFile)).getChannel();
            dest.transferFrom(source, 0, source.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(source != null) {
                source.close();
            }
            if(dest != null) {
                dest.close();
            }
        }

        return true;
    }

    public static boolean DeleteFile(String file) {
        try {
            new File(file).delete();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return false;
    }
}
