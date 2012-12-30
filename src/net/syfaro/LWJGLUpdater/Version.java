package net.syfaro.LWJGLUpdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Version {

    private File workingDir = null;
    private GUI gui = null;

    public Version(File workDir, GUI g) {
        workingDir = workDir;
        gui = g;
    }

    public String getCurrentVersion() throws FileNotFoundException, IOException {
        File vFile = new File(workingDir + "/bin/natives/lwjgl_version");
        String line;

        if (vFile.exists()) {
            FileReader input = new FileReader(vFile);
            BufferedReader br = new BufferedReader(input);

            line = br.readLine();

            gui.getCurVer().setText(line);

            br.close();
        } else {
            gui.getCurVer().setText("Unknown");
            return "false";
        }

        return line;
    }

    private String getNewestVersion() throws FileNotFoundException, IOException {
        Get.Download("http://vps.syfaro.net/lwjgl/lwjgl_version", workingDir + "/bin/natives/lwjgl_version_temp");

        File vFile = new File(workingDir + "/bin/natives/lwjgl_version_temp");
        String line;

        if (vFile.exists()) {
            FileReader input = new FileReader(vFile);
            BufferedReader br = new BufferedReader(input);

            line = br.readLine();

            gui.getNewVer().setText(line);

            br.close();
        } else {
            gui.getNewVer().setText("Failed to load");
            return "false";
        }

        vFile.deleteOnExit();

        return line;
    }

    public boolean isUpToDate() {
        String curr = null;

        try {
            curr = getCurrentVersion();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("false".equals(curr)) {
            try {
                getNewestVersion();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        } else {
            try {
                if (curr.equals(getNewestVersion())) {
                    return true;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Version.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean saveNewVersion() {
        File vFile = new File(workingDir + "/bin/natives/lwjgl_version");
        File tvFile = new File(workingDir + "/bin/natvies/lwjgl_version_temp");

        if (!vFile.exists()) {
            Get.Download("http://vps.syfaro.net/lwjgl/lwjgl_version", workingDir + "/bin/natives/lwjgl_version");
        } else {
            vFile.delete();
            if (!tvFile.renameTo(new File(workingDir + "/bin/natives/lwjgl_version"))) {
                System.out.println("could not rename file, resorting to alternate method");
                Get.Download("http://vps.syfaro.net/lwjgl/lwjgl_version", workingDir + "/bin/natives/lwjgl_version");
            }
        }

        return true;
    }
}
