package oogasalad.builder.model.exception;

/**
 * This exception is thrown when an invalid type of game element is requested.
 *
 * @author Shaan Gondalia
 */
public class InvalidTypeException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Invalid GameElement type requested";

  /**
   * Creates new InvalidTypeException with the default message
   */
  public InvalidTypeException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new InvalidTypeException with the provided message
   *
   * @param message the error message for this exception
   */
  public InvalidTypeException(String message) {
    super(message);
  }
}
