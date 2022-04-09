package oogasalad.engine.model.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {
  Board myBoard;
  Action myMove;
  @BeforeEach
  void setup() {
    myBoard = new ArrayBoard(3, 3);
    myBoard.placeNewPiece(1,1,0,0);
    myMove = new Move(new int[]{1,1,2,2});
  }

  @Test
  void basicTest() throws OutOfBoardException {
    assertTrue(!myBoard.isEmpty(1,1));
    assertTrue(myBoard.isEmpty(2,2));
    myMove.execute(myBoard, 0,0);

    assertTrue(myBoard.isEmpty(1,1));
    assertTrue(!myBoard.isEmpty(2,2));
  }
}
