package oogasalad.engine.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.parser.GameParser;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;
  private Collection<Rule> rules;
  private Collection<WinCondition> winConditions;



  public Controller(Board board, int rows, int columns) {
    try {
      // TODO: Replace this with some way to pick the configuration directory
      GameParser parser = new GameParser(new File("data/checkers/config.json"));
      myBoard = board;
      rules = parser.readRules();
      winConditions = parser.readWinConditions();
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
