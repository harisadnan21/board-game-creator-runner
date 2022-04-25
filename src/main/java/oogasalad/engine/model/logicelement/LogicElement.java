package oogasalad.engine.model.logicelement;

import oogasalad.engine.model.board.cells.Position;

public class LogicElement {

  private int[] myParameters;

  protected LogicElement(int[] parameters) {
    myParameters = parameters;
  }

  protected int getParameter(int i) {
    return myParameters[i];
  }

  protected Position transformToRelative(Position position, Position relativePoint) {
    return position.add(relativePoint);
  }
}
