package oogasalad.engine.view.game;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.parser.BoardSaver;
import oogasalad.engine.view.ApplicationAlert;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.ControlPanel.GameControlPanel;
import oogasalad.engine.view.ControlPanel.SettingsControlPanel;
import oogasalad.engine.view.OptionSelect.MouseSoundSelect;
import oogasalad.engine.view.Popup.SettingsView.SettingsView;
import oogasalad.engine.view.Popup.MessageView;
import oogasalad.engine.view.setup.DirectoryOpener;

/**
 * Class that sets up the game screen, with buttons
 * @author Cynthia France, Haris Adnan
 */
public class GameView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";

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
  private String language;
  private SettingsView settings;
  private File game;

  /**
   *
   * creates the view that displays the entire game
   *
   * @param board The front end board
   * @param controller game controller
   * @param w width of window
   * @param h height of window
   * @param css css filepath
   * @param language user-specified language in which the UI is displayed in
   * @param game game folder
   */
  public GameView(BoardView board, Controller controller, double w, double h, String css, String language, File game, Consumer<Scene> setHome) {
    this.language = language;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    myBoard = board;
    myController = controller;
    myGameControl = new GameControlPanel(controller, language, this::setPause, setHome, this::saveGame, this::getScene);
    mySettingsControl = new SettingsControlPanel(language, this::setInfo, this::setSettings);
    myPlayerText = board.getText();
    settings = new SettingsView(cssFilePath, language);
    this.game = game;
    setUpRoot();
  }

  /**
   *
   * returns the scene
   *
   * @return gameView scene
   */
  private Scene getScene() {
    return myScene;
  }

  /**
   *
   * makes the Scene for this GameView
   *
   * @return the Scene for this gameView
   */
  public Scene makeScene() {
    root.setCenter(myBoard.getRoot());
    root.setLeft(myGameControl.getRoot());
    root.setRight(mySettingsControl.getRoot());
    root.setBottom(myPlayerText);
    root.setAlignment(myPlayerText, Pos.CENTER);
    myScene = new Scene(root, width, height);
    myScene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    return myScene;
  }

  /**
   *
   * CSS dropdown menu, for ViewManager use
   *
   * @return CSS dropdown menu
   */
  public CSSSelect getCssDropdown() {
    return settings.getCssDropdown();
  }

  /**
   *
   * Mouse sounds menu, for ViewManager use
   *
   * @return Mouse sounds menu
   */
  public MouseSoundSelect getSoundDropdown() {
    return settings.getSoundDropdown();
  }

  private void setUpRoot() {
    root = new BorderPane();
    root.setId("game-view-root");
  }

  private void setPause() {
      root.setEffect(new GaussianBlur());
      MessageView pauseView = new MessageView(myResources.getString("PauseMessage"),
          myResources.getString("Resume"), cssFilePath, language);
      Stage popupStage = pauseView.getStage();
      pauseView.getReturnToGame().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });

      popupStage.show();
  }

  private void setInfo(){
      root.setEffect(new GaussianBlur());
      String infoMessage = myBoard.getGameInfo();
      MessageView infoView = new MessageView(infoMessage,
          myResources.getString("Resume"), cssFilePath, language);
      Stage popupStage = infoView.getStage();
      infoView.getReturnToGame().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });
      popupStage.show();
  }

  private void setSettings(){
      root.setEffect(new GaussianBlur());
      Stage popupStage = settings.getStage();
      settings.getReturnToGame().setOnAction(event -> {
        root.setEffect(null);
        popupStage.hide();
      });
      popupStage.show();
  }

  private void saveGame() {
    Stage myStage = new Stage();
    DirectoryOpener directoryOpener = new DirectoryOpener();
    File newDirectory = directoryOpener.fileChoice(myStage);

    try {
      BoardSaver saver = new BoardSaver(game);
      saver.saveBoardConfig(myController.getGame().getBoard(), newDirectory);
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Notif"), myResources.getString("GameSaved"));
    }
    catch (IOException e) {
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("SaveError"));
    }
  }
}
