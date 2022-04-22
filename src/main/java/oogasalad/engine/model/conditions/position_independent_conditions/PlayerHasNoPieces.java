package oogasalad.engine.model.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.jooq.lambda.Seq;

/**
 * Condition that evaluates to true when either player has no more pieces on the board
 * @author Robert Cranston
 */
public class PlayerHasNoPieces extends BoardCondition{

  public PlayerHasNoPieces(int[] parameters){
    super(parameters);
  }

  /**
   * Counts the number of pieces each player has and returns true if a player has no pieces.
   * @param board current board state
   * @param referencePoint
   * @return true if a player has no pieces left
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Seq<PositionState> player0 = board.getSatisfyingPositionStatesSeq(posState -> posState.player()==0);
    Seq<PositionState> player1 = board.getSatisfyingPositionStatesSeq(posState -> posState.player()==1);
    return player0.isEmpty() || player1.isEmpty();
  }

}
