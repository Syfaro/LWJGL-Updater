package net.syfaro.LWJGLUpdater;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Get {
    public static boolean Download(String URL, String folder, String name) {
        try {
            URL website = new URL(URL);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fox = new FileOutputStream(new File(folder + "/" + name).getAbsolutePath());
            fox.getChannel().transferFrom(rbc, 0, 1 << 24);
            fox.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
