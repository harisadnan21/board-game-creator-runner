package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import oogasalad.engine.controller.Controller;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;

  GridPane root;
  Text updateText;
  Inventory pInventory;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    updateText = myBoard.getText();
    pInventory = new Inventory();
    setUpGrid();
    board.addController(myController);
  }

  public Scene makeScene() {
    root.add(myBoard.getRoot(), 0, 0, 4, 4);
    root.add(updateText, 5, 2);
    root.add(pInventory.getRoot(), 5, 3, 6, 4);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    return scene;
  }

  private void setUpGrid() {
    root = new GridPane();
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(10, 10, 10, 10));
    //root.setGridLinesVisible(true);
  }
}
