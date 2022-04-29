package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.board.utilities.Direction;
import oogasalad.engine.model.board.utilities.Ray;
import org.junit.jupiter.api.Test;

public class RayBugTest {

  @Test
  void getRayOfMaxLength() {
    Board board = new Board(10,10);
    Collection<PositionState> ray = Ray.getRayOfMaxLength(board, new Position(2,2), new Position(0,1), 3);
    assertEquals(3, ray.size());

  }

}
