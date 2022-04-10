package oogasalad.engine.model.board;

import org.jooq.lambda.tuple.Tuple2;

public record Position (int i, int j) implements Comparable<Position> {
  public Position(Tuple2<Integer,Integer> tuple2) {
    this(tuple2.v1(), tuple2.v2());
  }

  @Override
  public int compareTo(Position position) {
    if(this.i() != position.i()) {
      return this.i() - position.i();
    }
    return this.j() - position.j();
  }

  @Override
  public boolean equals(Object position) {
    if (position.getClass() != Position.class) {
      return false;
    }
    else {
      return compareTo((Position) position) == 0;
    }
  }

  public int getI() {
    return i();
  }

  public int getJ() {
    return j();
  }

}
