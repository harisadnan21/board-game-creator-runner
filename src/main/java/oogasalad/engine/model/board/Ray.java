package oogasalad.engine.model.board;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

public class Ray {

  public static final Tuple2<Integer, Integer> NORTH = Tuple.tuple(0, 1);
  public static final Tuple2<Integer, Integer> EAST = Tuple.tuple(1, 0);
  public static final Tuple2<Integer, Integer> SOUTH = Tuple.tuple(0, -1);
  public static final Tuple2<Integer, Integer> WEST = Tuple.tuple(-1, 0);
  public static final Tuple2<Integer, Integer> NORTHEAST = Tuple.tuple(1, 1);
  public static final Tuple2<Integer, Integer> SOUTHEAST = Tuple.tuple(-1, 1);
  public static final Tuple2<Integer, Integer> NORTHWEST = Tuple.tuple(1, -1);
  public static final Tuple2<Integer, Integer> SOUTHWEST = Tuple.tuple(-1, -1);


  public static Stream<PositionState> getDirectionalRay(Board board, Position position, Directions direction)
      throws NoSuchFieldException, IllegalAccessException {
    return Ray.getDirectionalRayWhileCondition(board, position, direction, positionState -> true);
  }


  public static Stream<PositionState> getDirectionalRayWhileCondition(Board board, Position position, Directions direction, Predicate<PositionState> positionStatePredicate)
      throws NoSuchFieldException, IllegalAccessException {
    List<Predicate<PositionState>> positionStatePredicates = List.of(positionStatePredicate, positionState -> true);
    return getDirectionalRayWhileConditions(board, position, direction, positionStatePredicates);
  }


  public static Stream<PositionState> getDirectionalRayWhileConditions(Board board, Position position, Directions direction, List<Predicate<PositionState>> positionStatePredicates)
      throws NoSuchFieldException, IllegalAccessException {
    Builder<PositionState> ray = Stream.<PositionState>builder();
    int x = position.x();
    int y = position.y();
    Tuple2<Integer, Integer> delta = (Tuple2<Integer, Integer>) Ray.class.getField(direction.name()).<Tuple2<Integer,Integer>>get(null);
    Predicate<PositionState> positionStatePredicate = positionStatePredicates.stream().reduce(positionState -> true,
        Predicate::and);
    while(isValid(board, x, y) && passesCondition(board, positionStatePredicate, x, y)){
      ray.accept(board.getPositionStateAt(x,y));
      x = x + delta.v1();
      y = y + delta.v2();
    }

    return ray.build();

  }

  public static Stream<PositionState> getDirectionalRayUntilCondition(Board board, Position position, Directions direction, Predicate<PositionState> positionStatePredicate)
      throws NoSuchFieldException, IllegalAccessException {
    return getDirectionalRayWhileCondition(board, position,direction, positionStatePredicate.negate());
  }

  public static Stream<PositionState> getDirectionalRayUntilAnyOfConditions(Board board, Position position, Directions direction, List<Predicate<PositionState>> positionStatePredicates)
      throws NoSuchFieldException, IllegalAccessException {
    Predicate<PositionState> positionStatePredicate = positionStatePredicates.stream().reduce(positionState -> false,
        Predicate::or);
    return getDirectionalRayWhileCondition(board, position,direction, positionStatePredicate.negate());
  }

  private static boolean passesCondition(Board board, Predicate<PositionState> positionStatePredicate, int x,
      int y) {
    return positionStatePredicate.test(board.getPositionStateAt(x, y));
  }

  private static boolean isValid(Board board, int x, int y) {
    return board.isValid(x, y);
  }

}
