package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

  }

  @Test
  void values() {
  }

  @Test
  void valueOf() {

  }

  @ParameterizedTest
  @MethodSource("badValueOfProvider")
  void badValueOf(String badVal) {
    try {
      Direction.valueOf(badVal);
    } catch (Exception exception) {
      Assertions.assertTrue(true);
    }
  }

  public static Stream<String> badValueOfProvider() {
    String[] args = {null, "", "Apple", "\t\t\t", "\n\n\n", "      "};
    return Arrays.stream(args);
  }

  }