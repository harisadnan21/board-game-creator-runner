package oogasalad.engine.model.ai.evaluation.patterns;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;

class PatternEvaluatorTest {


  static Position position1 = new Position(0, 0);
  static Piece piece1 = new Piece(1, Piece.PLAYER_ONE);

  static Position position2 = position1.withNewRow(1);
  static Piece piece2 = piece1.withType(2);

  static Position position3 = position1.withNewRow(2);
  static Piece piece3 = piece1.withType(3);

  private static PatternEvaluator patternEvaluator = new PatternEvaluator(getTestPatterns());
  private static Board testBoard = new Board(3,3);

  private static Collection<Pattern> getTestPatterns() {

    return List.of(
        new Pattern(
            List.of(
                new PositionState(
                    position1,
                    piece1
                ),
                new PositionState(
                    position2,
                    piece2
                ),
                new PositionState(
                    position3,
                    piece3
                )
            )
        ),
        new Pattern(
            List.of(
                new PositionState(
                    position1,
                    piece1
                ),
                new PositionState(
                    position3,
                    piece3
                )
            )
        ),
        new Pattern(
            List.of(
                new PositionState(
                    position3,
                    new Piece(1, Piece.PLAYER_TWO)
                )
            )
        )
    );
  }

  @Test
  void evaluate() {
    Evaluation evaluation = patternEvaluator.evaluate(testBoard);

    assertInstanceOf(Evaluation.class, evaluation);
    assertNotNull(evaluation);

    assertNotEquals(evaluation.playerOneEvaluation(), evaluation.playerTwoEvaluation());

//    assertTrue(evaluation.playerOneEvaluation() < evaluation.playerTwoEvaluation());

    assertEquals(-2, evaluation.playerOneEvaluation());
    assertEquals(2, evaluation.playerTwoEvaluation());
  }

  @Test
  void getPositionsToCheck() {
    assertNotNull(patternEvaluator.getPositionsToCheck(testBoard));
  }

  @Test
  void throwIfInvalid() {
    assertDoesNotThrow(() -> new PatternEvaluator(getTestPatterns()));

    assertThrows(IllegalArgumentException.class, () -> new PatternEvaluator(null));

    assertThrows(IllegalArgumentException.class, () -> new PatternEvaluator(new LinkedList<>()));
    assertThrows(IllegalArgumentException.class, () -> new PatternEvaluator(new ArrayList<>()));
    assertThrows(IllegalArgumentException.class, () -> new PatternEvaluator(new HashSet<>()));
  }

  @Test
  void updateScoresForPosition() {
  }

  @Test
  void testGetPositionsToCheck() {
    assertNotNull(patternEvaluator.getPositionsToCheck(testBoard.placePiece(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE)))));


    PatternEvaluator patternEvaluator3 = new PatternEvaluator(getTestPatterns());
    patternEvaluator3.evaluate(testBoard);

    var positionsToCheck = patternEvaluator3.getPositionsToCheck(testBoard.placePiece(
        new PositionState(new Position(0, 0), new Piece(1, Piece.PLAYER_ONE)))).toList();
    assertEquals(1, positionsToCheck.size());
    assertEquals(new Position(0,0), positionsToCheck.get(0));
  }

  @Test
  void patternsAt() {
    assertNotNull(patternEvaluator.patternsAt(position1));
    assertEquals(2, patternEvaluator.patternsAt(position1).size());
  }
}