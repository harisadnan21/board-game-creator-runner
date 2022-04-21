package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.random.RandomRange;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CenterAroundTest {

  @Test
  void noErrors() {
    Assertions.assertDoesNotThrow(() -> new CenterAround(null, true, false, 1));
  }

  @Test
  void regularizeEvaluationScore() {
  }

  @Test
  void testRegularizeEvaluationScore() {
  }

  @Test
  void evaluate() {
    CenterAround centerAround = new CenterAround(new RandomRange(1,1), true, true, 1);
    Assertions.assertEquals(centerAround.evaluate(new Board(1,1), Piece.PLAYER_ONE), 0);
    Assertions.assertEquals(centerAround.evaluate(new Board(5,5), Piece.PLAYER_TWO), 0);


    CenterAround centerAround1 = new CenterAround(new RandomRange(5,5), true, true, 1);
    Assertions.assertEquals(-4, centerAround1.evaluate(new Board(1,1), Piece.PLAYER_ONE));
    Assertions.assertEquals(-4, centerAround1.evaluate(new Board(5,5), Piece.PLAYER_TWO));
  }

}