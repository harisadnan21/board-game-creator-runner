package oogasalad.engine.model.board.cells;

/**
 * Piece record
 * @author Alex Bildner
 */
public record Piece (int type, int player) implements Comparable<Piece> {

  /**
   * Player one's int
   */
  public static int PLAYER_ONE = 0;
  /**
   * Player two's int
   */
  public static int PLAYER_TWO = 1;
  /**
   * The int for no player
   */
  public static int NO_PLAYER = -1;

  /**
   * The int for blank type
   */
  public static int BLANK_TYPE = -1;

  /**
   * The Piece for Empty Pieces
   */
  public static Piece EMPTY = newEmptyPiece();

  /**
   *
   * Returns a new Piece with same data but a different type
   * @param withType the with type
   * @return the piece
   */
  public Piece withType(int withType) {
    return new Piece(withType, this.player);
  }

  /**
   *
   * Returns a new Piece with same data but a different player
   * @param withPlayer the with player
   * @return the piece
   */
  public Piece withPlayer(int withPlayer) {
    return new Piece(this.type, withPlayer);
  }

  /**
   * returns a new empty piece
   *
   * @return the piece
   */
  public static Piece newEmptyPiece() {
    return new Piece(BLANK_TYPE, NO_PLAYER);
  }

  public int compareTo(Piece piece) {
    if(this.player()!=piece.player()) return this.player() - piece.player();

    return this.type() - piece.type();
  }
}
