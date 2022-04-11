package oogasalad.engine.model.board;

public record PositionState (Position position, Piece piece) {
  public PositionState(int i, int j, int type, int player) {
    this(new Position(i,j), new Piece(type, player));
  }

  public boolean isEmpty() {
    return piece().equals(Piece.EMPTY);
  }

  public boolean isPresent() {
    return !isEmpty();
  }

  //Just a convenience function
  public int i(){
    return position.i();
  }

  //Just a convenience function
  public int j(){
    return position.j();
  }


  //Just a convenience function
  public int type() {
    return piece.type();
  }

  //Just a convenience function
  public int player() {
    return piece.player();
  }

}
