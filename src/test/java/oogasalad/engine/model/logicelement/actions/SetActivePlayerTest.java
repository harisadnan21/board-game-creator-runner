package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that tests the SetActivePlayer class
 * @author haris adnan
 */
class SetActivePlayerTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);
  /**
   * test for the execute method for the SetActivePlayer class
   */
  @Test
  void execute() {
    int[] paramarray = new int[]{1};
    SetActivePlayer setActivePlayer= new SetActivePlayer(paramarray);
    Position position = new Position(2,2);
    Board newTestBoard = setActivePlayer.execute(TestBoard, position);
    assertEquals(newTestBoard.getPlayer(), 1);
  }
}