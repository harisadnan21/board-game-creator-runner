package oogasalad.engine.model.player;

import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.engine.Engine;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan
 */

public abstract class Player implements PlayerInterface {

  Engine myEngine;
  protected Player(Engine engine) {
    myEngine = engine;
  }

  @Override
  public abstract void playTurn();

  public boolean isMyTurn() {
    Board board = myEngine.getGameStateBoard();
    return false;
  }

  @Override
  public abstract void chooseAction(Action[] PossibleActions);
}
