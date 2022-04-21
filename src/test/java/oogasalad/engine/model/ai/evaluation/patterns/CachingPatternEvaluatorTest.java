package oogasalad.engine.model.ai.evaluation.patterns;

import static org.junit.jupiter.api.Assertions.*;

import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.junit.jupiter.api.Test;

class CachingPatternEvaluatorTest {

  static CachingPatternEvaluator cachingPatternEvaluator = new CachingPatternEvaluator();
  private Board randomBoard = new Board(3,3);
  private int randomPlayer = Piece.PLAYER_ONE;

  private static SortedSet<Pattern> getRandomPatterns() {
    return TreeSet.empty();
  }

  @Test
  void evaluate() {
    assertInstanceOf(Evaluation.class, cachingPatternEvaluator.evaluate(randomBoard));
  }

  @Test
  void getPositionsToCheck() {
    assertNotNull(cachingPatternEvaluator.getPositionsToCheck());
    assertInstanceOf(PriorityBlockingQueue.class, cachingPatternEvaluator.getPositionsToCheck());
  }
}