package oogasalad.engine.model.ai.evaluation.random;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

class RandomRangeTest {

  @Test
  void evaluate() {
    RandomRange randomRange = new RandomRange(0, 100);
    for (int i = 0; i < 10; i++) {
      for (int player : Arrays.array(Piece.PLAYER_ONE, Piece.PLAYER_TWO)) {
        Board board = new Board(1,i);
        Evaluation ret = randomRange.evaluate(board);
        assertTrue(ret.forPlayer(player) >= 0);
        assertTrue(ret.forPlayer(player) <= 100);
      }
    }
  }
}