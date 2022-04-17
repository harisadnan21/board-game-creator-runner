package oogasalad.engine.model.AI;

import oogasalad.engine.model.ai.searchTypes.SearchType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTypeTest {

  @Test
  void values() {
    SearchType[] searchTypes = SearchType.values();
    for(SearchType searchType: searchTypes) {
      Assertions.assertNotNull(searchType);
    }
  }


  @Test
  void valueOf() {
    // MINIMAX, ALPHABETA, BFS, DFS, RANDOM, SSSSTAR
  }
}