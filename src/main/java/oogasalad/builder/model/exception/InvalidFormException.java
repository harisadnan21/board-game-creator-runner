package oogasalad.builder.model.exception;

/**
 * This exception is thrown when a parameter with an invalid form is requested.
 *
 * @author Shaan Gondalia
 */
public class InvalidFormException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Invalid Parameter Form";

  /**
   * Creates new InvalidFormException with the default message
   */
  public InvalidFormException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new InvalidFormException with the provided message
   *
   * @param message the error message for this exception
   */
  public InvalidFormException(String message) {
    super(message);
  }
}
