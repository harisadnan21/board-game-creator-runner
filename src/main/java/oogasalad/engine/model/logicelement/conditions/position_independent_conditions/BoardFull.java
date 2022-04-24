package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;

/**
 * Condition that evaluates to true when the entire board is full of pieces
 * @author Alex Bildner, Robert Cranston
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
    return noEmptyPieces(board.getPositionStatesStream());
  }

  private boolean noEmptyPieces(Stream<PositionState> positionStatesStream) {
    return positionStatesStream.noneMatch(positionState -> positionState.piece() == Piece.EMPTY);
  }
}
