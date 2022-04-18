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
  public MissingRequiredPropertyException(String target) {
    super(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY, target));
  }
}
