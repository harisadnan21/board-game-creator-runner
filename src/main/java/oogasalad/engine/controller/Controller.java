package oogasalad.engine.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class Controller<aiDificulties> {

  private GameParser myParser;

  private Board myInitialBoard;
  private Engine myEngine;
  private Game myGame;
  private Oracle myOracle;
  private Consumer<Set<Position>> setViewValidMarks;
  private IntConsumer endGame;
  private PlayerManager myPlayerManager;
  private int myNumPlayers;
  private String[] myPlayers;
  private Map<String, Player> playerMap;

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

  public Controller(String[]players) {
    myPlayers = players;
  }

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
    PlayerManager manager = new PlayerManager();
    for(int i = 0; i< myPlayers.length; i++) {
      createPlayerMap(oracle, setValidMarks, factory);
      manager.addPlayer(i, playerMap.get(myPlayers[i]));
    }
    return manager;
  }

  private void createPlayerMap(Oracle oracle, Consumer<Set<Position>> setValidMarks,
      AIPlayerFactory factory) {
    playerMap = new HashMap<>();
    playerMap.put("human", new HumanPlayer(oracle, null, setValidMarks));
    for(Difficulty level : Difficulty.values()){
      playerMap.put(level.name(), factory.makeAIPlayer(level, WinType.TOTAL, 1, oracle, new ArrayList<>()));
    }
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
   * gets and returns the game
   * @return : returns the game
   */
  public Game getGame(){
    return myGame;
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
  public void saveGame(){

  }
}
