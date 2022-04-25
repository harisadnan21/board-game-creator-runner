package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static oogasalad.engine.model.board.utilities.BoardUtilities.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.utilities.BoardUtilities;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.jooq.lambda.Seq;

/**
 * Condition that evaluates to true when either player has no more pieces on the board
 * @author Alex Bildner, Robert Cranston
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
    return numPiecesByPlayer(board).containsValue(0);
  }

}
