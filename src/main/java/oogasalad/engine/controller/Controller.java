package oogasalad.engine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.parsing.GameParser;
import org.jooq.lambda.function.Consumer0;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;
  private List<Move> moves;
  private List<WinCondition> winConditions;
  private Consumer<Board> updateView;
  private Consumer<Set<Position>> setViewValidMarks;
  private Consumer0 clearViewMarkers;




  public Controller(Board board) {
    try {
//      myBoard = GameParser.getCheckersBoard();
//      rules = GameParser.getCheckersRules();
      myBoard = board;

      moves = Arrays.asList(GameParser.readRules(Constants.CHECKERS_FILE));
      winConditions = Arrays.asList(GameParser.readWinConditions(Constants.CHECKERS_FILE));

      myGame = new Game(myBoard, updateView);

    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * resets the board model to the initial game state
   */
  public Board resetGame() {
    myGame = new Game(myBoard, updateView);

    myEngine = new PieceSelectionEngine(myGame, moves, winConditions, updateView, setViewValidMarks, clearViewMarkers);

    return myBoard;
  }

  public void click(int i, int j ) throws OutOfBoardException {
    myEngine.onCellSelect(i, j);
  }

  public Board setCallbackUpdates(Consumer<Board> update, Consumer<Set<Position>> setValidMarks, Consumer0 clearMarkers){
    updateView = update;
    setViewValidMarks = setValidMarks;
    clearViewMarkers = clearMarkers;

    myGame = new Game(myBoard, updateView);
    myEngine = new PieceSelectionEngine(myGame, moves, winConditions, updateView, setViewValidMarks, clearViewMarkers);

    return myBoard;
  }


  public void saveGame(){

  }
  //TODO: Add functionality to have turns and have the program run.

}
