package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.action.Move;
import oogasalad.engine.model.action.Place;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.conditions.IsEmpty;
import oogasalad.engine.model.conditions.IsOccupied;
import oogasalad.engine.model.move.Movement;

public abstract class Engine {

  private Game myGame;

  private List<Movement> myMoves;

  public Engine(Game game) {
    myGame = game;
    myMoves = new ArrayList<>();
  }

  protected List<Movement> getMoves() {
    return myMoves;
  }

  protected Game getGame() {
    return myGame;
  }

  public abstract Board onCellSelect(int x, int y) throws OutOfBoardException;

}
