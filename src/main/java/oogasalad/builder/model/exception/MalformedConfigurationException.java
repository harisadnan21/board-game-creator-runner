package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when a configuration file is malformed.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class MalformedConfigurationException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "MalformedConfiguration";

  /**
   * Creates new MalformedConfigurationException with the default message
   */
  public MalformedConfigurationException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
  }

  /**
   * Creates new MalformedConfigurationException with the provided message
   *
   * @param message the error message for this exception
   */
  public MalformedConfigurationException(String message) {
    super(message);
  }

  /**
   * Creates new MalformedConfigurationException with the provided message
   *
   * @param cause the cause of the exception
   */
  public MalformedConfigurationException(Throwable cause) {
    super(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY), cause);
  }
}
