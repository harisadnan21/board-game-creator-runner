package oogasalad.builder.controller;

/**
 * A singleton to get localized strings from the exception resources file.
 * Falls back to English when no language is set or the key being asked for can't be found.
 *
 * @author Ricky Weerts
 */
public class ExceptionResourcesSingleton extends ResourcesSingleton {

    private static ExceptionResourcesSingleton instance;

    /**
     * Creates an ExceptionResourcesSingleton that uses .properties files from the exceptions folder.
     */
    protected ExceptionResourcesSingleton() {
        super("/exceptions/");
    }

    /**
     * Gets the instance of this singleton.
     * @return an instance of ExceptionResourcesSingleton
     */
    public static synchronized ExceptionResourcesSingleton getInstance() {
        if(instance == null) {
            instance = new ExceptionResourcesSingleton();
        }
        return instance;
    }
}
