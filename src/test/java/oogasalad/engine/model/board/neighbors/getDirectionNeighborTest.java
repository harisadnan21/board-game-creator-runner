package oogasalad.engine.model.board.neighbors;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.components.Position;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class getDirectionNeighborTest {

  @Test
  void getNeighbor() {

  }

  @Test
  void differentNeighbors() {
    Board board = new Board(9,9);
    Position center = new Position(5,5);
    GetDirectionNeighbor[] directionNeighbors = {
        new GetEastNeighbor(),
        new GetNorthEastNeighbor(),
        new GetNorthNeighbor(),
        new GetNorthWestNeighbor(),
        new GetSouthEastNeighbor(),
        new GetSouthNeighbor(),
        new GetSouthWestNeighbor(),
        new GetWestNeighbor()
    };
    List<Position> positions = new ArrayList<>();
    for(GetDirectionNeighbor directionNeighbor: directionNeighbors) {
      Position neighbor = directionNeighbor.getNeighbor(center, board).get();
      positions.add(neighbor);
    }
    System.out.println(positions);
    Seq.range(0,8).forEach(index1 -> Seq.range(index1+1,8).forEach(index2 -> Assertions.assertNotEquals(positions.get(index1), positions.get(index2)))); //TODO: cleanup, maybe use scan?
  }

  @Test
  void getDirection() {
  }
}