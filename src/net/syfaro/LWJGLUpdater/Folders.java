package net.syfaro.LWJGLUpdater;

import java.io.File;

public class Folders {
    
    public static File workDir = null;
    
    public static OS getPlatform() {
        String str = System.getProperty("os.name").toLowerCase();
        if (str.contains("win")) {
              return OS.WINDOWS;
        }
        
        if (str.contains("mac")) {
              return OS.MACOS;
        }
        
        if (str.contains("solaris")) {
              return OS.SOLARIS;
        }
        
        if (str.contains("sunos")) {
              return OS.SOLARIS;
        }
        
        if (str.contains("linux")) {
              return OS.LINUX;
        }
        
        if (str.contains("unix")) {
              return OS.LINUX;
        }
        
        return OS.UNKNOWN;
    }

    public static File getWorkingDirectory() {
        if (workDir == null) {
              workDir = getWorkingDirectory("minecraft");
        }
        
        return workDir;
    }

    public static File getWorkingDirectory(String paramString) {
        String str1 = System.getProperty("user.home", ".");
        File localFile;
        switch (getPlatform()) {
            case LINUX:
                localFile = new File(str1, '.' + paramString + '/');
                break;
            case WINDOWS:
                String str2 = System.getenv("APPDATA");
                String str3 = str2 != null ? str2 : str1;

                localFile = new File(str3, '.' + paramString + '/');
                break;
            case MACOS:
                localFile = new File(str1, "Library/Application Support/" + paramString);
                break;
            default:
                localFile = new File(str1, paramString + '/');
        }

        if ((!localFile.exists()) && (!localFile.mkdirs())) {
            throw new RuntimeException("The working directory could not be created: " + localFile);
        }

        return localFile;
    }
  
    public static enum OS {
        LINUX, 
        SOLARIS, 
        WINDOWS, 
        MACOS, 
        UNKNOWN;
    }

}
