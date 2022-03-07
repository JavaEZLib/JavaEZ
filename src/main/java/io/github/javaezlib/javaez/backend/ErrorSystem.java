package io.github.javaezlib.javaez.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * The backend JavaEZ error system, used for handling internal Java errors.
 * @author Red050911
 * @since 1.0
 */
public class ErrorSystem {

    /**
     * The list of classnames allowed to call errors.
     * @since 1.5
     */
    private static final List<String> ACCEPTED_CLASSNAMES = new ArrayList<>();

    static {
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.extensions.Core");
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.extensions.Files");
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.extensions.System");
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.extensions.Threads");
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.backend.BackendVariableContext");
        ACCEPTED_CLASSNAMES.add("io.github.javaezlib.javaez.JavaEZ");
    }

    /**
     * Sends a JavaEZ error to the console.
     * @param className The name of the erroring class
     * @param message The error message
     * @since 1.0
     * @deprecated This is an internal method, not needing to be used by the user.
     */
    @Deprecated
    private static void sendError(String className, String message) {
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
        try {
            StackTraceElement e = Thread.currentThread().getStackTrace()[2];
            if(ACCEPTED_CLASSNAMES.contains(e.getClassName())) {
                e = Thread.currentThread().getStackTrace()[3];
                sendError(e.getClassName(), error);
            } else {
                try {
                    StackTraceElement el = Thread.currentThread().getStackTrace()[2];
                    sendError(el.getClassName(), "Sorry, but this class is not meant to be called upon by user classes.");
                } catch(Exception ex2) {
                    sendError("[CLASSNAME IRRETRIEVABLE]", "Sorry, but this class is not meant to be called upon by user classes.");
                }
            }
        } catch(Exception ex) {
            try {
                StackTraceElement e = Thread.currentThread().getStackTrace()[2];
                sendError(e.getClassName(), "Sorry, but there was an error handling that error!");
            } catch(Exception ex2) {
                sendError("[CLASSNAME IRRETRIEVABLE]", "Sorry, but there was an error handling that error!");
            }
        }
    }

}
