package oogasalad.engine.model.board;

public record Position (int i, int j) implements Comparable<Position> {

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
