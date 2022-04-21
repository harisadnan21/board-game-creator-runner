package oogasalad.engine.model.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;

/**
 * Condition that evaluates to true when the entire board is full of pieces
 * @author Robert Cranston
 */
public class BoardFull extends BoardCondition{

  public BoardFull(int[] parameters){
    super(parameters);
  }
  /**
   * Checks every board cell and returns true if every cell has a piece
   * @param board current board state
   * @param referencePoint
   * @return true if the board has no empty spaces
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint){
    return board.getPositionStatesStream().noneMatch(positionState -> positionState.piece() == Piece.EMPTY);
  }
}
