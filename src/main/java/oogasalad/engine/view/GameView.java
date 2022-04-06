package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.Game;

public class GameView {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  BoardView myBoard;
  Controller myController;
  GameControlPanel myGameControl;
  SettingsControlPanel mySettingsControl;
  Text myPlayerText;

  BorderPane root;

  public GameView(BoardView board, Controller controller) {
    myBoard = board;
    myController = controller;
    myGameControl = new GameControlPanel();
    mySettingsControl = new SettingsControlPanel();
    myPlayerText = board.getText();
    setUpRoot();
    board.addController(myController);
  }

  public Scene makeScene() {
    root.setCenter(myBoard.getRoot());
    root.setLeft(myGameControl.getRoot());
    root.setRight(mySettingsControl.getRoot());
    root.setBottom(myPlayerText);
    root.setAlignment(myPlayerText, Pos.CENTER);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    scene.setFill(Color.web("#EEEEEE"));
    return scene;
  }

  private void setUpRoot() {
    root = new BorderPane();
    root.setPadding(new Insets(10, 10, 10, 10));
  }
}
