package oogasalad.engine.model.board;

public class OutOfBoardException extends IndexOutOfBoundsException {
  public OutOfBoardException(String message) {
    super(message);
  }
}
