package oogasalad.engine.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.NoSelectionEngine;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.parsing.GameParser;
import oogasalad.engine.view.BoardView;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;
  private List<Rule> rules;
  private List<WinCondition> winConditions;



  public Controller(Board board) {
    try {
//      myBoard = GameParser.getCheckersBoard();
//      rules = GameParser.getCheckersRules();
      myBoard = board;
      rules = Arrays.asList(GameParser.readRules(Constants.TIC_TAC_TOE_FILE));
      winConditions = Arrays.asList(GameParser.readWinConditions(Constants.TIC_TAC_TOE_FILE));
      myGame = new Game(myBoard);
      myEngine = new PieceSelectionEngine(myGame, rules, winConditions);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * resets the board model to the initial game state
   */
  public Board resetGame() {
    myGame = new Game(myBoard);
    myEngine = new PieceSelectionEngine(myGame, rules, winConditions);
    return myBoard;
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
