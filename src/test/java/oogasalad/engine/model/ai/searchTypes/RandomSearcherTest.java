package oogasalad.engine.model.ai.searchTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomSearcherTest {

  @Test
  void goodErros() {
    assertThrows(IllegalStateException.class, () -> new RandomSearcher(null, 0));
    assertThrows(IllegalStateException.class, () -> new RandomSearcher(null, 1));
  }

  @Test
  void selectChoice() {
  }
}