package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.enums.Difficulty;

public class SearcherFinder {
  public static final String SEARCHER_DIRECTORY = "oogasalad/engine/model/ai/searchTypes/searchersForDifficulty/";

  public static String getFullyQualifiedClassName(Difficulty difficulty) {
    String name = toTitleCase(difficulty.toString());
    return SEARCHER_DIRECTORY + name + "Searcher";
  }

  public static String toTitleCase(String word) {
    word = word.toLowerCase();
    return word.charAt(0) + word.substring(1);
  }

}
