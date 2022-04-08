package oogasalad.engine.model.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.Delta;
import org.jooq.lambda.Seq;

public class Ray {

  public static Stream<PositionState> getDirectionalRay(Board board, Position position, Direction direction) {
    return Ray.getDirectionalRayWhileCondition(board, position, direction, positionState -> true);
  }

  public static Map<Direction, Stream<PositionState>> getDirectionalRays(Board board, Position position, Collection<Direction> directions) {
    return Seq.seq(directions)
              .toMap(direction -> direction,
                     direction -> Ray.getDirectionalRayWhileCondition(board, position, direction, positionState -> true));
  }


  public static Stream<PositionState> getDirectionalRayWhileCondition(Board board, Position position, Direction direction, Predicate<PositionState> positionStatePredicate) {
    List<Predicate<PositionState>> positionStatePredicates = List.of(positionStatePredicate, positionState -> true);
    return getDirectionalRayWhileConditions(board, position, direction, positionStatePredicates);
  }


  public static Stream<PositionState> getDirectionalRayWhileConditions(Board board, Position position, Direction direction, List<Predicate<PositionState>> positionStatePredicates) {
    Builder<PositionState> ray = Stream.builder();
    int x = position.x();
    int y = position.y();
    Delta delta = Constants.DIRECTIONDELTAS.get(direction);
    Predicate<PositionState> positionStatePredicate = reducePredicates(positionStatePredicates); // Combines list of predicates into one predicate which is when they are all true
    while(isValid(board, x, y) && passesCondition(board, positionStatePredicate, x, y)){
      ray.accept(board.getPositionStateAt(x,y));
      x += delta.xdelta();
      y += delta.ydelta();
    }

    return ray.build();

  }

  private static Predicate<PositionState> reducePredicates(
      List<Predicate<PositionState>> positionStatePredicates) {
    return positionStatePredicates.stream().reduce(positionState -> true,
        Predicate::and);
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
