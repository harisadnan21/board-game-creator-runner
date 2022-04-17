package oogasalad.engine.model.ai.searchTypes;

import java.util.Random;
import java.util.Set;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class RandomSearcher extends Searcher{
  private Set<AIChoice> AIChoices;

  public RandomSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }

  protected AIChoice nextChoiceToExplore() {
    int numChoices = AIChoices.size();
    Random random = new Random();
    int selection = random.nextInt(0, numChoices);
    var iterator = AIChoices.iterator();
    AIChoice AIChoice = iterator.next();
    for(int i = 0; i < selection; i++) {
      AIChoice = iterator.next();
    }
    return AIChoice;
  }


  @Override
  public AIChoice selectChoice(Board board) {
    this.AIChoices = this.Oracle.getChoices(board, forPlayer);
    return nextChoiceToExplore();
  }
}
