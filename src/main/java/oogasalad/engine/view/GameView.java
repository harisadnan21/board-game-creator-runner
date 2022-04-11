package oogasalad.engine.view;

import java.io.File;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

  private Double width;
  private Double height;
  private BoardView myBoard;
  private Controller myController;
  private GameControlPanel myGameControl;
  private SettingsControlPanel mySettingsControl;
  private Text myPlayerText;
  private BorderPane root;

  public GameView(BoardView board, Controller controller, double w, double h) {
    width = w;
    height = h;
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
    Scene scene = new Scene(root, width, height);
    scene.setFill(Color.web("#EEEEEE"));
    root.setBottom(myPlayerText);
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
