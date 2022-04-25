package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.board.utilities.Direction;
import oogasalad.engine.model.board.utilities.Ray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class RayTest {

  @Test
  void unhappy() {
    Executable express = () ->
    {var c = Ray.class.getDeclaredConstructor(); c.setAccessible(true); c.newInstance();};
    assertThrows(Exception.class, express );
  }

  @Test
  void getDirectionalRay() {
    Board board = new Board(4,4);
    var ret = Ray.getDirectionalRays(board, new Position(0,0), List.of(Direction.EAST, Direction.SOUTH));
    assertTrue(ret.containsKey(Direction.EAST));
    assertTrue(ret.containsKey(Direction.SOUTH));
    assertFalse(ret.containsKey(Direction.NORTH));
    assertFalse(ret.containsKey(Direction.SOUTHEAST));
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
      assertFalse(positionStateStreamLength1 > 4);
      assertFalse(positionStateStreamLength2 > 4);
      assertFalse(positionStateStreamLength3 > 4);
      assertFalse(positionStateStreamLength4 > 4);
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
      assertFalse(positionStateStreamLength1 > 2);
      assertFalse(positionStateStreamLength2 > 2);
      assertFalse(positionStateStreamLength3 > 2);
      assertFalse(positionStateStreamLength4 > 2);
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
      assertFalse(positionStateStreamLength1 > 1);
      assertFalse(positionStateStreamLength2 > 1);
      assertFalse(positionStateStreamLength3 > 1);
      assertFalse(positionStateStreamLength4 > 1);
      assertFalse(positionStateStreamLength5 > 1);
      assertFalse(positionStateStreamLength6 > 1);
      assertFalse(positionStateStreamLength7 > 1);
      assertFalse(positionStateStreamLength8 > 1);
    }
  }

  @Test
  void getDirectionalRayWhileCondition() {
    Board board = new Board(4,4);
    var s = Ray.getDirectionalRayWhileCondition(board, new Position(2,2), Direction.EAST,
        positionState -> true).toList();
    var s1 =Ray.getDirectionalRay(board, new Position(2,2), Direction.EAST).toList();
    assertEquals(s, s1);
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
        assertEquals(positionStateStream.count(), 0);
        Stream<PositionState> positionStateStream2 = Ray.getDirectionalRayWhileConditions(board, new Position(i, 1), direction, preds2);
        assertEquals(positionStateStream2.count(), 0);
      }
    }
  }

  @Test
  void getDirectionalRayUntilCondition() {
    long c = Ray.getDirectionalRayUntilCondition(new Board(2,2), new Position(0,0), Direction.SOUTH, PositionState::isPresent).count();
    assertEquals(2,c);
  }

  @Test
  void getDirectionalRayUntilAnyOfConditions() {
    long a = Ray.getDirectionalRayUntilAnyOfConditions(new Board(5,5), new Position(0,0), Direction.SOUTH, List.of(PositionState::isPresent, (positionState) -> false)).count();
    assertEquals(5,a);

    long d = Ray.getDirectionalRayUntilAnyOfConditions(new Board(5,5), new Position(0,0), Direction.SOUTH, List.of(PositionState::isPresent, (positionState) -> true)).count();
    assertTrue(d < a);
  }
}