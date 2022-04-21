package oogasalad.engine.model.ai.searchTypes;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.ai.searchTypes.searchersForDifficulty.RandomSearcher;
import org.junit.jupiter.api.Test;

class RandomSearcherTest {

  @Test
  void goodErros() {
    assertThrows(IllegalStateException.class, () -> new RandomSearcher(null, null));
    assertThrows(IllegalStateException.class, () -> new RandomSearcher(null, null));
  }

  @Test
  void selectChoice() {
  }
}