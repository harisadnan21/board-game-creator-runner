package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static oogasalad.engine.model.board.utilities.BoardUtilities.numPiecesByPlayer;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Returns true if specified player has no pieces on the board
 */
public class SpecificPlayerHasNoPieces extends BoardCondition{

  private int player;
  /**
   *
   * @param parameters size 1 array [player]
   */
  public SpecificPlayerHasNoPieces(int[] parameters){
    super(parameters);
    player = parameters[0];
  }

  /**
   * Counts the number of pieces each player has and returns true if a player has no pieces.
   * @param board current board state
   * @param referencePoint
   * @return true if a player has no pieces left
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return board.getPositionStatesStream().filter(positionState -> positionState.player() == player).count() == 0;
  }
}

