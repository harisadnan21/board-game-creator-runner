package oogasalad.engine.model.board.cells;

public record Piece (int type, int player) implements Comparable<Piece> {

  public static int PLAYER_ONE = 0;
  public static int PLAYER_TWO = 1;
  public static int NO_PLAYER = -1;

  public static int BLANK_TYPE = -1;

  public static Piece EMPTY = newEmptyPiece();

  public Piece withType(int withType) {
    return new Piece(withType, this.player);
  }

  public Piece withPlayer(int withPlayer) {
    return new Piece(this.type, withPlayer);
  }

  public static Piece newEmptyPiece() {
    return new Piece(BLANK_TYPE, NO_PLAYER);
  }

  public int compareTo(Piece piece) {
    if(this.player()!=piece.player()) return this.player() - piece.player();

    return this.type() - piece.type();
  }
}