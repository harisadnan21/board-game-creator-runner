package oogasalad.engine.model.ai.searchTypes;

import java.util.Set;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.Seq;

public class EasySearcher extends Searcher{

  private Set<Choice> choices;


  public EasySearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }

  protected Choice nextChoiceToExplore() {
    return Seq.seq(choices).maxBy(choice -> stateEvaluator.evaluate(choice.getResultingBoard(), forPlayer)).get();
  }

  @Override
  public Choice selectChoice(Board board) {
    this.choices = this.Oracle.getChoices(board, forPlayer);
    return nextChoiceToExplore();
  }
}
