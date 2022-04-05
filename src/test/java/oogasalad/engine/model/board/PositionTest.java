package oogasalad.engine.model.board;

import static org.jooq.lambda.Seq.zip;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PositionTest {

  public static final Integer[] X_VALS = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
  public static final Integer[] Y_VALS = new Integer[]{11, 22, 33, 44, 55, 66, 77, 88, 99};
  private static Position[] positions1;
  private static Position[] positions2;

  @BeforeAll
  static void beforeAll() {
    positions1 = new Position[9];
    positions2 = new Position[9];
    int count = 0;
    for(Tuple2<Integer, Integer> tup: zip(Arrays.stream(X_VALS), Arrays.stream(Y_VALS))){
      positions1[count] = new Position(tup);
      positions2[count] = new Position(tup.v1, tup.v2);
      count+=1;
    }
  }

  @Test
  void compareTo() {
  }

  @Test
  void x() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertEquals(positions1[i].x(), positions2[i].x());
      Assertions.assertEquals(positions1[i].x(), X_VALS[i]);
      Assertions.assertEquals(positions2[i].x(), X_VALS[i]);
    }
  }

  @Test
  void y() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertEquals(positions1[i].y(), positions2[i].y());
      Assertions.assertEquals(positions1[i].y(), Y_VALS[i]);
      Assertions.assertEquals(positions2[i].y(), Y_VALS[i]);
    }
  }

  @Test
  void constructorEquality() {
    Assertions.assertArrayEquals(positions1, positions2);

  }
  @Test
  void noError() {
    for(int i = 0; i < positions1.length; i++){
      Assertions.assertDoesNotThrow(positions1[i]::x);
      Assertions.assertDoesNotThrow(positions1[i]::y);
      Assertions.assertDoesNotThrow(positions2[i]::x);
      Assertions.assertDoesNotThrow(positions2[i]::y);
    }
  }

  @Test
  void differentXCompare() {
    for(int i = 0; i < positions1.length; i++){
      Position prev = positions1[i];
      for(int j = i+1; j < positions1.length; j++){
        Position curr = positions1[j];
        Assertions.assertTrue(prev.compareTo(curr) < 0);
      }
    }
  }
}