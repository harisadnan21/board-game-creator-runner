package oogasalad.builder.model.exception;

/**
 * This exception is thrown when a piece is placed in an occupied cell.
 *
 * @author Shaan Gondalia
 */
public class OccupiedCellException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "A piece already occupies that cell";

  /**
   * Creates new OccupiedCellException with the default message
   */
  public OccupiedCellException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new OccupiedCellException with the provided message
   *
   * @param message the error message for this exception
   */
  public OccupiedCellException(String message) {
    super(message);
  }
}
