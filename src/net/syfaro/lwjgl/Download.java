package net.syfaro.lwjgl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Download {
    public static boolean DownloadFile(String name, String uri) {
        URL link;
        try {
            link = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(link.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        FileOutputStream fox;
        try {
            fox = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            fox.getChannel().transferFrom(rbc, 0, 1 << 24);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            fox.close();
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        System.out.println(uri + " -> " + name);

        return true;
    }
}
