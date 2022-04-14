package oogasalad.engine.model.player;

import java.util.HashMap;
import java.util.Map;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.components.Position;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.move.Move;

public class HumanPlayer extends Player{

  protected HumanPlayer(Engine engine) {
    super(engine);
  }

  @Override
  public void playTurn() {
    Map<Position, Move> map = new HashMap<>();
  }

  @Override
  public void chooseAction(Action[] PossibleActions) {

  }
}
