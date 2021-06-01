package io.github.javaezlib.javaez;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for JavaEZ
 * @author RedstoneBoy0509
 * @since 1.1
 */
public class JavaEZ {

    /**
     * The current version of JavaEZ.
     * @since 1.1
     */
    public static final String VERSION = "1.2";

    /**
     * Prints info about your version of JavaEZ
     * @since 1.1
     */
    public static void info() {
        System.out.println("=[JavaEZ Info]=");
        System.out.println("JavaEZ running on version " + VERSION);
        String latestVersion = getLatestVersion();
        boolean areWeUpdated = latestVersion.equalsIgnoreCase(VERSION);
        if(!areWeUpdated) {
            System.out.println("Attention: your JavaEZ is not at latest version, please consider updating!");
            System.out.println("Latest version: " + latestVersion);
        } else System.out.println("JavaEZ is up to date!");
    }

    /**
     * Gets the latest version
     * @return The latest version
     * @since 1.1
     */
    public static String getLatestVersion() {
        List<String> versions = getVersions();
        return versions.isEmpty() ? "Unknown" : versions.get(versions.size() - 1);
    }

    /**
     * Returns a list of all known versions on the Internet
     * @return a list of all existent versions
     * @since 1.1
     */
    public static List<String> getVersions() {
        try {
            URL url = new URL("https://gist.githubusercontent.com/RedstoneBoy0509/fb10258f9ae7d858f94b8cbaa651548f/raw/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            List<String> list = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                if(line.isEmpty()) continue;
                list.add(line);
            }
            reader.close();
            return list;
        } catch(Exception ex) {
            return new ArrayList<>();
        }
    }

}
