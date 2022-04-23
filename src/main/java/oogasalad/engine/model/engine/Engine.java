package oogasalad.engine.model.engine;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import oogasalad.engine.model.ai.RandomPlayer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.player.InteractivePlayer;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.rule.Move;

import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Engine {

  private static final Logger LOG = LogManager.getLogger(Engine.class);

  private final Map<Integer, InteractivePlayer> myPlayers = new HashMap<>();

  // from Engine
  private Game myGame;

  private Oracle myOracle;

  private Consumer<Set<Position>> setViewValidMarks;

  public Engine(Game game, Collection<Move> moves,
      Collection<EndRule> endRules, Consumer<Board> update, Consumer<Set<Position>> setValidMarks) {

    myGame = game;
    setViewValidMarks = setValidMarks;

    int numPlayers = 2; //TODO: automate player creation

    if (moves == null) {
      LOG.warn("moves are null");
    }
    myOracle = new Oracle(moves, endRules, numPlayers);

    myPlayers.put(0, new HumanPlayer(myOracle, this::playTurn, setValidMarks));
    myPlayers.put(1, new RandomPlayer(myOracle, this::playTurn));

    pingActivePlayer();

  }

  public Timer t;

  public synchronized void gameLoop() {
    if (null != null) {
      TimerTask task = new TimerTask() {
        @Override
        public void run() {
          pingActivePlayer();
        }
      };

      t = new Timer();
      t.scheduleAtFixedRate(task, 0, 4000);
    }
  }

  private void pingActivePlayer() {
    LOG.info("Player pinged: {}\n", getGameStateBoard().getPlayer());
    myPlayers.get(getGameStateBoard().getPlayer()).setGameBoard(myGame.getBoard());
  }

  /**
   * This method gets passed as a lambda to the player class, which uses it to
   * execute moves
   * @param player
   * @param choice
   */
  private void playTurn(Player player, Choice choice) {
    if (isActivePlayer(player)) {
      Move move = choice.move();
      Position referencePoint = choice.position();
      if (move.isValid(getGameStateBoard(), referencePoint)) {
        Board board = move.doMove(getGameStateBoard(), referencePoint);
        // LOG.info("{} executed at {},{}", move.getName(), referencePoint.row(), referencePoint.column());

        board = myOracle.incrementPlayer(board);
        myGame.setBoard(board);

        pingActivePlayer();

      } else {
        LOG.warn("Player's move is not valid");
      }
    }
  }

  /**
   * Returns true if player object is the active player in the game
   * @param player
   * @return
   */
  public boolean isActivePlayer(Player player) {
    int playerID = getPlayerID(player);
    Board board = getGameStateBoard();
    return playerID == board.getPlayer();
  }

  private int getPlayerID(Player player) {
    int queryingPlayerID = -1;
    for (int playerID : myPlayers.keySet()) {
      if (myPlayers.get(playerID) == player) {
        queryingPlayerID = playerID;
      }
    }
    if (queryingPlayerID == -1) {
      throw new RuntimeException("Player does not exist in this game");
    }
    return queryingPlayerID;
  }

  /**
   * Handles cell selection from controller
   * @param row
   * @param column
   */
  public void onCellSelect(int row, int column) {
    Board board = getGameStateBoard();
    InteractivePlayer activePlayer = myPlayers.get(board.getPlayer());
    activePlayer.onCellSelect(row, column);
  }

  /**
   *
   * @return the board which is at the head of the game's board stack
   */
  public Board getGameStateBoard() {
    return myGame.getBoard();
  }
}
