package oogasalad.engine.model.board;


public record PositionState(Position position, int player, String pieceType) {

  //Just a convenience function
  public int x(){
    return position.x();
  }

  //Just a convenience function
  public int y(){
    return position.y();
  }

  @Deprecated
  public Position getKey() {
    return null;
  }

  @Deprecated
  public Piece getValue() {
    return null;
  }
}
