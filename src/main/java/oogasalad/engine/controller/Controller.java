package oogasalad.engine.controller;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;

  public Controller(Board boardView, int rows, int columns) {
    myBoard = new Board(rows, columns);
    //myBoard.addListener(boardView);
    myGame = new Game(myBoard);
    myEngine = new Engine(myGame) {
      @Override
      public Board onCellSelect(int x, int y) throws OutOfBoardException {
        return null;
      }
    };
  }

  public Board click(int i, int j) {
    return myEngine.selectCell(i, j);
  }

  public void resetGame() {
  }
}
