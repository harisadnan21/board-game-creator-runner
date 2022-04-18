package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class AlphaBetaSearcher extends MinMaxSearcher implements Selects {

  public AlphaBetaSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }


  @Override
  public AIChoice selectChoice(Board board, int forPlayer) {
    return this.getChoices(board, forPlayer).maxBy(choice -> runAlphaBeta(choice.getResultingBoard(),
        forPlayer, this.getDepthLimit(), 0, 0)).get();
  }

  protected int runAlphaBeta(Board board, int player, int depth, int alpha, int beta) {
    if(this.limitReached(board, depth)) { return this.getEvaluation(board, player); }

    var boards = this.getNextBoards(board, player);
    int nextPlayer = this.getNextPlayer(player);
    return boards.mapToInt(currBoard -> runAlphaBeta(currBoard, nextPlayer, depth-1, alpha, beta)).max().getAsInt();
  }

  @Override
  protected boolean limitReached(Board board, int depth) {
    return super.limitReached(board, depth) || false; // TODO: replace false with proper alpha-beta end condition
  }

}
