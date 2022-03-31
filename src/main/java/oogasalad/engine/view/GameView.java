package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import oogasalad.engine.Controller;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;

  GridPane root;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    setUpGrid();
    board.addController(myController);
  }

  public Scene makeScene() {
    root.add(myBoard.getRoot(), 1, 1);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    return scene;
  }

  private void setUpGrid() {
    root = new GridPane();
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(0, 10, 0, 10));
  }
}
