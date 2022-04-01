package oogasalad.engine.controller;

import oogasalad.engine.model.driver.Engine;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.view.BoardView;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;

  public Controller(BoardView boardView, int rows, int columns) {
    myBoard = new Board(rows, columns);
    //myBoard.addListener(boardView);
    myGame = new Game(myBoard);
    myEngine = new Engine(myGame);
  }

  public Board click(int i, int j) {
    return myEngine.selectCell(i, j);
  }
}
