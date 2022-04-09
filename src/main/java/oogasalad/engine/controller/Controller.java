package oogasalad.engine.controller;

import java.util.Arrays;
import java.util.List;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.engine.NoSelectionEngine;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.parsing.GameParser;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;
  private List<Rule> rules;
  private List<WinCondition> winConditions;



  public Controller(Board board, int rows, int columns) {
    try {
//      myBoard = GameParser.getCheckersBoard();
//      rules = GameParser.getCheckersRules();
      myBoard = board;
      rules = Arrays.asList(GameParser.readRules(GameParser.CHECKERS_FILE));
      winConditions = GameParser.getCheckersWinConditions();
      myGame = new Game(myBoard);
      myEngine = new PieceSelectionEngine(myGame, rules, winConditions);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * resets the board model to the initial game state
   */
  public void resetGame() {
    myGame = new Game(myBoard);
    myEngine = new PieceSelectionEngine(myGame, rules, winConditions);
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
