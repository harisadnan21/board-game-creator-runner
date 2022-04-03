package oogasalad.builder.controller;

import java.util.ResourceBundle;

/**
 * A singleton to get localized strings from the default resources file.
 * Falls back to English when no language is set or the key being asked for can't be found.
 *
 * @author Ricky Weerts
 */
public class ResourcesSingleton {

    private static ResourcesSingleton instance;

    private static final String DEFAULT_LANGUAGE = "English";

    private String path;

    private final ResourceBundle fallbackResources;

    private ResourceBundle resources;

    /**
     * Creates a ResourcesSingleton that uses .properties files from the root of the resources folder.
     */
    protected ResourcesSingleton() {
        this("/");
    }

    /**
     * Creates a ResourcesSingleton that uses .properties files from a specified path relative to the resources folder.
     * Only to be used by subclasses.
     * @param path the path to get .properties files from
     */
    protected ResourcesSingleton(String path) {
        this.path = path;
        fallbackResources = ResourceBundle.getBundle(path + DEFAULT_LANGUAGE);
    }

    /**
     * Gets the instance of this singleton.
     * @return an instance of ResourcesSingleton
     */
    public static synchronized ResourcesSingleton getInstance() {
        if(instance == null) {
            instance = new ResourcesSingleton();
        }
        return instance;
    }

    /**
     * Sets the language to be used for finding strings.
     * This controls which .properties file will be read from.
     * @param lang the language to use
     */
    public void setLanguage(String lang) {
        resources = ResourceBundle.getBundle(path + lang + ".properties");
    }

    /**
     * Gets a string in the proper language and formats it with the passed arguments.
     * Defaults to English if no language has been set or the key can't be found in the set language's .properties file.
     * @param key the key of the string to get
     * @param formatting the arguments to format the resulting string with
     * @return the localized string formatted using the specified arguments
     */
    public String getString(String key, Object...formatting) {
        if(resources == null || !resources.containsKey(key)) {
            return fallbackResources.getString(key).formatted(formatting);
        }
        return resources.getString(key).formatted(formatting);
    }



}
