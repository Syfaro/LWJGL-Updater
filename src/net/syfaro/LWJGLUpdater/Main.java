package net.syfaro.LWJGLUpdater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GUI.main();
    }

    public static void load() throws FileNotFoundException {
        GUI.jButton1.setEnabled(false);
        GUI.status.setText("Loading");

        File workDir = OS.getWorkingDirectory();

        List<String> natives = getNativesDownload(getNatives());
        List<String> jars = getJarsDownload(getJars());

        if (!new File(workDir + "/bin/natives").exists()) {
            new File(workDir + "/bin/natives").mkdir();
        }

        for (String n : natives) {
            String name = n.split("/")[n.toString().split("/").length - 1];
            String folder = workDir + "/bin/natives";
            Get.Download(n.toString(), folder, name);
        }

        for (String j : jars) {
            String name = j.split("/")[j.toString().split("/").length - 1];
            String folder = workDir + "/bin";
            Get.Download(j.toString(), folder, name);
        }

        GUI.status.setText("done");
    }

    public static String[] getJars() {
        String[] jars = new String[3];

        jars[0] = "jinput.jar";
        jars[1] = "lwjgl.jar";
        jars[2] = "lwjgl_util.jar";

        return jars;
    }

    public static String[] getNatives() {
        String[] natives = null;

        switch (OS.getPlatform()) {
            case WINDOWS:
                natives = new String[8];

                natives[0] = "jinput-dx8.dll";
                natives[1] = "jinput-dx8_64.dll";
                natives[2] = "jinput-raw.dll";
                natives[3] = "jinput-raw_64.dll";
                natives[4] = "lwjgl.dll";
                natives[5] = "lwjgl64.dll";
                natives[6] = "OpenAL32.dll";
                natives[7] = "OpenAL64.dll";

                break;
            case LINUX:
                natives = new String[6];

                natives[0] = "libjinput-linux.so";
                natives[1] = "libjinput-linux64.so";
                natives[2] = "liblwjgl.so";
                natives[3] = "liblwjgl64.so";
                natives[4] = "libopenal.so";
                natives[5] = "libopenal64.so";

                break;
            case MACOS:
                natives = new String[3];

                natives[0] = "libjinput-osx.jnilib";
                natives[1] = "liblwjgl.jnilib";
                natives[2] = "openal.dylib";

                break;
        }

        return natives;
    }

    public static ArrayList<String> getJarsDownload(String[] jars) {
        ArrayList jar = new ArrayList();

        for (String s : jars) {
            jar.add("http://vps.syfaro.net/lwjgl/jar/" + s);
        }

        return jar;
    }

    public static ArrayList<String> getNativesDownload(String[] natives) {
        ArrayList nat = new ArrayList();

        String platform = OS.getPlatform().getName();

        for (String s : natives) {
            nat.add("http://vps.syfaro.net/lwjgl/native/" + platform + "/" + s);
        }

        return nat;
    }
}
