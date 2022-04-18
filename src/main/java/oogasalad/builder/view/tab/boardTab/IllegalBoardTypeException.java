package oogasalad.builder.view.tab.boardTab;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when an unknown board type is used to create a board
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class IllegalBoardTypeException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "IllegalBoardTypeException";

  /**
   * Creates new IllegalBoardTypeException for a specified board type
   *
   * @param type the board type that was used
   */
  public IllegalBoardTypeException(String type) {
    this(type, null);
  }

  /**
   * Creates new ElementCreationException for a specific board type and with a provided cause
   *
   * @param type the board type that was used
   * @param cause the throwable that caused this exception
   */
  public IllegalBoardTypeException(String type, Throwable cause) {
    super(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY, type), cause);
  }
}
