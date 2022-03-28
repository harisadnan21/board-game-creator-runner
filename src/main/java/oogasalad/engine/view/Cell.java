package oogasalad.engine.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Cell {

  private Shape myShape;
  private Pane myPane;

  public Cell() {
  }

  public Node getNode() {
    return myPane;
  }
}
