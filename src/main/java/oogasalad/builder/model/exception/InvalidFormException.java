package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when a parameter with an invalid form is requested.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class InvalidFormException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "InvalidForm";

  /**
   * Creates new InvalidFormException with the default message
   */
  public InvalidFormException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
  }

  /**
   * Creates new InvalidFormException with the provided message
   *
   * @param message the error message for this exception
   */
  public InvalidFormException(String message) {
    this(message, null);
  }

  /**
   * Creates new InvalidFormException with the default message and the provided cause
   *
   * @param cause the cause for this exception
   */
  public InvalidFormException(Throwable cause) {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY), cause);
  }

  /**
   * Creates new InvalidFormException with the provided message and cause
   *
   * @param message the error message for this exception
   * @param cause the cause for this exception
   */
  public InvalidFormException(String message, Throwable cause) {
    super(message, cause);
  }
}
