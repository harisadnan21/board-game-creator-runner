package oogasalad.engine.model.engine;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.ai.RandomPlayer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.rule.Move;

import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Engine {

  private static final Logger LOG = LogManager.getLogger(Engine.class);

  private Map<Integer, Player> myPlayers = new HashMap<>();

  // from Engine
  private Game myGame;

  private Oracle myOracle;

  private Collection<Move> myMoves;
  private Consumer<Set<Position>> setViewValidMarks;

  public Engine(Game game, Collection<Move> moves,
      Collection<EndRule> endRules, Consumer<Board> update, Consumer<Set<Position>> setValidMarks) {

    myGame = game;
    myMoves = moves;
    setViewValidMarks = setValidMarks;

    int numPlayers = 2; //TODO: automate player creation

    if (moves == null) {
      LOG.warn("moves are null");
    }
    myOracle = new Oracle(moves, endRules, new ArrayList<>(), numPlayers);

    myPlayers.put(0, new HumanPlayer(myOracle, myGame, this::playTurn, setValidMarks));
    myPlayers.put(1, new RandomPlayer(myOracle, myGame, this::playTurn));

  }

  public void gameLoop() throws InterruptedException {
    while(true) {
      Thread.sleep(2000);
      myPlayers.get(getGameStateBoard().getPlayer()).chooseMove();
    }
  }

  private void playTurn(Player player, Choice choice) {

    if (isActivePlayer(player)) {
      Move move = choice.move();
      Position referencePoint = choice.position();
      if (move.isValid(getGameStateBoard(), referencePoint)) {
        Board board = move.doMove(getGameStateBoard(), referencePoint);
        board = board.setPlayer(board.getPlayer()+1);
        LOG.info("{} executed at {},{}", move.getName(), referencePoint.row(), referencePoint.column());

        board = myOracle.incrementPlayer(board);
        myGame.setBoard(board);

        // ping next player
        myPlayers.get(board.getPlayer()).chooseMove();

      } else {
        LOG.warn("Player's move is not valid");
      }
    }
  }

  private void wait(double millis) {
    Instant instant = Instant.now();
  }

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

  public void onCellSelect(int i, int j) {
    Board board = getGameStateBoard();
    Player activePlayer = myPlayers.get(board.getPlayer());
    activePlayer.onCellSelect(i, j);
  }

  /**
   *
   * @return the board which is at the head of the game's board stack
   */
  public Board getGameStateBoard() {
    return myGame.getBoard();
  }
}
