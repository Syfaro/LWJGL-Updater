package net.syfaro.LWJGLUpdater;

import java.io.File;
import java.io.FileNotFoundException;

public enum OS {

    LINUX("linux"),
    WINDOWS("windows"),
    MACOS("macosx"),
    UNKNOWN("unknown");
    private String name;

    private OS(String aN) {
        name = aN;
    }

    public static OS getPlatform() {
        String str = System.getProperty("os.name").toLowerCase();
        if (str.contains("win")) {
            return OS.WINDOWS;
        }

        if (str.contains("mac")) {
            return OS.MACOS;
        }

        if (str.contains("linux")) {
            return OS.LINUX;
        }

        if (str.contains("unix")) {
            return OS.LINUX;
        }

        return OS.UNKNOWN;
    }

    public static File getWorkingDirectory() throws FileNotFoundException {
        return getWorkingDirectory("minecraft");
    }

    public static File getWorkingDirectory(String paramString) throws FileNotFoundException {
        String str1 = System.getProperty("user.home", ".");
        File localFile;
        switch (getPlatform()) {
            case LINUX:
                localFile = new File(str1, '.' + paramString + File.separator);
                break;
            case WINDOWS:
                String str2 = System.getenv("APPDATA");
                String str3 = str2 != null ? str2 : str1;

                localFile = new File(str3, '.' + paramString + File.separator);
                break;
            case MACOS:
                localFile = new File(str1, "Library" + File.separator + "Application Support" + File.separator + paramString);
                break;
            default:
                localFile = new File(str1, paramString + File.separator);
        }

        if ((!localFile.exists()) && (!localFile.mkdirs())) {
            throw new FileNotFoundException("The working directory could not be created: " + localFile);
        }

        return localFile;
    }

    public String getName() {
        return name;
    }
}
