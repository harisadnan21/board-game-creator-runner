package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when an invalid type of game element is requested.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class InvalidTypeException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "InvalidGameElementType";

  /**
   * Creates new InvalidTypeException with the default message
   */
  public InvalidTypeException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
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
