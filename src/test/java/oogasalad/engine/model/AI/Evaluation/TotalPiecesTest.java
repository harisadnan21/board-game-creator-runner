package oogasalad.engine.model.AI.Evaluation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import oogasalad.engine.model.AI.InvalidBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;

import static org.jooq.lambda.Seq.*;

import org.junit.jupiter.api.Test;


class TotalPiecesTest {
  private static StateEvaluator evaluator = new TotalPieces();

  @Test
  void emptyBoardEvaluation() {
    Board[] boards = {new Board(1, 1),
                      new Board(3,9),
                      new Board(5,5)};
    int[] evaluations = Arrays.stream(boards).mapToInt(board -> evaluator.Evaluate(board, Piece.PLAYER_ONE)).toArray();
    range(0, 3).forEach(index -> assertTrue(evaluations[index] ==  0));
    int[] evaluations2 = Arrays.stream(boards).mapToInt(board -> evaluator.Evaluate(board, Piece.PLAYER_TWO)).toArray();
    range(0, 3).forEach(index -> assertTrue(evaluations2[index] ==  0));
  }

  @Test
  void fullOneSidedBoardEvaluation() {
    Board[] boards1 = {new Board(1, 1), new Board(3,9), new Board(5,5)};
    Board[] boards2 = {new Board(1, 1), new Board(3,9), new Board(5,5)};
    for(int index = 0; index < boards1.length; index++) {
      Board board1 = boards1[index];
      Board board2 = boards2[index];
      for(int row = 0; row < board1.getHeight(); row++) {
        for(int col = 0; col < board1.getWidth(); col++) {
          board1 = board1.placePiece(new PositionState(new Position(row, col), new Piece(1, Piece.PLAYER_ONE)));
          board2 = board2.placePiece(new PositionState(new Position(row, col), new Piece(1, Piece.PLAYER_TWO)));
        }
      }
    }

    for(int index = 0; index < boards1.length; index++) {
      Board board1 = boards1[index];
      Board board2 = boards2[index];
      int numPositions = board1.getWidth() * board2.getHeight();

      assertEquals(numPositions, evaluator.Evaluate(board1, Piece.PLAYER_ONE));
      assertEquals(-1 * numPositions, evaluator.Evaluate(board1, Piece.PLAYER_TWO));

      assertEquals(-1 * numPositions, evaluator.Evaluate(board2, Piece.PLAYER_ONE));
      assertEquals(numPositions, evaluator.Evaluate(board2, Piece.PLAYER_TWO));

    }

  }

  @Test
  void testSplitBoard() {
      Board board = new Board(4,4);

      for(int row = 0; row < 2; row++) {
        for(int col = 0; col < 2; col++) {
          board = board.placePiece(new PositionState(new Position(row, col), new Piece(1, Piece.PLAYER_ONE)));
        }
      }

      for(int row = 2; row < 4; row++) {
        for(int col = 2; col < 4; col++) {
          board = board.placePiece(new PositionState(new Position(row, col), new Piece(1, Piece.PLAYER_TWO)));
        }
      }

    assertEquals(evaluator.Evaluate(board, Piece.PLAYER_ONE), evaluator.Evaluate(board, Piece.PLAYER_TWO));
    assertEquals(evaluator.Evaluate(board, Piece.PLAYER_ONE), 0); 
    assertEquals(evaluator.Evaluate(board, Piece.PLAYER_TWO), 0); 
  }

  @Test
  void badInput() {
    assertThrows(InvalidBoardException.class, () -> evaluator.Evaluate(null, Piece.PLAYER_ONE));
    assertThrows(InvalidBoardException.class, () -> evaluator.Evaluate(null, Piece.PLAYER_TWO));
  }


}
