package oogasalad.engine.model.ai.evaluation;

import static org.jooq.lambda.Seq.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import oogasalad.engine.model.ai.evaluation.totals.TotalPieces;
import oogasalad.engine.model.ai.exceptions.InvalidBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.junit.jupiter.api.Test;


class TotalPiecesTest {
  private static final StateEvaluator evaluator = new TotalPieces();

  @Test
  void emptyBoardEvaluation() {
    Board[] boards = {new Board(1, 1),
                      new Board(3,9),
                      new Board(5,5)};
    int[] evaluations = Arrays.stream(boards).mapToInt(board -> evaluator.evaluate(board).forPlayer(Piece.PLAYER_ONE)).toArray();
    range(0, 3).forEach(index -> assertTrue(evaluations[index] ==  0));
    int[] evaluations2 = Arrays.stream(boards).mapToInt(board -> evaluator.evaluate(board).forPlayer(Piece.PLAYER_TWO)).toArray();
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
      boards1[index] = board1;
      boards2[index] = board2;
    }

    for(int index = 0; index < boards1.length; index++) {
      Board board1 = boards1[index];
      Board board2 = boards2[index];
      int numPositions = board1.getWidth() * board1.getHeight();

      assertEquals(numPositions, evaluator.evaluate(board1).playerOneEvaluation());
      assertEquals(-1 * numPositions, evaluator.evaluate(board1).playerTwoEvaluation());

      assertEquals(-1 * numPositions, evaluator.evaluate(board2).playerOneEvaluation());
      assertEquals(numPositions, evaluator.evaluate(board2).playerTwoEvaluation());

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

    assertEquals(evaluator.evaluate(board), evaluator.evaluate(board));
    assertEquals(evaluator.evaluate(board), new Evaluation(0,0));
    assertEquals(evaluator.evaluate(board), new Evaluation(0,0));
  }

  @Test
  void badInput() {
    assertThrows(InvalidBoardException.class, () -> evaluator.evaluate(null));
    assertThrows(InvalidBoardException.class, () -> evaluator.evaluate(null));
  }


}
