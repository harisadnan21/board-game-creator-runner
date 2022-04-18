package oogasalad.engine.model.ai.searchTypes;

import static oogasalad.engine.model.board.Piece.PLAYER_ONE;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.jooq.lambda.Seq;

public class MinMaxSearcher implements Selects, DepthLimit  {
  private final int maxDepth;
  protected final int forPlayer;
  private final StateEvaluator stateEvaluator;
  private final AIOracle AIOracle;


  public MinMaxSearcher(int maxDepth, int forPlayer, StateEvaluator stateEvaluator, AIOracle AIOracle) {
    this.maxDepth = maxDepth;
    this.forPlayer = forPlayer;
    this.stateEvaluator = stateEvaluator;
    this.AIOracle = AIOracle;
  }

  public MinMaxSearcher(int forPlayer, StateEvaluator stateEvaluator, AIOracle AIOracle) {
    this.maxDepth = 5;
    this.forPlayer = forPlayer;
    this.stateEvaluator = stateEvaluator;
    this.AIOracle = AIOracle;
  }

  public AIChoice selectChoice(Board board) {
    return getChoices(board, forPlayer).maxBy(choice -> runMinimax(choice.getResultingBoard(), forPlayer, maxDepth)).get();
  }

  protected final Seq<AIChoice> getChoices(Board board, int forPlayer) {
    return Seq.seq(this.AIOracle.getChoices(board, forPlayer));
  }

  protected int runMinimax(Board board, int player, int depth) {
    if(limitReached(board, depth)) {
      return getEvaluation(board, player);
    }
    var boards = getChoices(board, player).stream().map(AIChoice::getResultingBoard);
    int nextPlayer = player==PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
    return boards.mapToInt(currBoard -> runMinimax(currBoard, nextPlayer, depth-1)).max().getAsInt();
  }

  protected int getEvaluation(Board board, int player) {
    return this.stateEvaluator.evaluate(board, player);
  }

  protected boolean limitReached(Board board, int depth) {
    return depth == 0 || AIOracle.isWinningState(board);
  }

  @Override
  public int getDepthLimit() {
    return maxDepth;
  }
}
