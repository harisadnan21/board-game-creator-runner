package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.move.Rule;

import oogasalad.engine.model.player.Player;


public abstract class Engine {

  private Game myGame;

  private List<Rule> myMoves;
  private List<WinCondition> myWinConditions;

  private List<Player> players;

  public Engine(Game game, List<Rule> rules) {
    myGame = game;
<<<<<<< HEAD
    myMoves = new ArrayList<>();
    myWinConditions = new ArrayList<>();
=======
    myMoves = rules;
>>>>>>> 3adab346f07d644d721c464f3360561541cc2428
  }

  protected List<Rule> getMoves() {
    return myMoves;
  }
  protected List<WinCondition> getWinConditions() {
    return myWinConditions;
  }

  /**
   *
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
