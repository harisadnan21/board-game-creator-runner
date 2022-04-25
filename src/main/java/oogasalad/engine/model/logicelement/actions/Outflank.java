package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * @author Alex Bildner
 */
public class Outflank extends Action {


  /**
   *
   * Almost identical requirements to condition, except if
   * there is an outflanking in this direction
   * flip everything in between to the type and player of the start position
   *
   * should do nothing if there is no outflanking in this direction
   *
   * isAbsolute specifies if the source position adds in the reference point or not
   *
   * @param parameters size - x array [startRow, startColumn, directionRow, directionColumn, isAbsolute]
   */
  protected Outflank(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    return null;
  }
}
