package oogasalad.builder.view;

import oogasalad.builder.controller.ResourcesSingleton;

/**
 * A singleton to get localized strings from the view resources file.
 * Falls back to English when no language is set or the key being asked for can't be found.
 *
 * @author Ricky Weerts
 */
public class ViewResourcesSingleton extends ResourcesSingleton {

    private static ViewResourcesSingleton instance;

    /**
     * Creates a ViewResourcesSingleton that uses .properties files from the view folder.
     */
    protected ViewResourcesSingleton() {
        super("view.");
    }

    /**
     * Gets the instance of this singleton.
     * @return an instance of ViewResourcesSingleton
     */
    public static synchronized ViewResourcesSingleton getInstance() {
        if(instance == null) {
            instance = new ViewResourcesSingleton();
        }
        return instance;
    }
}
