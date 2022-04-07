package oogasalad.engine.model;

public class OutOfBoardException extends IndexOutOfBoundsException {
  public OutOfBoardException(String message) {
    super(message);
  }
}
