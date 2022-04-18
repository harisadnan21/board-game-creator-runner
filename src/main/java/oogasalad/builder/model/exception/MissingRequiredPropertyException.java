package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when a Game Element is created without a required property.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class MissingRequiredPropertyException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "MissingRequiredProperty";

  /**
   * Creates new MissingRequiredPropertyException with the default message
   */
  public MissingRequiredPropertyException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
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
