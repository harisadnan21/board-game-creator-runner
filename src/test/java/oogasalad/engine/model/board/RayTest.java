package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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
  void getDirectionalRayWhileTrueConditionLength() {
    Board board4x4 = new Board(2,2);
    Direction[] directions = Direction.values();
    for(Direction direction: directions) {
      long positionStateStreamLength1 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(0,0), direction, positionState -> true).count();
      long positionStateStreamLength2 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(2,2), direction, positionState -> true).count();
      long positionStateStreamLength3 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(0,2), direction, positionState -> true).count();
      long positionStateStreamLength4 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(2,0), direction, positionState -> true).count();
      Assertions.assertFalse(positionStateStreamLength1 > 2);
      Assertions.assertFalse(positionStateStreamLength2 > 2);
      Assertions.assertFalse(positionStateStreamLength3 > 2);
      Assertions.assertFalse(positionStateStreamLength4 > 2);
    }
  }

  @Test
  void getDirectionalRayWhileFalseConditionLength() {
    Board board4x4 = new Board(5,5);
    Direction[] directions = Direction.values();
    for(Direction direction: directions) {
      long positionStateStreamLength1 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(0,0), direction, positionState -> false).count();
      long positionStateStreamLength2 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(2,2), direction, positionState -> false).count();
      long positionStateStreamLength3 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(0,2), direction, positionState -> false).count();
      long positionStateStreamLength4 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(2,0), direction, positionState -> false).count();
      long positionStateStreamLength5 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(4,0), direction, positionState -> false).count();
      long positionStateStreamLength6 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(4,2), direction, positionState -> false).count();
      long positionStateStreamLength7 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(4,2), direction, positionState -> false).count();
      long positionStateStreamLength8 = Ray.getDirectionalRayWhileCondition(board4x4, new Position(4,0), direction, positionState -> false).count();
      Assertions.assertFalse(positionStateStreamLength1 > 1);
      Assertions.assertFalse(positionStateStreamLength2 > 1);
      Assertions.assertFalse(positionStateStreamLength3 > 1);
      Assertions.assertFalse(positionStateStreamLength4 > 1);
      Assertions.assertFalse(positionStateStreamLength5 > 1);
      Assertions.assertFalse(positionStateStreamLength6 > 1);
      Assertions.assertFalse(positionStateStreamLength7 > 1);
      Assertions.assertFalse(positionStateStreamLength8 > 1);
    }
  }

  @Test
  void getDirectionalRayWhileCondition() {
  }

  @Test
  void getDirectionalRayWhileConditionsSimple() {
    Board board = new Board(3,3);
    List<Predicate<PositionState>> preds1 = new ArrayList<>();
    List<Predicate<PositionState>> preds2 = new ArrayList<>();

    for(int i = 0; i < 5; i++) {
      preds1.add(positionState -> true);
      preds2.add(positionState -> false);
    }

    preds1.add(positionState -> false);
    preds2.add(positionState -> true);

    for(int i = 0; i < 3; i++) {
      for(Direction direction: Direction.values()){
        Stream<PositionState> positionStateStream = Ray.getDirectionalRayWhileConditions(board, new Position(i, 1), direction, preds1);
        Assertions.assertEquals(positionStateStream.count(), 0);
        Stream<PositionState> positionStateStream2 = Ray.getDirectionalRayWhileConditions(board, new Position(i, 1), direction, preds2);
        Assertions.assertEquals(positionStateStream2.count(), 0);
      }
    }
  }

  @Test
  void getDirectionalRayUntilCondition() {
  }

  @Test
  void getDirectionalRayUntilAnyOfConditions() {
  }
}