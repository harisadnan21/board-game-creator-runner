package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Board;

public class RandomSearcher implements Selects {

  private final Random random;
  private AIOracle aiOracle;

  public RandomSearcher(AIOracle aiOracle) {
    this.aiOracle = aiOracle;
    this.random = new Random();
  }

  public AIChoice selectChoice(Board board, int forPlayer) {
    var choices = List.copyOf(aiOracle.getChoices(board, forPlayer));
    int selection = random.nextInt(0, choices.size());
    return choices.get(selection);
  }
}
