public class Piece {

  public void move(int i, int j);

  /**
   * Changes piece type
   * ex: in Othello, piece gets switched from black to white, or pawn becomes queen in Chess
   *
   * @param i type to change piece to
   */
  public void changeType(int i);
}