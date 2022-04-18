package oogasalad.engine.view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.view.ControlPanel.GameControlPanel;
import oogasalad.engine.view.ControlPanel.SettingsControlPanel;

public class GameView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private Double width;
  private Double height;
  private BoardView myBoard;
  private Controller myController;
  private GameControlPanel myGameControl;
  private SettingsControlPanel mySettingsControl;
  private Text myPlayerText;
  private BorderPane root;
  private ResourceBundle myResources;
  private String cssFilePath;

  public GameView(BoardView board, Controller controller, double w, double h, String css) {
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    myBoard = board;
    myController = controller;
    myGameControl = new GameControlPanel(controller);
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
    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    return scene;
  }

  public Button getHome() {
    return myGameControl.getHome();
  }

  private void setUpRoot() {
    root = new BorderPane();
    root.setId("game-view-root");
  }

  private void setPause() {
    myGameControl.getPause().setOnAction(e -> {
      root.setEffect(new GaussianBlur());

      VBox pauseRoot = new VBox();
      pauseRoot.setId("pause-root");
      Text text = new Text(myResources.getString("PauseMessage"));
      text.setId("pause-message-text");
      pauseRoot.getChildren().add(text);

      Button resume = new Button(myResources.getString("Resume"));
      resume.setId("resume-button");
      pauseRoot.getChildren().add(resume);

      Stage popupStage = new Stage(StageStyle.TRANSPARENT);
      popupStage.initModality(Modality.APPLICATION_MODAL);
      Scene pauseScene = new Scene(pauseRoot);
      pauseScene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
      popupStage.setScene(pauseScene);

      resume.setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });

      popupStage.show();
    });
  }

  //TODO: add methods that give functionality to back, forward, setting and home functions
}
