package oogasalad.engine.model.board.cells;


/**
 * Position State Record
 * @author Alex Bildner
 */
public record PositionState(Position position, Piece piece) implements Comparable<PositionState> {

  /**
   * With position state.
   *
   * @param newPiece the new piece
   * @return the position state
   */
  public PositionState with(Piece newPiece) {
    return new PositionState(this.position, newPiece);
  }

  /**
   * With position state.
   *
   * @param position the position
   * @return the position state
   */
  public PositionState with(Position position) {
    return new PositionState(position, this.piece);
  }

  /**
   * With empty position state.
   *
   * @return the position state
   */
  public PositionState withEmpty() {
    return new PositionState(position, Piece.EMPTY);
  }

  /**
   *
   * @return the int
   */
//Just a convenience function
  public int i(){
    return position.row();
  }

  /**
   *
   * @return the int
   */
//Just a convenience function
  public int j(){
    return position.column();
  }


  /**
   *
   * @return the int
   */
//Just a convenience function
  public int type() {
    return piece.type();
  }

  /**
   *
   * @return the int
   */
//Just a convenience function
  public int player() {
    return piece.player();
  }

  /**
   *
   * @return the boolean
   */
  public boolean isPresent() {
    return !isEmpty();
  }

  /**
   *
   * @return the boolean
   */
  public boolean isEmpty() {
    return piece.equals(Piece.EMPTY);
  }


  /**
   *
   * @param positionState the position state
   * @return the int
   */
  @Override
  public int compareTo(PositionState positionState) {
    int positionDifference = this.position().compareTo(positionState.position());
    if (positionDifference == 0)
      return positionDifference;

    return this.piece().compareTo(positionState.piece());
  }

}
