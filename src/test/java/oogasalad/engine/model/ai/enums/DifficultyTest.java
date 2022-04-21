package oogasalad.engine.model.ai.enums;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import oogasalad.engine.model.ai.TimeLimit;
import org.junit.jupiter.api.Test;

class DifficultyTest {

  @Test
  void values() {
    Difficulty[] difficulties = Difficulty.values();
    for (Difficulty difficulty : Arrays.asList(Difficulty.EASY, Difficulty.RANDOM,
        Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXPERT, Difficulty.ADAPTIVE)) {
      assertTrue(Arrays.asList(difficulties).contains(difficulty));
    }
  }

  @Test
  void depth() {
    Difficulty[] difficulties = Difficulty.values();
    for (Difficulty difficulty : Arrays.asList(Difficulty.EASY, Difficulty.RANDOM,
        Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXPERT, Difficulty.ADAPTIVE)) {
      assertInstanceOf(Integer.TYPE, difficulty.depth());
    }
  }

  @Test
  void timeLimit() {
    Difficulty[] difficulties = Difficulty.values();
    for (Difficulty difficulty : Arrays.asList(Difficulty.EASY, Difficulty.RANDOM,
        Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXPERT, Difficulty.ADAPTIVE)) {
      assertInstanceOf(TimeLimit.class, difficulty.timeLimit());
    }
  }
}
