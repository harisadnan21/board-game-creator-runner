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

public class MinMaxSearcher implements Selects {
  protected final int maxDepth;
  protected final int forPlayer;
  protected final StateEvaluator stateEvaluator;
  protected final AIOracle AIOracle;


  public MinMaxSearcher(int maxDepth, int forPlayer, StateEvaluator stateEvaluator, AIOracle AIOracle) {
    this.maxDepth = maxDepth;
    this.forPlayer = forPlayer;
    this.stateEvaluator = stateEvaluator;
    this.AIOracle = AIOracle;

  }


  public AIChoice selectChoice(Board board) {
    Collection<AIChoice> choices = this.AIOracle.getChoices(board, forPlayer);
    return Seq.seq(choices).maxBy(choice -> runMinimax(choice.getResultingBoard(), forPlayer, maxDepth)).get();
  }

  protected int runMinimax(Board board, int player, int depth) {
    if(depth==0 || AIOracle.isWinningState(board)) {
      return this.stateEvaluator.evaluate(board, player);
    }
    Stream<Board> boards = this.AIOracle.getChoices(board, player).stream().map(AIChoice::getResultingBoard);
    int nextPlayer = player==PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
    return Seq.seq(boards).mapToInt(currBoard -> runMinimax(currBoard, nextPlayer, depth-1)).max().getAsInt();
  }
}
