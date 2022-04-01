package oogasalad.engine.model.board;

import org.jooq.lambda.tuple.Tuple2;

public record Position (int x, int y) implements Comparable<Position> {

  public Position(Tuple2<Integer,Integer> tuple2) {
    this(tuple2.v1(), tuple2.v2());
  }

  @Override
  public int compareTo(Position position) {
    if(this.x() != position.x()) {
      return this.x() - position.x();
    }
    return this.y() - position.y();
  }


}

