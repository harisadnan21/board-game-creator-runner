package oogasalad.engine.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class BoardView {

  private Pane[][] myGrid;

  public BoardView(int width, int height) {

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        myGrid[i][j] = new StackPane();
      }
    }
  }
}
