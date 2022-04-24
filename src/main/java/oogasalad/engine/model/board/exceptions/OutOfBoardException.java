package oogasalad.engine.model.board.exceptions;

/**
 * Exception when an attempt is made to go out of the Board
 * @author Alex Bildner
 */
public class OutOfBoardException extends IndexOutOfBoundsException {
  //TODO: make sure this message is informative
  public OutOfBoardException(String message) {
    super(message);
  }
}
