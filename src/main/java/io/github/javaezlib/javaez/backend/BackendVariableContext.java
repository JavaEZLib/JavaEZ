package io.github.javaezlib.javaez.backend;

import java.util.HashMap;

/**
 * The context in which variables made using the Core extension are stored.
 * @author Red050911
 * @since 1.0
 */
public class BackendVariableContext {

    /**
     * The global Variable Context Storage for all core variables
     * @since 1.0
     * @deprecated Not meant for use by the user.
     */
    @Deprecated
    private static final HashMap<String, String> VARIABLES = new HashMap<>();

    /**
     * Adds or modifies a variable
     * @param name The name of the variable to modify
     * @param value The value to put in it
     * @since 1.0
     * @deprecated Not meant for use by the user.
     */
    @Deprecated
    public static void addOrModifyVariable(String name, String value) {
        VARIABLES.put(name, value);
    }

    /**
     * Gets a variable by its name
     * @param name The name of the variable to get
     * @return The value of the variable, blank if none.
     * @since 1.0
     * @deprecated Not meant for use by the user.
     */
    @Deprecated
    public static String getVariable(String name) {
        return VARIABLES.get(name) != null ? VARIABLES.get(name) : "";
    }

    /**
     * Removes a variable by its name
     * @param name The name of the variable to remove
     * @since 1.0
     * @deprecated Not meant for use by the user.
     */
    @Deprecated
    public static void removeVariable(String name) {
        VARIABLES.remove(name);
    }

}
