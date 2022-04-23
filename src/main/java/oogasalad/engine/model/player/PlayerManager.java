package oogasalad.engine.model.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import oogasalad.engine.model.ai.AIPlayer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;


public class PlayerManager {

  private Map<Integer, Player> myPlayers;

  private Oracle myActiveOracle;
  private Game myActiveGame;
  private BiConsumer<Player, Choice> myActiveExecuteMove;
  private Consumer<Set<Position>> setValidMarks;

  public PlayerManager() {
    myPlayers = new HashMap<>();
  }

  /**
   * Returns true if player object is the active player in the game
   * @param player
   * @return
   */
  public boolean isActivePlayer(Player player, Board board ) {
    int playerID = getPlayerID(player);
    return playerID == board.getPlayer();
  }

  /**
   * Gets ID of player
   * @param player player object
   * @return
   */
  public int getPlayerID(Player player) {
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
   * Returns player object for ID
   * @param playerID
   * @return
   */
  public Player getPlayer(int playerID) {
    try {
      return myPlayers.get(playerID);
    } catch (Exception e) {
      throw new RuntimeException("Player does not exist in this game");
    }
  }

  /**
   * True if player exists
   *
   * @param playerID
   * @return
   */
  public boolean playerExists(int playerID) {
    return myPlayers.containsKey(playerID);
  }

  /**
   * True if player exists
   *
   * @param player
   * @return
   */
  public boolean playerExists(Player player ) {
    return myPlayers.containsValue(player);
  }

  /**
   * Adds player with specified ID
   * @param playerID
   * @param player
   */
  public void addPlayer(int playerID, Player player) {
    System.out.println("Player version called");
    myPlayers.put(playerID, player);
  }

  public void addPlayer(int playerID, AIPlayer player) {
    System.out.println("AIPlayer version called");
  }

}