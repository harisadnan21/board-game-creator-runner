package oogasalad.engine.model.ai.searchTypes;

import static oogasalad.engine.model.board.Piece.PLAYER_ONE;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.depthlimiting.LimitsDepth;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import org.jooq.lambda.Seq;

public class MinMaxSearcher extends Searcher {
  private LimitsDepth limitsDepth;

  public MinMaxSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle Oracle, LimitsDepth limitsDepth) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }


  @Override
  public Choice selectChoice(Board board) {
//    this.limitsDepth.reset();
    Collection<Choice> choices = this.Oracle.getChoices(board, forPlayer);
    return Seq.seq(choices).maxBy(choice -> runMinimax(choice.getResultingBoard(), forPlayer, maxDepth)).get();
  }

  protected int runMinimax(Board board, int player, int depth) {
    if(depth==0 || Oracle.isWinningState(board)) {
      return this.stateEvaluator.evaluate(board, player);
    }
    Stream<Board> boards = this.Oracle.getChoices(board, player).stream().map(Choice::getResultingBoard);
    int nextPlayer = player==PLAYER_ONE ? Piece.PLAYER_TWO : PLAYER_ONE;
    return Seq.seq(boards).mapToInt(currBoard -> runMinimax(currBoard, nextPlayer, depth-1)).max().getAsInt();
  }
}
