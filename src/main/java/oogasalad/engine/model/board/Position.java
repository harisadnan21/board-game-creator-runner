package oogasalad.engine.model.board;

public class Position {

  private int myI;
  private int myJ;

  public Position(int i, int j) {
    myI = i;
    myJ = j;
  }

  public int getI() {
    return myI;
  }

  public int getJ() {
    return myJ;
  }
}
