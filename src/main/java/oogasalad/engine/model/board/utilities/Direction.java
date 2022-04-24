package oogasalad.engine.model.board.utilities;

/**
 * Enum that defines different directions in the board.
 * @author Alex Bildner
 */
public enum Direction {
  NORTH(-1, 0),
  SOUTH(1, 0),
  EAST(0, 1),
  WEST(0, -1),
  NORTHEAST(-1, 1),
  NORTHWEST(-1, -1),
  SOUTHEAST(1, 1),
  SOUTHWEST(1, -1);

  private final int deltaI;
  private final int deltaJ;

  Direction(int deltaI, int deltaJ) {
    this.deltaI = deltaI;
    this.deltaJ = deltaJ;
  }

  public int deltaI() {
    return deltaI;
  }

  public int deltaJ() {
    return deltaJ;
  }
}
