package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.engine.controller.Controller;

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
    setPause();
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

  private void setPause() {
    myGameControl.getPause().setOnAction(e -> {
      root.setEffect(new GaussianBlur());

      VBox pauseRoot = new VBox(5);
      Text text = new Text("paused");
      text.setFont(Font.font("Montserrat", 20));
      text.setFill(Color.web("72E8E1"));
      pauseRoot.getChildren().add(text);
      pauseRoot.setStyle("-fx-background-color: rgba(70, 70, 70, 0.8);");
      pauseRoot.setAlignment(Pos.CENTER);
      pauseRoot.setPadding(new Insets(20));

      Button resume = new Button("Resume");
      pauseRoot.getChildren().add(resume);

      Stage popupStage = new Stage(StageStyle.TRANSPARENT);
      popupStage.initModality(Modality.APPLICATION_MODAL);
      Scene pauseScene = new Scene(pauseRoot);
      pauseScene.setFill(Color.web("464646"));
      popupStage.setOpacity(0.8);
      popupStage.setScene(pauseScene);


      resume.setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });

      popupStage.show();
    });
  }
}
