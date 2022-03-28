package oogasalad.engine.view;

import java.util.EventListener;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import oogasalad.engine.Controller;

public class BoardView implements PropertyChangeListener {


  private Controller myController;

  private Pane[][] myGrid;

  public BoardView(int rows, int columns, double width, double height) {

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        myGrid[i][j] = new StackPane();
        Rectangle rect = new Rectangle(width / columns, height / rows, Color.GREEN);
        rect.applyCss();
        //myGrid[i][j];


        int finalI = i;
        int finalJ = j;
        myGrid[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {cellClicked(e, finalI, finalJ);});
      }
    }
  }

  public void cellClicked(MouseEvent e, int i, int j) {
    myController.click(i, j);
  }
}
