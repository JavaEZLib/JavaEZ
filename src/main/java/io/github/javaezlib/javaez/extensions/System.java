package io.github.javaezlib.javaez.extensions;

import io.github.javaezlib.javaez.backend.ErrorSystem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * The system extension, allowing the user to check system info
 * @author RedstoneBoy0509
 * @since 1.2
 */
@SuppressWarnings("deprecation")
public class System {

    /**
     * Gets the OS name of the one who runs the program. This info is delivered by the system and may look a bit technical. (Except for Windows)
     * @return The system-provided OS name
     * @since 1.2
     */
    public static String whatsMyOS() {
        return java.lang.System.getProperty("os.name");
    }

    /**
     * Gets the OS name of the one who runs the program. However, unlike {@link #whatsMyOS()} this info is simplified. Ex. Windows Version 10
     * @return The simplified OS name (If OS could not be simplified, it will equal {@link #whatsMyOS()})
     * @since 1.2
     */
    public static String whatsMySimplifiedOS() {
        String osName = whatsMyOS();
        String osVersion = java.lang.System.getProperty("os.version");
        String osSimplifiedName = null;
        if(osName.toLowerCase().contains("win")) {
            osSimplifiedName = "Windows";
        } else if(osName.toLowerCase().contains("mac")) {
            osSimplifiedName = "macOS";
        } else if(osName.toLowerCase().contains("nix") || osName.toLowerCase().contains("nux") || osName.toLowerCase().contains("aix")) {
            osSimplifiedName = "Linux UNIX";
        } else if(osName.toLowerCase().contains("sunos")) {
            osSimplifiedName = "Solaris";
        }
        return osSimplifiedName != null ? osSimplifiedName + " Version " + osVersion : osName;
    }

    /**
     * Returns the username of the one who is using the PC
     * @return who is using the PC
     * @since 1.2
     */
    public static String whoIsUsingThePC() {
        return java.lang.System.getProperty("user.name");
    }

    /**
     * Places the user on the sign-in screen without closing any apps. (You have to be on Windows for this to work)
     * @since 1.2
     */
    public static void lockMyPC() {
        if(!whatsMyOS().toLowerCase().contains("win")) {
            ErrorSystem.handleError("You are not on Windows!");
            return;
        }
        try {
            Runtime.getRuntime().exec("rundll32 user32.dll,LockWorkStation");
        } catch (IOException e) {
            ErrorSystem.handleError("Error while locking PC");
        }
    }

    /**
     * Opens a path in Explorer/Finder/whatever file viewer
     * @param path the path to open
     * @since 1.2
     */
    public static void openAPath(String path) {
        try {
            Desktop.getDesktop().browse(new URI(path));
        } catch (Exception e) {
            ErrorSystem.handleError("Could not open path");
        }
    }

    /**
     * Prints a file using whatever print system your OS uses
     * @param path the path of the file to print
     * @since 1.2
     */
    public static void printAFile(String path) {
        try {
            Desktop.getDesktop().print(new File(path));
        } catch(Exception ex) {
            ErrorSystem.handleError("Could not print this file");
        }
    }

}
