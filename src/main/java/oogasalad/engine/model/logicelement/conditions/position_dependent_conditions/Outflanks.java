package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static java.lang.Math.*;
import static oogasalad.engine.model.board.cells.Piece.PLAYER_ONE;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Delta;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Defines outflanks condition
 *
 * Outflanks returns true if there exists a line of pieces between two points, where the endpoints
 * are owned by one player, and the inner pieces are owned by a different player
 *
 * @author Alex Bildner, Jake Heller
 */
public class Outflanks extends Condition {


  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private boolean isAbsolute;

  /**
   * Returns true if there exists a line starting from the start position moving in the specified direction
   * where the start position is of a certain player, everything in the middle of the line is of a different player,
   * and the end point is of the same type as the starting player
   *
   * For the purposes of this function direction does not specifically have to move to adjacent cells
   * it can be [2,2] and look at every other cell
   *
   * isAbsolute specifies whether the starting position should add in the reference point or ignore it
   *
   * @param parameters array of size 5 [startRow, startColumn, directionRow, directionColumn, isAbsolute]
   */
  public Outflanks(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position firstPosition = new Position(0,0);
    Position secondPosition = new Position(0,0);
    //TODO: fix above
    return samePlayerAtPositions(board, firstPosition, secondPosition) && otherPlayerBetweenPositions(board, firstPosition, secondPosition);
    
  }

  private boolean otherPlayerBetweenPositions(Board board, Position firstPosition, Position secondPosition) {
    Delta delta = new Delta(secondPosition.row() - firstPosition.row(), secondPosition.column() - firstPosition.column());
    if(notInLine(delta)) return false;

    int incrementDistance = delta.idelta()/ abs(delta.idelta());

    int playerNeeded = otherPlayer(board, firstPosition);
    Delta increment = new Delta(incrementDistance, incrementDistance);

    Position positionToCheck = firstPosition.add(increment);

    while (!positionToCheck.equals(secondPosition)) {
      if (board.getPositionStateAt(positionToCheck).player() != playerNeeded) {
        return false;
      }
      positionToCheck = positionToCheck.add(increment);
    }
    return true;

  }

  private boolean notInLine(Delta delta) {
    return abs(delta.idelta()) != abs(delta.jdelta());
  }

  private int otherPlayer(Board board, Position firstPosition) {
    return PLAYER_ONE == board.getPositionStateAt(firstPosition).player()? Piece.PLAYER_TWO : PLAYER_ONE;
  }

  private boolean samePlayerAtPositions(Board board, Position firstPosition, Position secondPosition) {
    return board.getPositionStateAt(firstPosition).player() == board.getPositionStateAt(
        secondPosition).player();
  }

}
