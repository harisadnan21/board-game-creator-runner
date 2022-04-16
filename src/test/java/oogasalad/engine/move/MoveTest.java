package oogasalad.engine.move;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.actions.Remove;
import oogasalad.engine.model.actions.Translate;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.conditions.piece_conditions.IsOccupied;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.move.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {

  Board myBoard;
  Move myMove;

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
    PieceCondition[] conditions = new PieceCondition[2];
    conditions[0] = new IsOccupied(new int[]{0,0});
    conditions[1] = new IsOccupied(new int[]{1,1});

    Action[] actions = new Action[2];
    actions[0] = new Translate(new int[]{0,0,2,2});
    actions[1] = new Remove(new int[]{1,1});

    myMove = new Move("Jump", conditions, actions, 2,2);
  }

  @Test
  void basicTest() throws OutOfBoardException {
    assertFalse(myMove.isValid(myBoard, 0,0));

    myBoard = myBoard.placeNewPiece(1, 1, 0, 0);
    myBoard = myBoard.placeNewPiece(0,0,0,0);

    assertFalse(myBoard.isEmpty(0,0));
    assertTrue(myBoard.isEmpty(2,2));
    assertFalse(myBoard.isEmpty(1,1));

    assertTrue(myMove.isValid(myBoard, 0,0));

    myBoard = myMove.doMovement(myBoard, 0,0);

    assertTrue(myBoard.isEmpty(0,0));
    assertFalse(myBoard.isEmpty(2,2));
    assertTrue(myBoard.isEmpty(1,1));
    
    assertFalse(myMove.isValid(myBoard, 0,0));
  }
}
