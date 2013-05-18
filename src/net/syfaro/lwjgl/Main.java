package net.syfaro.lwjgl;

import java.util.*;

public class Main {
    private static List<String> currOS = null;
    public static HashMap versions;
    public static HashMap defaults;
    public static String shortOS;
    public static String downloadLocation;

    public static void main(String[] args){
        shortOS = shortOS();
        setDownloadLocation();
        
        getXml();

        HashMap information = null;
        try {
            information = XMLLoader.loadInformation();
        } catch (Exception ex) {
            System.exit(1);
        }
        
        versions = (HashMap) information.get("versions");
        defaults = (HashMap) information.get("defaults");
        
        UI.main(null);
    }
    
    public static void setDownloadLocation() {
        if("win".equals(shortOS)) {
            downloadLocation = System.getenv("APPDATA") + "\\.minecraft\\";
        } else if("mac".equals(shortOS)) {
            downloadLocation = System.getProperty("user.home") + "/Library/Application Support/minecraft/";
        } else {
            downloadLocation = System.getProperty("user.home") + "/.minecraft/";
        }
        System.out.println(downloadLocation);
    }

    public static void getXml() {
        UpdaterFunctions.DownloadFile(downloadLocation + "lwjgl_version.xml", "http://lwjgl.syfaro.net/lwjgl_version_v2.xml");
    }

    public static boolean currentOperatingSystem(String wanted) {
        if(currOS == null) currOS = currOS();
        String[] foros = wanted.split(" ");
        for(String f : foros) {
            if(currOS.contains(f)) return true;
        }
        return false;
    }

    public static List<String> currOS() {
        String os = System.getProperty("os.name");
        List<String> oslist = new ArrayList<String>();
        if(os.toLowerCase().contains("win")) {
            oslist.add("win");
            return oslist;
        } else if(os.toLowerCase().contains("mac")) {
            oslist.add("mac");
            return oslist;
        } else {
            oslist.add("linux");
            return oslist;
        }
    }

    public static String shortOS() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().contains("win")) {
            return "win";
        } else if(os.toLowerCase().contains("mac")) {
            return "mac";
        } else {
            return "linux";
        }
    }
}
