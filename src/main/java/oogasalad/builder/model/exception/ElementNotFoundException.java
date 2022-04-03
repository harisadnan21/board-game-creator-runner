package oogasalad.builder.model.exception;

/**
 * This exception is thrown when the board is updated without being initialized.
 *
 * @author Shaan Gondalia
 */
public class ElementNotFoundException extends Exception {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Requested game element was not found";

  /**
   * Creates new ElementNotFoundException with the default message
   */
  public ElementNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new ElementNotFoundException with the provided message
   *
   * @param message the error message for this exception
   */
  public ElementNotFoundException(String message) {
    super(message);
  }
}
