package oogasalad.engine.model.logicelement;

import java.util.List;
import oogasalad.engine.model.board.Position;

public class LogicElement {

  protected int[] myParameters;

  protected LogicElement(int[] parameters) {
    myParameters = parameters;
  }

  protected int getParameter(int i) {
    return myParameters[i];
  }

  protected Position transformToRelative(Position position, Position relativePoint) {
    return new Position(position.row() + relativePoint.row(), position.column() + relativePoint.column());
  }
}
