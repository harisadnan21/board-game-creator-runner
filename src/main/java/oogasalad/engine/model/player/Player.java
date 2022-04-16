package oogasalad.engine.model.player;

import oogasalad.engine.model.Oracle;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.utilities.Pair;

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
  public abstract Pair<Position, Move> chooseMove(Engine oracle, Board board);

  public boolean isMyTurn() {
    Board board = myEngine.getGameStateBoard();
    return false;
  }
}
