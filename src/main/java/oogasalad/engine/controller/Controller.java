package oogasalad.engine.controller;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import oogasalad.engine.model.board.ImmutableBoard;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.driver.BoardHistoryException;
import oogasalad.engine.model.player.PlayerManager;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.board.Board;

import oogasalad.engine.model.rule.Move;
import oogasalad.engine.model.parser.GameParser;
import org.jooq.lambda.function.Consumer0;

public class Controller {

  private Board myBoard;
  private Engine myEngine;
  private Game myGame;
  private Collection<Move> moves;
  private Collection<EndRule> endRules;
  private Consumer<Board> updateView;
  private Consumer<Set<Position>> setViewValidMarks;
  private IntConsumer endGame;

  private PlayerManager myPlayerManager;
  private int myNumPlayers;

  /**
   * Constructor for the controller
   * @param board: the board that the game in the engine uses
   * @param parser : the parser that is used
   */
  public Controller(Board board, GameParser parser) {
    try {
      myBoard = board;
      myGame = new Game(myBoard, null);

      moves = parser.readRules();
      endRules = parser.readWinConditions();

      myNumPlayers = parser.readNumberOfPlayers();

      // TODO: figure out better way to pass in view lambdas
      myEngine = new Engine(myGame, moves, endRules, null, null, null);

    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * resets the board model to the initial game state
   */
  public Board resetGame() {
    myGame = new Game(myBoard, updateView);

    myEngine = new Engine(myGame, moves, endRules, updateView, setViewValidMarks, endGame);

    return myBoard;
  }

  public void click(int i, int j ) throws OutOfBoardException {
    myEngine.onCellSelect(i, j);
  }

  public Board setCallbackUpdates(Consumer<Board> update, Consumer<Set<Position>> setValidMarks, IntConsumer endGame){
    updateView = update;
    setViewValidMarks = setValidMarks;
    this.endGame = endGame;
    myGame = new Game(myBoard, updateView);
    myEngine = new Engine(myGame, moves, endRules, updateView, setViewValidMarks, endGame);
    myEngine.gameLoop();

    return myBoard;
  }

  /**
   * gets and returns the game
   * @return : returns the game
   */
  public Game getGame(){
    return myGame;
  }

  /**
   * Function starts the game
   */
  public void startGame() {
    myEngine.gameLoop();
  }

  public void setBoard(Board board){
    myGame.setBoard(board);
  }

  /**
   * Undoes number of actions by user by the integer provided
   * @param numberOfUndoes : number of boards in history to go back to
   * @throws BoardHistoryException
   */
  public void undoGame(int numberOfUndoes) throws BoardHistoryException {
    myGame.backByAmount(numberOfUndoes);
  }

  /**
   * Undoes the action previously done
   * @throws BoardHistoryException
   */
  public Board undoGameOnce() throws BoardHistoryException {
    myGame.back();
    return myGame.getBoard();
  }
}
