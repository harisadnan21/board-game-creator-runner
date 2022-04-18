package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.enums.Difficulty;

public class SearcherFinder {
  public static final String SEARCHER_DIRECTORY = "oogasalad/engine/model/ai/searchTypes/searchersForDifficulty/";

  public static String getFullyQualifiedClassName(Difficulty difficulty) {
    String name = difficulty.toString().toLowerCase();
    name = name.substring(0,1) + name.substring(1);
    return SEARCHER_DIRECTORY + name + "Searcher";
  }

}
