package oogasalad.engine.model.board;

import static org.jooq.lambda.Seq.zip;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Delta;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PositionTest {
  public static final Integer[] ROW_VALS = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
  public static final Integer[] COL_VALS = new Integer[]{11, 22, 33, 44, 55, 66, 77, 88, 99};
  private static Position[] positions1;
  private static Position[] positions2;

  @Test
  void constructor() {
    Assertions.assertInstanceOf(Position.class, new Position(new Tuple2<>(1,1)));
  }

  @Test
  void add() {
    Position position = new Position(1,1);
    Assertions.assertInstanceOf(Position.class, position.add(new Delta(1,1)));
  }

  @BeforeAll
  static void beforeAll() {
    positions1 = new Position[9];
    positions2 = new Position[9];
    int count = 0;
    for(Tuple2<Integer, Integer> tup: zip(Arrays.stream(ROW_VALS), Arrays.stream(COL_VALS))){
      positions1[count] = new Position(tup.v1, tup.v2);
      positions2[count] = new Position(tup.v1, tup.v2);
      count+=1;
    }
  }

  @Test
  void testEquals() {
    Position myPosition = new Position(1, 1);
    Position p2 = new Position(1, 3);
    Position p3 = new Position(1, 1);
    Object p4 = new Position(1, 1);
    int[] p5 = new int[2];

    assertNotEquals(myPosition, p2);
    assertEquals(myPosition, p3);
    assertEquals(myPosition, p4);
    assertNotEquals(myPosition, p5);
  }

  @Test
  void testAdd() {
    Position p1 = new Position(1, 2);
    Position p2 = new Position(5, 2);
    Position sum = p1.add(p2);
    assertEquals(new Position(6, 4), sum);
  }



  @Test
  void compareTo() {
    Position p1 = new Position(1,1);
    Position p2 = new Position(1,2);
    Position p3 = new Position(2,1);
    Position p4 = new Position(2,2);

    assertTrue(p1.compareTo(p2) < 0);
    assertTrue(p1.compareTo(p3) < 0);
    assertTrue(p1.compareTo(p4) < 0);

    assertTrue(p2.compareTo(p3) < 0);
    assertTrue(p2.compareTo(p4) < 0);

    assertTrue(p3.compareTo(p4) < 0);

  }

  @Test
  void i() {
    for(int i = 0; i < positions1.length; i++){
      assertEquals(positions1[i].row(), positions2[i].row());
      assertEquals(positions1[i].row(), ROW_VALS[i]);
      assertEquals(positions2[i].row(), ROW_VALS[i]);
    }
  }

  @Test
  void j() {
    for(int i = 0; i < positions1.length; i++){
      assertEquals(positions1[i].column(), positions2[i].column());
      assertEquals(positions1[i].column(), COL_VALS[i]);
      assertEquals(positions2[i].column(), COL_VALS[i]);
    }
  }

  @Test
  void constructorEquality() {
    Assertions.assertArrayEquals(positions1, positions2);

  }
  @Test
  void noError() {
    for(int i = 0; i < positions1.length; i++){
      assertDoesNotThrow(positions1[i]::row);
      assertDoesNotThrow(positions1[i]::column);
      assertDoesNotThrow(positions2[i]::row);
      assertDoesNotThrow(positions2[i]::column);
    }
  }

  @Test
  void differentICompare() {
    for(int i = 0; i < positions1.length; i++){
      Position prev = positions1[i];
      for(int j = i+1; j < positions1.length; j++){
        Position curr = positions1[j];
        assertTrue(prev.compareTo(curr) < 0);
      }
    }
  }
}
