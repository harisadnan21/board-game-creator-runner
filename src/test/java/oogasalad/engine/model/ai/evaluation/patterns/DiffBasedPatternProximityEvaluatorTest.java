package oogasalad.engine.model.ai.evaluation.patterns;

import static org.junit.jupiter.api.Assertions.*;

import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.junit.jupiter.api.Test;

class DiffBasedPatternProximityEvaluatorTest {

  static DiffBasedPatternProximityEvaluator diffBasedPatternProximityEvaluator = new DiffBasedPatternProximityEvaluator(getRandomPatterns());
  private Board randomBoard = new Board(3,3);
  private int randomPlayer = Piece.PLAYER_ONE;

  private static SortedSet<Pattern> getRandomPatterns() {
    return TreeSet.empty();
  }

  @Test
  void evaluate() {
    assertInstanceOf(Integer.class, Integer.valueOf(diffBasedPatternProximityEvaluator.evaluate(randomBoard, randomPlayer)));
  }

  @Test
  void getPositionsToCheck() {
    assertNotNull(diffBasedPatternProximityEvaluator.getPositionsToCheck());
    assertInstanceOf(PriorityBlockingQueue.class, diffBasedPatternProximityEvaluator.getPositionsToCheck());
  }
}