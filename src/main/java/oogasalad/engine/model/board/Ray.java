package oogasalad.engine.model.board;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import oogasalad.engine.model.Constants;
import oogasalad.engine.model.Delta;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

public class Ray {

  public static Stream<PositionState> getDirectionalRay(Board board, Position position, Direction direction) {
    return Ray.getDirectionalRayWhileCondition(board, position, direction, positionState -> true);
  }


  public static Stream<PositionState> getDirectionalRayWhileCondition(Board board, Position position, Direction direction, Predicate<PositionState> positionStatePredicate) {
    List<Predicate<PositionState>> positionStatePredicates = List.of(positionStatePredicate, positionState -> true);
    return getDirectionalRayWhileConditions(board, position, direction, positionStatePredicates);
  }


  public static Stream<PositionState> getDirectionalRayWhileConditions(Board board, Position position, Direction direction, List<Predicate<PositionState>> positionStatePredicates) {
    Builder<PositionState> ray = Stream.<PositionState>builder();
    int x = position.x();
    int y = position.y();
    Delta delta = Constants.DIRECTIONDELTAS.get(direction);
    Predicate<PositionState> positionStatePredicate = positionStatePredicates.stream().reduce(positionState -> true,
        Predicate::and);
    while(isValid(board, x, y) && passesCondition(board, positionStatePredicate, x, y)){
      ray.accept(board.getPositionStateAt(x,y));
      x = x + delta.xdelta();
      y = y + delta.ydelta();
    }

    return ray.build();

  }

  public static Stream<PositionState> getDirectionalRayUntilCondition(Board board, Position position, Direction direction, Predicate<PositionState> positionStatePredicate) {
    return getDirectionalRayWhileCondition(board, position,direction, positionStatePredicate.negate());
  }

  public static Stream<PositionState> getDirectionalRayUntilAnyOfConditions(Board board, Position position, Direction direction, List<Predicate<PositionState>> positionStatePredicates) {
    Predicate<PositionState> positionStatePredicate = positionStatePredicates.stream().reduce(positionState -> false,
        Predicate::or);
    return getDirectionalRayWhileCondition(board, position,direction, positionStatePredicate.negate());
  }

  private static boolean passesCondition(Board board, Predicate<PositionState> positionStatePredicate, int x,
      int y) {
    return positionStatePredicate.test(board.getPositionStateAt(x, y));
  }

  private static boolean isValid(Board board, int x, int y) {
    return board.isValidXY(x, y);
  }

}
