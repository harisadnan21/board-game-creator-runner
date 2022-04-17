package oogasalad.engine.model.AI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTypeTest {

  @Test
  void values() {
    oogasalad.engine.model.AI.SearchType[] searchTypes = oogasalad.engine.model.AI.SearchType.values();
    for(oogasalad.engine.model.AI.SearchType searchType: searchTypes) {
      Assertions.assertNotNull(searchType);
    }
  }


  @Test
  void valueOf() {
    // MINIMAX, ALPHABETA, BFS, DFS, RANDOM, SSSSTAR
  }
}