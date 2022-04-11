package oogasalad.engine.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.parsing.GameParser;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;

  public Controller(Board board) throws IOException {
    //myBoard.addListener(boardView);
    myGame = new Game(board);
    List<Rule> rules = Arrays.asList(GameParser.readRules(Constants.CHECKERS_FILE));
    List<WinCondition> winConditions = Arrays.asList(GameParser.readWinConditions(Constants.CHECKERS_FILE));
    myEngine = new PieceSelectionEngine(myGame, rules, winConditions);
  }

  public Board click(int i, int j) {
    return myEngine.onCellSelect(i, j);
  }

  public void resetGame() {
  }
}
