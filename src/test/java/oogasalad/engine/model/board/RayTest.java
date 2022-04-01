package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import io.vavr.Tuple5;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RayTest {

  @BeforeEach
  void setUp() {
  }

  @ParameterizedTest
  @MethodSource("getDirectionalRayProvider")
  void getDirectionalRay() {
  }
  Stream<Tuple5> getDirectionalRayProvider(){

  }

  @Test
  void getDirectionalRayWhileCondition() {
  }

  @Test
  void getDirectionalRayWhileConditions() {
  }

  @Test
  void getDirectionalRayUntilCondition() {
  }

  @Test
  void getDirectionalRayUntilAnyOfConditions() {
  }
}