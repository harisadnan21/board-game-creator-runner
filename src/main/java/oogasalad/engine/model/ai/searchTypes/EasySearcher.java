package oogasalad.engine.model.ai.searchTypes;

import java.util.Set;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.Seq;

@Deprecated
public class EasySearcher extends Searcher implements Selects {

  private Set<AIChoice> AIChoices;


  public EasySearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }

  protected AIChoice nextChoiceToExplore() {
    return Seq.seq(AIChoices).maxBy(choice -> stateEvaluator.evaluate(choice.getResultingBoard(), forPlayer)).get();
  }

  @Override
  public AIChoice selectChoice(Board board) {
    this.AIChoices = this.Oracle.getChoices(board, forPlayer);
    return nextChoiceToExplore();
  }
}
