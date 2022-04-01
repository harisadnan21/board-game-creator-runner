package oogasalad.engine.model.driver;

import oogasalad.engine.model.misc.Piece;
import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.action.Place;
import oogasalad.engine.model.action.Remove;
import oogasalad.engine.model.board.Board;

public class Engine {

  private Game myGame;

  public Engine(Game game) {
    myGame = game;
    //ActionType place = (i, j, piece) -> myBoard.placeNewPiece(i, j, piece);
    //ActionType move = (i, j, piece) -> myBoard.move(i, j, piece);
  }

  public Board selectCell(int x, int y){
    Board board = myGame.getBoard();
    Action action;
    if (board.getPiece(x, y) != null) {
      action = new Remove(board, x, y);
    }
    else {
      Piece p = new Piece("Knight", 1);
      action = new Place(board, x, y, p);
    }
    // notifyListeners("UPDATE", oldBoard, myBoard);
    action.execute(myGame);
    return action.getNextState();
  }
}
