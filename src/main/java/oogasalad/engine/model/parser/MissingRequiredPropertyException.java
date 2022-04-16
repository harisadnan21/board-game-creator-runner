package oogasalad.engine.model.parser;

/**
 * This exception is thrown when the parser does not find a required property
 *
 * @author Shaan Gondalia
 */
public class MissingRequiredPropertyException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Missing Required Property";

  /**
   * Creates new MissingRequiredPropertyException with the default message
   */
  public MissingRequiredPropertyException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new MissingRequiredPropertyException with the provided message
   *
   * @param message the error message for this exception
   */
  public MissingRequiredPropertyException(String message) {
    super(message);
  }
}
