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
        List<String> natives = new ArrayList<String>();

        switch (OS.getPlatform()) {
            case WINDOWS:
                natives.add("jinput-dx8.dll");
                natives.add("jinput-dx8_64.dll");
                natives.add("jinput-raw.dll");
                natives.add("jinput-raw_64.dll");
                natives.add("lwjgl.dll");
                natives.add("lwjgl64.dll");
                natives.add("OpenAL32.dll");
                natives.add("OpenAL64.dll");
                break;
            case LINUX:
                natives.add("libjinput-linux.so");
                natives.add("libjinput-linux64.so");
                natives.add("liblwjgl.so");
                natives.add("liblwjgl64.so");
                natives.add("libopenal.so");
                natives.add("libopenal64.so");
                break;
            case MACOS:
                natives.add("libjinput-osx.jnilib");
                natives.add("liblwjgl.jnilib");
                natives.add("openal.dylib");
                break;
        }

        return natives.toArray(new String[0]);
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
