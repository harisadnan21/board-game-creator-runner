package oogasalad.engine.model.driver;


/**
 * Exception class that handles Board History Excpetions: these happen if the user goes too far back
 * or forward in the history of Boards.
 * Git links:
 *
 * @author Haris Adnan
 */
public class BoardHistoryException extends Exception {

  /**
   * Exception for problematic input for the number of boards in the history of boards.
   *
   * @param message : message that is shown to user when this exception is thrown
   */
  public BoardHistoryException(String message) {
    super(message);

  }
}
