package oogasalad.engine.model.ai.moveSelection;

import static oogasalad.engine.model.board.Piece.*;
import static oogasalad.engine.model.board.Piece.PLAYER_ONE;
import static org.jooq.lambda.Seq.seq;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.timeLimiting.TimeLimit;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.Seq;

/**
 * @author Alex Bildner
 */
public class TreeSearcher implements Selects {
  private final int maxDepth;
  private final StateEvaluator stateEvaluator;
  private final AIOracle aiOracle;
  private final TimeLimit timeLimit;


  public TreeSearcher(Difficulty difficulty, StateEvaluator stateEvaluator, AIOracle aiOracle) {
    this.maxDepth = difficulty.depth();
    this.timeLimit = difficulty.timeLimit();
    this.stateEvaluator = stateEvaluator;
    this.aiOracle = aiOracle;
  }

  public AIChoice selectChoice(Board board, int forPlayer) {
    return getChoices(board, forPlayer).maxBy(choice -> runMinimax(choice.getResultingBoard(),
        forPlayer, getDepthLimit())).get();
  }

  protected final Seq<AIChoice> getChoices(Board board, int forPlayer) {
    timeLimit.start();
    return seq(aiOracle.getChoices(board, forPlayer));
  }

  protected int runMinimax(Board board, int player, int depth) {
    if(limitReached(board, depth)) { return getEvaluationForPlayer(board, player); }

    var boards = getNextBoards(board, player);
    int nextPlayer = getNextPlayer(player);
    return boards.mapToInt(currBoard -> runMinimax(currBoard, nextPlayer, depth-1)).max().getAsInt();
  }

  protected Seq<Board> getNextBoards(Board board, int player) {
    return getChoices(board, player).map(AIChoice::getResultingBoard);
  }

  protected int getNextPlayer(int player) {
    return player == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
  }

  protected int getEvaluationForPlayer(Board board, int player) {
    return getEvaluation(board).forPlayer(player);
  }

  protected Evaluation getEvaluation(Board board) {
    return getStateEvaluator().evaluate(board);
  }

  protected StateEvaluator getStateEvaluator() {
    return this.stateEvaluator;
  }

  protected boolean limitReached(Board board, int depth) {
    return (depth == 0 || timeLimit.isTimeUp()) || aiOracle.isWinningState(board);
  }

  public int getDepthLimit() {
    return maxDepth;
  }
}
