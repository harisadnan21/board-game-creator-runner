package oogasalad.builder.model.exception;

/**
 * This exception is thrown when the board is updated without being initialized.
 *
 * @author Shaan Gondalia
 */
public class NullBoardException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Board has not been initialized";

  /**
   * Creates new NullBoardException with the default message
   */
  public NullBoardException() {
    super(DEFAULT_MESSAGE);
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
