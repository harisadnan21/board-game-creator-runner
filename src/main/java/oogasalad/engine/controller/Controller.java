package oogasalad.engine.controller;

import java.io.IOException;
import java.util.List;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.NoSelectionEngine;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.parsing.GameParser;
import oogasalad.engine.view.BoardView;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;

  public Controller(BoardView boardView, int rows, int columns) {
    List<Rule> rules;
    try {
      myBoard = GameParser.getCheckersBoard();
      rules = GameParser.getCheckersRules();
      myGame = new Game(myBoard);
      myEngine = new PieceSelectionEngine(myGame, rules);
    } catch (Exception e){
      e.printStackTrace();
    }
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
