package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Direction;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Returns true if cells has n neighbors of a given type
 * @author Jake Heller
 */
public class HasNNeighborsOfType extends Condition {

  private int row;
  private int column;
  private int n;
  private int id;
  private boolean isAbsolute;
  private boolean invert;

  /**
   *
   * @param parameters size array [row, column, n, id, isAbsolute, invert]
   */
  public HasNNeighborsOfType(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    n = getParameter(2);
    id = getParameter(3);
    isAbsolute = getParameter(4) != 0;
    invert = getParameter(5) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = position.add(referencePoint);
    }
    int neighbors = getNeighbors(board, position, id);
    boolean result = neighbors == n;
    return invertIfTrue(result, invert);
  }

  private int getNeighbors(Board board, Position position, int id) {
    int count = 0;
    for (Direction dir: Direction.values()) {
      Position neighbor = position.add(new Position(dir.deltaRow(), dir.deltaColumn()));
      if (board.isValidPosition(neighbor)) {
        if (board.getPositionStateAt(neighbor).type() == id) {
          count++;
        }
      }
    }
    return count;
  }


}
