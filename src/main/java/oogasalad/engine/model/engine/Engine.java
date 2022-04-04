package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.move.Movement;
import oogasalad.engine.model.player.Player;


public abstract class Engine {

  private Game myGame;

  private List<Movement> myMoves;

  private List<Player> players;

  public Engine(Game game) {
    myGame = game;
    myMoves = new ArrayList<>();
  }

  protected List<Movement> getMoves() {
    return myMoves;
  }

  /**
   *
   * @param player player requesting possible actions
   * @return
   */
  public Movement[] getPossibleActions(int player) {

    return null;
  }

  protected Game getGame() {
    return myGame;
  }

  public abstract Board onCellSelect(int x, int y) throws OutOfBoardException;

}
