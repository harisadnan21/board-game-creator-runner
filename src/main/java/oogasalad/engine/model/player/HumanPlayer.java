package oogasalad.engine.model.player;

import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.utilities.Pair;
import org.jooq.lambda.function.Consumer0;

public class HumanPlayer extends Player{


  public HumanPlayer(Engine engine, Consumer<Set<Position>> setValidMarks, Consumer0 clearMarkers) {
    super(engine);
  }

  @Override
  public Pair<Position, Move> chooseMove(Engine oracle, Board board) {
    return null;
  }

}
