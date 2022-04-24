package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

  public static final String[] DIRECTIONS_ARRY = new String[]{"NORTH", "SOUTH", "EAST", "WEST",
      "NORTHEAST", "NORTHWEST", "SOUTHEAST", "SOUTHWEST"};

  @Test
  void values() {
    // Resource: https://stackoverflow.com/questions/26663399/why-do-the-new-java-8-streams-return-an-object-array-on-toarray-calls
    String[] values = Arrays.stream(Direction.values()).map(Direction::toString).toArray(String[]::new);
    assertArrayEquals(values, DIRECTIONS_ARRY);
  }

  @Test
  void valueOf() {
    for (String dir: DIRECTIONS_ARRY) {
      assertNotNull(Direction.valueOf(dir));
    }
  }

  @ParameterizedTest
  @MethodSource("badValueOfProvider")
  void badValueOf(String badVal) {
    try {
      Direction.valueOf(badVal);
    } catch (IllegalArgumentException exception) {
      assertTrue(true);
    }
  }

  public static Stream<String> badValueOfProvider() {
    String[] args = {"", "Apple", "\t\t\t", "\n\n\n", "      "};
    return Arrays.stream(args);
  }

  }