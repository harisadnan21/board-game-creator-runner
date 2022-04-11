package oogasalad.engine.model.board.neighbors;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Direction;
import oogasalad.engine.model.board.Position;
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
    getDirectionNeighbor[] directionNeighbors = {
        new getEastNeighbor(),
        new getNorthEastNeighbor(),
        new getNorthNeighbor(),
        new getNorthWestNeighbor(),
        new getSouthEastNeighbor(),
        new getSouthNeighbor(),
        new getSouthWestNeighbor(),
        new getWestNeighbor()
    };
    List<Position> positions = new ArrayList<>();
    for(getDirectionNeighbor directionNeighbor: directionNeighbors) {
      Position neighbor = directionNeighbor.getNeighbor(center, board).get();
      positions.add(neighbor);
    }
    Seq.range(0,8).forEach(index1 -> Seq.range(index1+1,8).forEach(index2 -> Assertions.assertNotEquals(positions.get(index1), positions.get(index2)))); //TODO: cleanup, maybe use scan?
  }

  @Test
  void getDirection() {
  }
}