package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static oogasalad.engine.model.board.utilities.BoardUtilities.numPiecesByPlayer;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Returns true if specified player has no pieces on the board
 */
public class SpecificPlayerHasNoPieces extends BoardCondition{
  private int player;
  private boolean invert;

  /**
   *
   * @param parameters size 2 array [player, invert]
   */
  public SpecificPlayerHasNoPieces(int[] parameters){
    super(parameters);
    player = getParameter(0);
    invert = getParameter(1) != 0;
  }

  /**
   * Counts the number of pieces each player has and returns true if a player has no pieces.
   * @param board current board state
   * @param referencePoint
   * @return true if a player has no pieces left
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return invertIfTrue(board.getPositionStatesStream().noneMatch(positionState -> positionState.player() == player), invert);
  }
}

