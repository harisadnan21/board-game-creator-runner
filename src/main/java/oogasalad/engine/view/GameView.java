package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import oogasalad.engine.Controller;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;

  GridPane root;
  Text updateText;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    updateText = myBoard.getText();
    setUpGrid();
    board.addController(myController);
  }

  public Scene makeScene() {
    root.add(myBoard.getRoot(), 0, 0);
    root.add(updateText, 1, 0);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    return scene;
  }

//  public void updateText() {
//    updateText.updateText(myBoard.getChange().getKey(), myBoard.getChange().getValue());
//  }


  private void setUpGrid() {
    root = new GridPane();
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(10, 10, 10, 10));
  }
}
