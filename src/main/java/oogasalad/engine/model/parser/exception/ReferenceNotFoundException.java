package oogasalad.engine.model.parser.exception;

/**
 * This exception is thrown when a requested reference is not found while parsing.
 *
 * @author Shaan Gondalia
 */
public class ReferenceNotFoundException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Requested reference was not found";

  /**
   * Creates new ReferenceNotFoundException with the default message
   */
  public ReferenceNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new ElementNotFoundException with the provided message
   *
   * @param message the error message for this exception
   */
  public ReferenceNotFoundException(String message) {
    super(message);
  }
}
