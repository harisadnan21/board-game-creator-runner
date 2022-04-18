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
    Collection<AIChoice> AIChoices = this.AIOracle.getChoices(board, forPlayer);
    return Seq.seq(AIChoices).maxBy(choice -> runAlphaBeta(choice.getResultingBoard(), forPlayer, maxDepth, 0, 0)).get();
  }

  protected int runAlphaBeta(Board board, int player, int depth, int alpha, int beta) {
    //TODO: change this to be Alpha-Beta
    if(depth==0 || AIOracle.isWinningState(board)) {
      return this.stateEvaluator.evaluate(board, player);
    }
    Stream<Board> boards = this.AIOracle.getChoices(board, player).stream().map(AIChoice::getResultingBoard);
    int nextPlayer = player==PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
    return Seq.seq(boards).mapToInt(currBoard -> runAlphaBeta(currBoard, nextPlayer, depth-1, alpha, beta)).max().getAsInt();
  }

}
