package oogasalad.engine.controller;

import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.NoSelectionEngine;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.view.BoardView;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;

  public Controller(BoardView boardView, int rows, int columns) {
    myBoard = new Board(rows, columns);
    //myBoard.addListener(boardView);
    myGame = new Game(myBoard);
    myEngine = new PieceSelectionEngine(myGame);

  }

  public Board click(int i, int j) throws OutOfBoardException {
    Board board = myEngine.onCellSelect(i, j);
    System.out.printf("Player %d's turn\n", board.getPlayer());
    return board;
  }
  public void saveGame(){

  }
  //TODO: Add functionality to have turns and have the program run.

}
