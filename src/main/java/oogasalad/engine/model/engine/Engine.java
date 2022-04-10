package oogasalad.engine.model.engine;

import java.util.List;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Rule;

import oogasalad.engine.model.player.Player;


public abstract class Engine {

  private Game myGame;

  private List<Rule> myMoves;
  private List<WinCondition> myWinConditions;

  private List<Player> players;

  public Engine(Game game, List<Rule> rules,
      List<WinCondition> winConditions) {
    myGame = game;
    myWinConditions = winConditions;
    myMoves = rules;
  }

  public Engine(Game game){
    myGame = game;
  }

  protected List<Rule> getMoves() {
    return myMoves;
  }
  protected List<WinCondition> getWinConditions() {
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

  public abstract Board onCellSelect(int x, int y)
      throws OutOfBoardException, CloneNotSupportedException;

  public Board selectCell(int i, int j) {
    return null;
  }
}
