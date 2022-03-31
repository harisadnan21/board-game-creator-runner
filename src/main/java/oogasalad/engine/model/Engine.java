package oogasalad.engine.model;

import oogasalad.engine.model.action.ActionType;
import oogasalad.engine.model.board.Board;

public class Engine {

  private Board myBoard;

  public Engine() {

    ActionType place = (i, j, piece) -> myBoard.placeNewPiece(i, j, piece);
    ActionType move = (i, j, piece) -> myBoard.move(i, j, piece);
  }

}
