package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import oogasalad.engine.controller.Controller;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;

  //GridPane root;
  BorderPane myRoot;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    myRoot = new BorderPane();
    //setUpGrid();
    board.addController(myController);
  }

  public Scene makeScene() {
    myRoot.setCenter(myBoard.getRoot());
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT);
    scene.setFill(Color.web("#EEEEEE"));
    return scene;
  }

//  private void setUpGrid() {
//    root = new GridPane();
//    root.setHgap(10);
//    root.setVgap(10);
//    root.setPadding(new Insets(10, 10, 10, 10));
//  }
}
