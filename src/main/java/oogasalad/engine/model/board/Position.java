package oogasalad.engine.model.board;

import org.jooq.lambda.tuple.Tuple2;

public record Position (int x, int y) implements Comparable<Position> {

  public Position(Tuple2<Integer,Integer> tuple2) {
    this(tuple2.v1(), tuple2.v2());
  }

  /**
   * Compares positions by the following rules:
   * 1: If the positions do not have the same x value, the position with the smaller x is the "smaller" position
   * 2: If the positions do have the same x value, the position with the smaller y is the "smaller" position
   * @param position
   * @return an int representing order
   */
  @Override
  public int compareTo(Position position) {
    if(this.x() != position.x()) {
      return this.x() - position.x();
    }
    return this.y() - position.y();
  }

  public int i(){
    return y;
  }

  public int j(){
    return x;
  }


}

