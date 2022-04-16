package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.move.Rule;

import oogasalad.engine.model.player.Player;


public abstract class Engine {

  private Game myGame;

  private Collection<Rule> myMoves;
  private Collection<WinCondition> myWinConditions;

  private Collection<Player> players;

  public Engine(Game game, Collection<Rule> rules,
      Collection<WinCondition> winConditions) {
    myGame = game;
    myWinConditions = winConditions;
    myMoves = rules;
  }

  protected Collection<Rule> getMoves() {
    return myMoves;
  }
  protected Collection<WinCondition> getWinConditions() {
    return myWinConditions;
  }

  /**
   * @param player player requesting possible actions
   * @return
   */
  public Rule[] getPossibleActions(int player) {

    return null;
  }

  protected Game getGame() {
    return myGame;
  }

  public abstract Board onCellSelect(int x, int y) throws OutOfBoardException;

}
