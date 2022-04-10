package oogasalad.engine.model.board;

/**
 * Record that defines a Piece
 */
public record Piece (int type, int player) {
  public static int PLAYER_ONE = 0;
  public static int PLAYER_TWO = 1;

  public static int NO_PLAYER = -1;

  public static int BLANK_TYPE = -1;

  public static Piece EMPTY = new Piece(BLANK_TYPE, NO_PLAYER);

  public static Piece newEmptyPiece() {
    return new Piece(BLANK_TYPE, NO_PLAYER);
  }

  public boolean isEmpty() {
    return this.equals(EMPTY);
  }

  public boolean equals(Object o) {
    if (o.getClass() != Piece.class) {
      return false;
    }
    else {
      Piece piece = (Piece) o;
      if (piece.player() == player() && piece.type() == type()) {
        return true;
      }
      else {
        return false;
      }
    }
  }
}
