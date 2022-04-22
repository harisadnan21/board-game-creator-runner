package oogasalad.engine.view.game;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.view.ControlPanel.GameControlPanel;
import oogasalad.engine.view.ControlPanel.SettingsControlPanel;

/**
 * Class that sets up the game screen, with buttons
 * @author Cynthia France, Haris Adnan
 */
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
  private Scene myScene;

  public GameView(BoardView board, Controller controller, double w, double h, String css) {
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    myBoard = board;
    myController = controller;
    myGameControl = new GameControlPanel(controller, board::updateBoard);
    mySettingsControl = new SettingsControlPanel();
    myPlayerText = board.getText();
    setUpRoot();
    board.addController(myController);
  }

  public Scene getScene() {
    return myScene;
  }

  public Scene makeScene() {
    root.setCenter(myBoard.getRoot());
    root.setLeft(myGameControl.getRoot());
    setPause();
    setInfo();
    setSettings();
    root.setRight(mySettingsControl.getRoot());
    root.setBottom(myPlayerText);
    root.setAlignment(myPlayerText, Pos.CENTER);
    myScene = new Scene(root, width, height);
    myScene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    return myScene;
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
      MessageView pauseView = new MessageView(myResources.getString("PauseMessage"),
          myResources.getString("Resume"), cssFilePath);
      Stage popupStage = pauseView.getStage();
      pauseView.getButton().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });

      popupStage.show();
    });
  }

  private void setInfo(){
    mySettingsControl.getInfoButton().setOnAction(e -> {
      root.setEffect(new GaussianBlur());
      String infoMessage = myBoard.getGameInfo();
      MessageView infoView = new MessageView(infoMessage,
          myResources.getString("Resume"), cssFilePath);
      Stage popupStage = infoView.getStage();
      infoView.getButton().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });
      popupStage.show();
    });
  }

  private void setSettings(){
    mySettingsControl.getSettingsButton().setOnAction(e -> {
      root.setEffect(new GaussianBlur());
      MessageView pauseView = new MessageView(
          myResources.getString("SettingsDisplay"),
          myResources.getString("Continue"), cssFilePath);
      Stage popupStage = pauseView.getStage();
      pauseView.getButton().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });
      popupStage.show();
    });
  }

}
