package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that tests the Translate class
 * @author haris adnan
 */
class TranslateTest {
  /**
   * test for the execute method for the Translate class
   */
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);
  @Test
  void execute() {
    int[] paramarray = new int[]{1,1, 3,3,1};
    Translate translate= new Translate(paramarray);
    Position position = new Position(2,2);
    Piece testPiece = TestBoard.getPiece(1,1);
    Board newTestBoard = translate.execute(TestBoard, position);
    Piece newTestPiece = newTestBoard.getPiece(3,3);
    assertEquals(testPiece,newTestPiece);
  }
}