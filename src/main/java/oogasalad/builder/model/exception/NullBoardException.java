package oogasalad.builder.model.exception;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when the board is updated without being initialized.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class NullBoardException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "NullBoard";

  /**
   * Creates new NullBoardException with the default message
   */
  public NullBoardException() {
    this(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY));
  }

  /**
   * Creates new NullBoardException with the provided message
   *
   * @param message the error message for this exception
   */
  public NullBoardException(String message) {
    super(message);
  }
}
