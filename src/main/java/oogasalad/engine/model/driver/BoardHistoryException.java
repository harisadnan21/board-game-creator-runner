package oogasalad.engine.model.driver;


/**
 * Exception class that handles Board History Excpetions: these happen if the user goes too far back
 * or forward in the history of Boards.
 * Git links: https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/76928e61bfee0217adf002f3aabb3403a5569ae6
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
