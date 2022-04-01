package oogasalad.engine.model.action;

import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.misc.Piece;
import oogasalad.engine.model.board.Board;

public class Place implements Action {

  private Board myResultantBoard;

  public Place(Board initialBoard, int i, int j, Piece piece) {
    Board copy = initialBoard.deepCopy();
    copy.placeNewPiece(i, j, piece);
    myResultantBoard = copy;
  }

  @Override
  public void execute(Game game) {
    game.setBoard(myResultantBoard);
  }

  @Override
  public Board getNextState() {
    return myResultantBoard;
  }
}
