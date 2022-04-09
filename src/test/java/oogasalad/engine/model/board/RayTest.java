package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RayTest {

  @Test
  void getDirectionalRay() {
  }

  @Test
  void directionalRayLength() {
    Board board4x4 = new Board(4,4);
    Direction[] directions = Direction.values();
    for(Direction direction: directions) {
      long positionStateStreamLength1 = Ray.getDirectionalRay(board4x4, new Position(0,0), direction).count();
      long positionStateStreamLength2 = Ray.getDirectionalRay(board4x4, new Position(3,3), direction).count();
      long positionStateStreamLength3 = Ray.getDirectionalRay(board4x4, new Position(0,3), direction).count();
      long positionStateStreamLength4 = Ray.getDirectionalRay(board4x4, new Position(3,0), direction).count();
      Assertions.assertFalse(positionStateStreamLength1 > 4);
      Assertions.assertFalse(positionStateStreamLength2 > 4);
      Assertions.assertFalse(positionStateStreamLength3 > 4);
      Assertions.assertFalse(positionStateStreamLength4 > 4);
    }
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