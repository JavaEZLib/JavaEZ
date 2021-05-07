package io.github.redstoneboy0509.javaez.extensions;

import io.github.redstoneboy0509.javaez.backend.ErrorSystem;

import java.util.UUID;

/**
 * Threads extension for JavaEZ, allowing you to run something parallel to your other code
 * @author RedstoneBoy0509
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class Threads {

    /**
     * Allows you to run code parallel to your other code. Example: {@code runParallel(() -> say("Hello"));} would say Hello but not in the same thread.
     * Useful if you want to run a loop without stopping your own code.
     * @param run What to run parallel
     * @since 1.0
     */
    public static void runParallel(Runnable run) {
        try {
            UUID threadId = UUID.randomUUID();
            Thread thread = new Thread(run, "JavaEZ-" + threadId);
            thread.start();
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while running a piece of code parallel");
        }
    }

}
