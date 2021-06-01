package io.github.javaezlib.javaez.backend;

/**
 * The backend JavaEZ error system, used for handling internal Java errors.
 * @author RedstoneBoy0509
 * @since 1.0
 */
public class ErrorSystem {

    /**
     * Sends a JavaEZ error to the console.
     * @param className The name of the erroring class
     * @param message The error message
     * @since 1.0
     * @deprecated This is an internal method, not needing to be used by the user.
     */
    @Deprecated
    public static void sendError(String className, String message) {
        System.err.println("[JavaEZ] [Error at class " + className + "] >> " + message);
    }

    /**
     * Handles a JavaEZ error, sending it to {@link #sendError(String, String)}
     * @param error The error message
     * @since 1.0
     * @deprecated This is an internal method, not needing to be used by the user.
     */
    @Deprecated
    public static void handleError(String error) {
        StackTraceElement e = Thread.currentThread().getStackTrace()[3];
        sendError(e.getClassName(), error);
    }

}
