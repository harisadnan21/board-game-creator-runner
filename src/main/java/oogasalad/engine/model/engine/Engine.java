package oogasalad.engine.model.engine;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.player.PlayerManager;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.rule.Move;

import oogasalad.engine.model.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The type Engine.
 */
public class Engine {

  private static final Logger LOG = LogManager.getLogger(Engine.class);

  private Map<Integer, Player> myPlayers = new HashMap<>();

  private PlayerManager myPlayerManager;

  // from Engine
  private Game myGame;

  private Oracle myOracle;

  private IntConsumer myEndGame;

  /**
   * Instantiates a new Engine.
   *
   * @param game
   * @param players
   * @param oracle
   * @param setValidMarks
   * @param endGame
   */
  public Engine(Game game, PlayerManager players, Oracle oracle, Consumer<Set<Position>> setValidMarks, IntConsumer endGame) {

    myGame = game;

    myPlayerManager = players;

    myEndGame = endGame;

    myOracle = oracle;

    players.addDependencies(myOracle, this::playTurn, setValidMarks);

    pingActivePlayer();

  }

  /**
   * The T.
   */
  public Timer t;

  /**
   * Game loop.
   */
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

  public void pingActivePlayer() {
    LOG.info("Player pinged: {}\n", getGameBoard().getPlayer());
    myPlayerManager.getPlayer(getGameBoard().getPlayer()).chooseMove(getGameBoard());
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
      if (move.isValid(getGameBoard(), referencePoint)) {
        //Board board = move.doMove(getGameBoard(), referencePoint);
        Board board = myOracle.getNextState(getGameBoard(), choice);
        LOG.info("{} executed at {},{}", move.getName(), referencePoint.row(), referencePoint.column());

        board = incrementPlayer(board);
        myGame.setBoard(board);

        checkWin();

        pingActivePlayer();

      } else {
        LOG.warn("Player's move is not valid");
      }
    } else {
      LOG.warn("inactive player tried to execute move");
    }
  }

  public Board incrementPlayer(Board board) {
    return myOracle.incrementPlayer(board);
  }


  public void checkWin() {
    if (myOracle.isWinningState(getGameBoard())) {
      int winner = myOracle.getWinner(getGameBoard());
      if (myPlayerManager.playerExists(winner)) {
        myPlayerManager.getPlayer(winner).updateScore(1);
      }
      endGame(winner);
    }
  }

  public void endGame(int winner) {
    if (myEndGame != null) {
      myEndGame.accept(winner);
    }
  }

  /**
   * Returns true if player object is the active player in the game
   * @param player
   * @return
   */
  private boolean isActivePlayer(Player player) {
    return myPlayerManager.isActivePlayer(player, myGame.getBoard());
  }

  /**
   * Handles cell selection from controller
   *
   * @param row    the row
   * @param column the column
   */
// TODO: move to controller
  public void onCellSelect(int row, int column) {
    Board board = getGameBoard();
    Player activePlayer = myPlayerManager.getPlayer(board.getPlayer());
    activePlayer.onCellSelect(row, column);
  }

  /**
   * Gets game board.
   *
   * @return the board which is at the head of the game's board stack
   */
  public Board getGameBoard() {
    return myGame.getBoard();
  }
}
