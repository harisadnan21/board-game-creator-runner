package oogasalad.engine.model.ai.searchTypes;

import static oogasalad.engine.model.board.Piece.PLAYER_ONE;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.TimeLimit;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.jooq.lambda.Seq;

public class MinMaxSearcher implements Selects, DepthLimit  {
  private final int maxDepth;
  private final StateEvaluator stateEvaluator;
  private final AIOracle aiOracle;
  private final TimeLimit timeLimit;


  public MinMaxSearcher(Difficulty difficulty, StateEvaluator stateEvaluator, AIOracle aiOracle) {
    this.maxDepth = difficulty.depth();
    this.timeLimit = difficulty.timeLimit();
    this.stateEvaluator = stateEvaluator;
    this.aiOracle = aiOracle;
  }

  public AIChoice selectChoice(Board board, int forPlayer) {
    return getChoices(board, forPlayer).maxBy(choice -> runMinimax(choice.getResultingBoard(),
        forPlayer, maxDepth)).get();
  }

  protected final Seq<AIChoice> getChoices(Board board, int forPlayer) {
    timeLimit.start();
    return Seq.seq(aiOracle.getChoices(board, forPlayer));
  }

  protected int runMinimax(Board board, int player, int depth) {
    if(limitReached(board, depth)) { return getEvaluation(board, player); }

    var boards = getNextBoards(board, player);
    int nextPlayer = getNextPlayer(player);
    return boards.mapToInt(currBoard -> runMinimax(currBoard, nextPlayer, depth-1)).max().getAsInt();
  }

  protected Seq<Board> getNextBoards(Board board, int player) {
    return getChoices(board, player).map(AIChoice::getResultingBoard);
  }

  protected int getNextPlayer(int player) {
    return player == PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
  }

  protected int getEvaluation(Board board, int player) {
    return this.stateEvaluator.evaluate(board, player);
  }

  protected boolean limitReached(Board board, int depth) {
    return (depth == 0 || timeLimit.isTimeUp()) || aiOracle.isWinningState(board);
  }

  @Override
  public int getDepthLimit() {
    return maxDepth;
  }
}
