package oogasalad.engine.model.board;

import org.jooq.lambda.tuple.Tuple2;

public record Position (int i, int j) implements Comparable<Position> {

  public Position(Tuple2<Integer,Integer> tuple2) {
    this(tuple2.v1(), tuple2.v2());
  }


  /**
   * Compares positions by the following rules:
   * 1: If the positions do not have the same i value, the position with the smaller i is the "smaller" position
   * 2: If the positions do have the same j value, the position with the smaller j is the "smaller" position
   * @param position
   * @return an int representing order
   */
  @Override
  public int compareTo(Position position) {
    if(this.i() != position.i()) {
      return this.i() - position.i();
    }
    return this.j() - position.j();
  }

}

