package oogasalad.engine.model.action;

import oogasalad.engine.model.Game;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Board;

public class Remove implements Action {

  private Board myResultantBoard;

  public Remove(Board initialBoard, int i, int j) {
    Board copy = initialBoard.deepCopy();
    copy.remove(i, j);
    myResultantBoard = copy;
  }

  @Override
  public void execute(Game game) {
    game.setBoard(myResultantBoard);
  }

  public Board getNextState() {
    return myResultantBoard;
  }

}
