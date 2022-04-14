package oogasalad.engine.model.conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.misc.OutOfBoardException;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IsEmptyTest {
  Board myBoard;
  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }

  @Test
  void basicTest() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1, 1, 0, 0);
    int[] params = new int[]{0, 0};
    PieceCondition condition = new IsEmpty(params);

    assertFalse(condition.isTrue(myBoard, 1, 1));
    assertTrue(condition.isTrue(myBoard, 0, 1));
  }

  @Test
  void copyTest() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1, 1, 0, 0);

    Board copyBoard = myBoard.copy();

    int[] params = new int[]{0, 0};
    PieceCondition condition = new IsEmpty(params);

    assertFalse(condition.isTrue(copyBoard, 1, 1));
    assertTrue(condition.isTrue(copyBoard, 0, 1));
  }
}
