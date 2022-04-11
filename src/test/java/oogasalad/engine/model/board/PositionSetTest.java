package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;


public class PositionSetTest {
  @Test
  void positionInSet() {
    Set<Position> set = new HashSet<>();
    Position myPosition = new Position(1, 1);
    set.add(myPosition);
    Position p1 = new Position(1, 1);
    assertTrue(set.contains(p1));
  }

}
