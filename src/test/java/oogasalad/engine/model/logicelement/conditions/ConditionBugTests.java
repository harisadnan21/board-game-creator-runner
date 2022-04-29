package oogasalad.engine.model.logicelement.conditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.conditions.position_dependent_conditions.IsInColumn;
import oogasalad.engine.model.logicelement.conditions.position_dependent_conditions.IsInRow;
import org.junit.jupiter.api.Test;

/**
 * Class for bug testing in conditions
 *
 * @author Jake Heller
 */
public class ConditionBugTests {
  Board board = new Board(5,5);

  /**
   * Bug fix test for IsInRow
   *
   * Before bug fix, second assertion fails given same input
   */
  @Test
  void isInRowTwice() {
    int[] paramarray = new int[]{0, 1, 0};
    Condition isInRow = new IsInRow(paramarray);
    Position position = new Position(1,1);
    boolean answer = isInRow.isTrue(board, position);
    assertTrue(answer);
    assertTrue(isInRow.isTrue(board, position));
  }

  /**
   * Bug fix test for IsInColumn
   *
   * Before bug fix, second assertion fails given same input
   */
  @Test
  void isInColumnTwice() {
    int[] paramarray = new int[]{0, 1, 0};
    Condition isInRow = new IsInColumn(paramarray);
    Position position = new Position(1,1);
    boolean answer = isInRow.isTrue(board, position);
    assertTrue(answer);
    assertTrue(isInRow.isTrue(board, position));
  }
}
