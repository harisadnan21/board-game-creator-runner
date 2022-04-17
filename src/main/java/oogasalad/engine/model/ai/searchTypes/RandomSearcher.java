package oogasalad.engine.model.ai.searchTypes;

import java.util.Random;
import java.util.Set;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class RandomSearcher extends Searcher{
  private Set<Choice> choices;

  public RandomSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }

  protected Choice nextChoiceToExplore() {
    int numChoices = choices.size();
    Random random = new Random();
    int selection = random.nextInt(0, numChoices);
    var iterator = choices.iterator();
    Choice choice = iterator.next();
    for(int i = 0; i < selection; i++) {
      choice = iterator.next();
    }
    return choice;
  }


  @Override
  public Choice selectChoice(Board board) {
    this.choices = this.Oracle.getChoices(board, forPlayer);
    return nextChoiceToExplore();
  }
}
