package io.github.redstoneboy0509.javaez.extensions;

import io.github.redstoneboy0509.javaez.backend.ErrorSystem;

import java.io.*;

/**
 * The Files extension for handling I/O with files.
 * @author RedstoneBoy0509
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class Files {

    /**
     * The current reader. Used for reading files.
     * @since 1.0
     */
    private static BufferedReader currentReader;
    /**
     * The current writer. Used for writing files.
     * @since 1.0
     */
    private static BufferedWriter currentWriter;

    /**
     * Opens a file in READ mode. Without calling this, {@link #readLine()} will not work.
     * @param path The path of the file to read.
     * @since 1.0
     */
    public static void openFileForReading(String path) {
        try {
            closeFile();
            File file = new File(path);
            if(!file.exists()) file.createNewFile();
            if(file.isDirectory()) {
                ErrorSystem.handleError("Error while opening file, it's actually a folder");
                return;
            }
            currentReader = new BufferedReader(new FileReader(file));
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while opening file");
        }
    }

    /**
     * Opens a file in WRITE mode. Without calling this, {@link #writeLine(String)} will not work.
     * @param path Path of the file to write.
     * @param append If we should append to the file. If true, new lines will be added. If false, the file will be replaced.
     * @since 1.0
     */
    public static void openFileForWriting(String path, boolean append) {
        try {
            closeFile();
            File file = new File(path);
            if(!file.exists()) file.createNewFile();
            if(file.isDirectory()) {
                ErrorSystem.handleError("Error while opening file, it's actually a folder");
                return;
            }
            currentWriter = new BufferedWriter(new FileWriter(file, append));
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while opening file");
        }
    }

    /**
     * Closes any open files. You must run this after you are finished reading/writing to a file.
     * @since 1.0
     */
    public static void closeFile() {
        try {
            if(currentReader != null) currentReader.close();
            if(currentWriter != null) currentWriter.close();
            currentReader = null;
            currentWriter = null;
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while closing file");
        }
    }

    /**
     * Reads a line of a READ-MODE file. You must open a file this way using {@link #openFileForReading(String)} or this will not work.
     * @return The line read from the file, null if EOF
     * @since 1.0
     */
    public static String readLine() {
        if(currentReader == null) {
            ErrorSystem.handleError("Current reader is null, please open a file for reading!");
            return "";
        }
        try {
            return currentReader.readLine();
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while reading a file");
            return "";
        }
    }

    /**
     * Writes a line to a WRITE-MODE file. You must open a file this way using {@link #openFileForWriting(String, boolean)} or this will not work.
     * @param line What to write to the file.
     * @since 1.0
     */
    public static void writeLine(String line) {
        if(currentWriter == null) {
            ErrorSystem.handleError("Current writer is null, please open a file for writing!");
        }
        try {
            currentWriter.write(line + "\n");
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while writing to file!");
        }
    }

}
