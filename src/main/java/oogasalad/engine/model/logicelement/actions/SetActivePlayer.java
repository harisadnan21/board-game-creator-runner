package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

public class SetActivePlayer extends Action {

  private int player;
  /**
   *
   * @param parameters size 1 array where [player] is the new active player in the board
   */
  public SetActivePlayer(int[] parameters) {
    super(parameters);
    player = getParameter(0);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    return board.setPlayer(player);
  }
}
