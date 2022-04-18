package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when the board is updated without being initialized.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class ElementNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "GameElementNotFound";

  /**
   * Creates new ElementNotFoundException with the default message
   */
  public ElementNotFoundException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
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
