package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.components.Position;
import org.junit.jupiter.api.Test;
import static org.jooq.lambda.Seq.zip;

import java.util.Arrays;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class PositionTest {
  public static final Integer[] ROW_VALS = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
  public static final Integer[] COL_VALS = new Integer[]{11, 22, 33, 44, 55, 66, 77, 88, 99};
  private static Position[] positions1;
  private static Position[] positions2;

  @BeforeAll
  static void beforeAll() {
    positions1 = new Position[9];
    positions2 = new Position[9];
    int count = 0;
    for(Tuple2<Integer, Integer> tup: zip(Arrays.stream(ROW_VALS), Arrays.stream(COL_VALS))){
      positions1[count] = new Position(tup);
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
  void compareTo() {
    Position p1 = new Position(1,1);
    Position p2 = new Position(1,2);
    Position p3 = new Position(2,1);
    Position p4 = new Position(2,2);

    Assertions.assertTrue(p1.compareTo(p2) < 0);
    Assertions.assertTrue(p1.compareTo(p3) < 0);
    Assertions.assertTrue(p1.compareTo(p4) < 0);

    Assertions.assertTrue(p2.compareTo(p3) < 0);
    Assertions.assertTrue(p2.compareTo(p4) < 0);

    Assertions.assertTrue(p3.compareTo(p4) < 0);

  }

  @Test
  void i() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertEquals(positions1[i].i(), positions2[i].i());
      Assertions.assertEquals(positions1[i].i(), ROW_VALS[i]);
      Assertions.assertEquals(positions2[i].i(), ROW_VALS[i]);
    }
  }

  @Test
  void j() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertEquals(positions1[i].j(), positions2[i].j());
      Assertions.assertEquals(positions1[i].j(), COL_VALS[i]);
      Assertions.assertEquals(positions2[i].j(), COL_VALS[i]);
    }
  }

  @Test
  void constructorEquality() {
    Assertions.assertArrayEquals(positions1, positions2);

  }
  @Test
  void noError() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertDoesNotThrow(positions1[i]::i);
      Assertions.assertDoesNotThrow(positions1[i]::j);
      Assertions.assertDoesNotThrow(positions2[i]::i);
      Assertions.assertDoesNotThrow(positions2[i]::j);
    }
  }

  @Test
  void differentICompare() {
    for(int i = 0; i < positions1.length; i++){
      Position prev = positions1[i];
      for(int j = i+1; j < positions1.length; j++){
        Position curr = positions1[j];
        Assertions.assertTrue(prev.compareTo(curr) < 0);
      }
    }
  }
}
