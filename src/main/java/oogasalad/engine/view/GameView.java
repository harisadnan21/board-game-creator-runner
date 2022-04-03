package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import oogasalad.engine.controller.Controller;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;
  GameControlPanel myGameControl;
  SettingsControlPanel mySettingsControl;

  BorderPane root;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    myGameControl = new GameControlPanel();
    mySettingsControl = new SettingsControlPanel();
    setUpRoot();
    board.addController(myController);
  }

  public Scene makeScene() {
    root.setCenter(myBoard.getRoot());
    root.setLeft(myGameControl.getRoot());
    root.setRight(mySettingsControl.getRoot());
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    scene.setFill(Color.web("#EEEEEE"));
    return scene;
  }

  private void setUpRoot() {
    root = new BorderPane();
    root.setPadding(new Insets(10, 10, 10, 10));
  }
}
