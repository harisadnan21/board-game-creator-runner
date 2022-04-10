package oogasalad.engine.model.board;

public record PositionState (Position position, Piece piece) {
  public PositionState(int i, int j, int type, int player) {
    this(new Position(i,j), new Piece(type, player));
  }

  public boolean isEmpty() {
    return piece().equals(Piece.EMPTY);
  }
}
