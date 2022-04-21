package oogasalad.engine.model.board;



public record PositionState(Position position, Piece piece) implements Comparable<PositionState> {

  public PositionState with(Piece newPiece) {
    return new PositionState(this.position, newPiece);
  }

  public PositionState with(Position position) {
    return new PositionState(position, this.piece);
  }

  public PositionState withEmpty() {
    return new PositionState(position, Piece.EMPTY);
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

  public boolean isPresent() {
    return !isEmpty();
  }

  public boolean isEmpty() {
    return piece.equals(Piece.EMPTY);
  }

  @Override
  public int compareTo(PositionState positionState) {
    return this.position().compareTo(positionState.position());
  }
}
