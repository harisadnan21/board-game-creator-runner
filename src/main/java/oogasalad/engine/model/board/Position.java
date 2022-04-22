package oogasalad.engine.model.board;

import org.jooq.lambda.tuple.Tuple2;

public record Position (int row, int column) implements Comparable<Position> {

  public Position(Tuple2<Integer,Integer> tuple2) {
    this(tuple2.v1(), tuple2.v2());
  }

  public Position withNewRow(int newRow) {
    return new Position(newRow, this.column);
  }

  public Position withNewColumn(int newColumn) {
    return new Position(this.row, newColumn);
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
    if(this.row() != position.row()) {
      return this.row() - position.row();
    }
    return this.column() - position.column();
  }

}

