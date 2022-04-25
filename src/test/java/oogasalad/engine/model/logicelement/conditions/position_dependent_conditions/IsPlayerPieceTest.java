package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsPlayerPiece condition
 * @author Haris Adnan
 */

class IsPlayerPieceTest {
  PositionState[][] positionStates = new PositionState[10][10];
  Board TestBoard = new Board(positionStates);
  /**
   * test for the IsTrue Function in the IsPlayerPiece Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1,1,0};
    IsPlayerPiece isPlayerPiece= new IsPlayerPiece(paramarray);
    Position position = new Position(1,1);
    boolean answer = isPlayerPiece.isTrue(TestBoard, position);
    assertFalse(answer);
  }
}