package oogasalad.engine.model.ai.searchTypes;

import static oogasalad.engine.model.board.Piece.PLAYER_ONE;

import java.util.Collection;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.jooq.lambda.Seq;

public class AlphaBetaSearcher extends MinMaxSearcher implements Selects {

  public AlphaBetaSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }


  @Override
  public AIChoice selectChoice(Board board) {
    return this.getChoices(board, forPlayer).maxBy(choice -> runAlphaBeta(choice.getResultingBoard(), forPlayer, maxDepth, 0, 0)).get();
  }

  protected int runAlphaBeta(Board board, int player, int depth, int alpha, int beta) {
    //TODO: change this to be Alpha-Beta
    if(this.limitReached(board, depth)) { return this.stateEvaluator.evaluate(board, player); }

    var boards = this.getChoices(board, player).map(AIChoice::getResultingBoard);
    int nextPlayer = player==PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
    return boards.mapToInt(currBoard -> runAlphaBeta(currBoard, nextPlayer, depth-1, alpha, beta)).max().getAsInt();
  }

}
