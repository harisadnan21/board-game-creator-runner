package oogasalad.engine.controller;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.view.BoardView;

public class Controller {

  private Board myBoard;
  public Controller(BoardView boardView, int rows, int columns) {
    myBoard = new Board(rows, columns);
    myBoard.addListener(boardView);
  }

  public void click(int i, int j) {
    myBoard.selectCell(i, j);
  }
}
