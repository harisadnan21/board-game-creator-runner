package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the Place class
 * @author haris adnan
 */
class PlaceTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);


  /**
   * test for the execute method for the Place class
   */
  @Test
  void execute() {
    int[] paramarray = new int[]{1,1,1,1,1};
    Place PlaceAction= new Place(paramarray);
    Position position = new Position(2,2);
    Board newTestBoard = PlaceAction.execute(TestBoard, position);
    assertEquals(newTestBoard.getPiece(2,2).type(), -1);
    assertEquals(newTestBoard.getPiece(2,2).player(), -1);
  }
}