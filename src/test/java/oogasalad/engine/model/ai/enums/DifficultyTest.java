package oogasalad.engine.model.ai.enums;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class DifficultyTest {

  @Test
  void values() {
    Difficulty[] difficulties = Difficulty.values();
    assertTrue(Arrays.asList(difficulties).contains(Difficulty.EASY));
    assertTrue(Arrays.asList(difficulties).contains(Difficulty.RANDOM));
  }
}