package io.github.redstoneboy0509.javaez.extensions;

import io.github.redstoneboy0509.javaez.backend.BackendVariableContext;
import io.github.redstoneboy0509.javaez.backend.ErrorSystem;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * The core functions of JavaEZ, usually implemented in every JavaEZ project.
 * @author RedstoneBoy0509
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class Core {

    /**
     * Says something in the console. An example of using this: {@code say("Hello");}
     * @param text The text you want to be said in the console.
     * @since 1.0
     */
    public static void say(String text) {
        try {
            System.out.println(text);
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while saying something in the console, text is " + text);
        }
    }

    /**
     * Checks if something is true and does something if it is.
     * @param condition The condition to check
     * @param ifTrue What to do if true
     * @since 1.0
     * @see #doIf(boolean, Runnable, Runnable)
     */
    public static void doIf(boolean condition, Runnable ifTrue) {
        try {
            if (condition) {
                ifTrue.run();
            }
        } catch(Exception ex) {
            ErrorSystem.handleError("Error in an IF statement without else");
        }
    }

    /**
     * Checks if something is true and does something if it is. This one also runs something if not true.
     * @param condition The condition to check
     * @param ifTrue What to do if true
     * @param elseDo What to do if false
     * @since 1.0
     * @see #doIf(boolean, Runnable)
     */
    public static void doIf(boolean condition, Runnable ifTrue, Runnable elseDo) {
        try {
            if (condition) {
                ifTrue.run();
            } else {
                elseDo.run();
            }
        } catch(Exception ex) {
            ErrorSystem.handleError("Error in an IF statement with else");
        }
    }

    /**
     * Sets/adds a variable
     * @param name The name of the variable
     * @param val The value of the variable
     * @since 1.0
     */
    public static void set(String name, String val) {
        try {
            BackendVariableContext.addOrModifyVariable(name, val);
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while setting a variable");
        }
    }

    /**
     * Gets a variable
     * @param name Name of the variable
     * @return The variable's value, blank if none
     * @since 1.0
     */
    public static String get(String name) {
        try {
            return BackendVariableContext.getVariable(name);
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while getting a variable");
            return "";
        }
    }

    /**
     * Deletes a variable
     * @param name The name of the variable
     * @since 1.0
     */
    public static void remove(String name) {
        try {
            BackendVariableContext.removeVariable(name);
        } catch(Exception ex) {
            ErrorSystem.handleError("Error while removing a variable");
        }
    }

    /**
     * Runs code forever
     * @param toRun What to run
     * @since 1.0
     */
    public static void forever(Runnable toRun) {
        try {
            while (true) {
                toRun.run();
            }
        } catch(Exception ex) {
            ErrorSystem.handleError("Error in a forever loop");
        }
    }

    /**
     * Waits for a number of seconds
     * @param seconds The seconds to wait for
     * @since 1.0
     */
    public static void waitFor(int seconds) {
        long startTime = System.currentTimeMillis();
        boolean keepGoing = true;
        while(keepGoing) {
            long elapsed = System.currentTimeMillis() - startTime;
            Duration dur = Duration.ofMillis(elapsed);
            if(dur.getSeconds() >= seconds) keepGoing = false;
        }
    }

}
