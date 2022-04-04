package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import oogasalad.engine.model.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PositionTest {

  Position myPosition;
  @BeforeEach
  void setup() {
    myPosition = new Position(1, 1);
  }

  @Test
  void testEquals() {
    Position p2 = new Position(1, 3);
    Position p3 = new Position(1, 1);
    Object p4 = new Position(1, 1);
    int[] p5 = new int[2];

    assertFalse(myPosition.equals(p2));
    assertTrue(myPosition.equals(p3));
    assertTrue(myPosition.equals(p4));
    assertFalse(myPosition.equals(p5));
  }

  @Test
  void positionInSet() {
    Set<Position> set = new HashSet<>();
    set.add(myPosition);
    Position p1 = new Position(1, 1);
    assertTrue(set.contains(p1));
  }
}
