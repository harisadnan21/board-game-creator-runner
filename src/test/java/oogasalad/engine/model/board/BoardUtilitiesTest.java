package oogasalad.engine.model.board;

import static oogasalad.engine.model.board.utilities.BoardUtilities.getNeighbor;
import static org.jooq.lambda.Seq.range;
import static org.jooq.lambda.Seq.zip;

import java.util.stream.Stream;
import oogasalad.engine.model.board.utilities.Direction;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardUtilitiesTest {

  @Test
  void differentNeighbors() {
    Board board = new Board(9,9);
    Position center = new Position(5,5);

    var positions = Stream.builder();

    for(Direction direction: Direction.values()) {
      Position neighbor = getNeighbor(center, board, direction).get();
      positions.accept(neighbor);
    }

    var pos = positions.build();

    var allPairs = zip(range(0,8), pos).innerSelfJoin((pair1, pair2) -> pair1.v1 < pair2.v1).map(Tuple2::v2);
    allPairs.forEach((pair) -> Assertions.assertNotEquals(pair.v1, pair.v2));
  }

}
