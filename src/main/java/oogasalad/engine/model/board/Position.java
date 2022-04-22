package oogasalad.engine.model.board;

public record Position (int row, int column) implements Comparable<Position> {

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

  /**
   * Returns a new position which is a sum of this position and position
   * @param position
   * @return
   */
  public Position add(Position position) {
    return new Position(this.row() + position.row(), this.column() + position.column);
  }
}

