package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import java.util.Random;
import java.util.Set;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Board;

public class RandomSearcher implements Selects {

  private final Random random;
  private Set<AIChoice> choices;
  private AIOracle aiOracle;
  private int forPlayer;

  public RandomSearcher(AIOracle aiOracle, int forPlayer) {
    Selects.validate(aiOracle);
    this.aiOracle = aiOracle;
    this.forPlayer = forPlayer;
    this.random = new Random();
  }

  public AIChoice selectChoice(Board board, int forPlayer) {
    this.choices = this.aiOracle.getChoices(board, this.forPlayer);

    int numChoices = choices.size();
    int selection = random.nextInt(0, numChoices);
    var iterator = choices.iterator();
    AIChoice aiChoice = iterator.next();
    for(int i = 0; i < selection; i++) {
      aiChoice = iterator.next();
    }
    return aiChoice;
  }
}
