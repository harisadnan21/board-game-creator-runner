package oogasalad.engine.model.board;


public record PositionState(Position position, int player, String pieceType) {

  @Deprecated
  public Position getKey() {
    return null;
  }

  @Deprecated
  public Piece getValue() {
    return null;
  }
}
