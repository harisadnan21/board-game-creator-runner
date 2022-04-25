package oogasalad.engine.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.driver.BoardHistoryException;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.player.AIPlayerFactory;
import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.player.PlayerManager;
import oogasalad.engine.model.rule.Rule;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.board.Board;

import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.game.BoardView;

public class Controller {

  private GameParser myParser;

  private Board myInitialBoard;
  private Engine myEngine;
  private Game myGame;
  private Oracle myOracle;
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
      myInitialBoard = board;
      myGame = new Game(myInitialBoard);

      Collection<Rule> rules = parser.readRules();

      myNumPlayers = parser.readNumberOfPlayers();

      myOracle = new Oracle(rules, myNumPlayers);


      // TODO: figure out better way to pass in view lambdas
      myEngine = new Engine(myGame, myPlayerManager, myOracle, null);

    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public Controller() {
  }

  /**
   * Starts a game
   * Requires two functions from the active BoardView class
   *
   * Additionally, the board view needs to be added as a listener to the game
   * @param parser
   * @param setValidMarks
   * @param endGame
   * @throws FileNotFoundException
   */
  public void startEngine(GameParser parser, Consumer<Set<Position>> setValidMarks, IntConsumer endGame)
      throws FileNotFoundException {
    Board board = parser.parseBoard();

    myParser = parser;
    myInitialBoard = board;
    myGame = new Game(myInitialBoard);

    Collection<Rule> rules = parser.readRules();

    myNumPlayers = parser.readNumberOfPlayers();

    myOracle = new Oracle(rules, myNumPlayers);

    myPlayerManager = makePlayerManager(myOracle, setValidMarks);

    myEngine = new Engine(myGame, myPlayerManager, myOracle, endGame);
  }

  public PlayerManager makePlayerManager(Oracle oracle, Consumer<Set<Position>> setValidMarks) {
    AIPlayerFactory factory = new AIPlayerFactory();
    Player player = factory.makeAIPlayer(Difficulty.EASY, WinType.TOTAL, 1, oracle, new ArrayList<>());
    PlayerManager manager = new PlayerManager();

    manager.addPlayer(0, new HumanPlayer(oracle, null, setValidMarks));
    manager.addPlayer(1, new HumanPlayer(oracle, null, setValidMarks));
    return manager;
  }

  /**
   * resets the board model to the initial game state
   */
  public void resetGame() {
    myGame.reset(myInitialBoard);
  }

  public void click(int row, int column) throws OutOfBoardException {
    myEngine.onCellSelect(row, column);
  }

  public Engine getEngine(){
    return myEngine;
  }

  /**
   * Gets and returns the game
   * @return : returns the game
   */
  public Game getGame(){
    return myGame;
  }

  /**
   * Gets player manager
   * @return
   */
  public PlayerManager getPlayerManager() {
    return myPlayerManager;
  }

  /**
   * Sets the board of the game
   * @param board
   */
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
  public void saveGame(){

  }
}
