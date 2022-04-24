package oogasalad.engine.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oogasalad.engine.cheat_codes.CheatCode;
import oogasalad.engine.cheat_codes.RemoveRandomPlayer0Piece;
import oogasalad.engine.cheat_codes.RemoveRandomPlayer1Piece;
import oogasalad.engine.cheat_codes.ShuffleBoard;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;


import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.game.GameView;
import oogasalad.engine.view.setup.SelectionView.AISelectView;
import oogasalad.engine.view.setup.SelectionView.PlayerModeView;
import oogasalad.engine.view.setup.dashboard.Dashboard;
import oogasalad.engine.view.setup.dashboard.GameIcon;

import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.setup.OpeningView;


public class ViewManager {
  public static final Map<KeyCode, Object> cheatCodes = Map.of(
      KeyCode.O, new RemoveRandomPlayer1Piece(),
      KeyCode.Z, new RemoveRandomPlayer0Piece(),
      KeyCode.S, new ShuffleBoard());
  public static double WIDTH = 600;
  public static double HEIGHT = 400;
  public static double GAME_SELECTION_WIDTH = 1000;
  public static double GAME_SELECTION_HEIGHT = 600;
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private FileInputStream fis;

  public static double BOARDX;
  public static double BOARDY ;
  public static String CSS_RESOURCE = "/css/";
  public static String DEFAULT_CSS = "light";
  public static String CSS_EXTENSION = ".css";
  public static String DEFAULT_LANGUAGE = "English";


  private OpeningView openingView;
  private Scene currScene;
  private Stage stage;
  private String cssFilepath;
  private File currGame;
  private List<Stage> gameStages = new ArrayList<>();
  private Controller controller;
  private List<Scene> gameScenes = new ArrayList<>();
  private String language;
  private ResourceBundle myResources;
  private MouseSound sound;

  public ViewManager(Stage s) throws IOException {
    language = DEFAULT_LANGUAGE;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    stage = s;
    cssFilepath = CSS_RESOURCE + DEFAULT_CSS + CSS_EXTENSION;
    fis = new FileInputStream("data/Properties/ViewManagerProperties.properties");
    Properties prop = new Properties();
    prop.load(fis);

    WIDTH = Double.parseDouble(prop.getProperty("WIDTH"));
    HEIGHT = Double.parseDouble(prop.getProperty("HEIGHT"));
    BOARDX = Double.parseDouble(prop.getProperty("BOARDX"));
    BOARDY = Double.parseDouble(prop.getProperty("BOARDY"));
    currScene = createOpeningView().makeScene();
    gameScenes.add(currScene);

    sound = new MouseSound(language);
    stage.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> sound.playSound());
  }



  public Scene getCurrScene() {
    return currScene;
  }

  public OpeningView createOpeningView() {
    openingView = new OpeningView(WIDTH, HEIGHT, cssFilepath, language);
    currGame = openingView.getFileChoice();
    openingView.getLanguageSelect().setOnAction(e -> setLanguage(openingView.getLanguageSelect().getLanguage()));
    openingView.getContSel().setOnAction(e -> selectMode(openingView.getFileChoice()));
    openingView.getDashboard().setOnAction(e -> showGames());
    return openingView;
  }

  public GameView createGameView(BoardView board, Controller controller, File game) {
    GameView gameView = new GameView(board, controller, WIDTH, HEIGHT, cssFilepath, language, game);
    gameView.getHome().setOnAction(e -> goHome(gameView.getScene()));
    return gameView;
  }

  private void setLanguage(String currLanguage) {
    language = currLanguage;
    System.out.println(language);
    currScene = createOpeningView().makeScene();
    stage.setScene(currScene);
  }

  private void showGames() {
    currScene = new Scene(new Dashboard(this::selectMode), GAME_SELECTION_WIDTH, GAME_SELECTION_HEIGHT);
    currScene.getStylesheets().add(getClass().getResource(cssFilepath).toExternalForm());
    updateStage();
  }

  private void selectMode(File game) {
    Stage newStage = new Stage();
    newStage.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> sound.playSound());
    newStage.setTitle(game.getName());
    PlayerModeView pmv = new PlayerModeView(WIDTH, HEIGHT, cssFilepath, language);
    Scene newScene = pmv.makeScene();
    newStage.setScene(newScene);
    newScene.getStylesheets().add(getClass().getResource(cssFilepath).toExternalForm());
    gameScenes.add(newScene);
    pmv.getOnePlayer().setOnAction(e -> selectAI(newStage));
    pmv.getTwoPlayer().setOnAction(e -> startGame(game, newStage));
    newStage.show();
  }

  private void selectAI(Stage newStage) {
    AISelectView aiView = new AISelectView(WIDTH, HEIGHT, cssFilepath, language);
    newStage.setScene(aiView.makeScene());
  }

  private void startGame(File game, Stage newStage) {
    try {
      GameParser parser;
      try {
        parser = new GameParser(
            Objects.requireNonNull(game.listFiles(GameIcon.getConfigFile))[0]);
      }
      catch (NullPointerException e) {
        parser = new GameParser(game);
      }
      Board board = parser.parseBoard();
      controller = new Controller(board, parser);
      BoardView boardView = new BoardView(game, board.getHeight(), board.getWidth(), BOARDX, BOARDY, cssFilepath, language);
      boardView.addController(controller);

      GameView gameView = createGameView(boardView, controller, game);
      gameView.getCssDropdown().setOnAction(e -> updateSceneCSS(gameView.getCssDropdown().getCSS()));
      gameView.getSoundDropdown().setOnAction(e -> sound.setSound(gameView.getSoundDropdown().getSound()));
      Scene newScene = gameView.makeScene();
      addKeyPress(newScene);
      newStage.setScene(newScene);
      gameScenes.add(newScene);
      gameStages.add(newStage);
    }
    catch (IOException e) {
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("ExceptionThrown"));
    }
  }


  //Checks for keys being pressed on game scene
  private void addKeyPress(Scene scene){
    scene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
  }


  private void handleKeyPressed(KeyCode code) {
    if(cheatCodes.containsKey(code)) {
      CheatCode cheatCode = (CheatCode) cheatCodes.get(code);
      Board board = cheatCode.accept(controller.getGame().getBoard());
      controller.setBoard(board);
    }
  }

  private void goHome(Scene scene) {
    currScene = createOpeningView().makeScene();
    closeStage(findClosedStage(scene));
    updateStage();
  }

  private void closeStage(Stage stage) {
    gameStages.remove(stage);
    stage.close();
  }

  private Stage findClosedStage(Scene scene) {
    for (Stage stage : gameStages) {
      if (stage.getScene().equals(scene)) {
        return stage;
      }
    }
    return new Stage();
  }

  private void updateStage() {
    stage.setScene(currScene);
    stage.show();
  }

  private void updateSceneCSS(String style) {
    System.out.println(cssFilepath);
    String oldCss = cssFilepath;
    cssFilepath = CSS_RESOURCE + style + CSS_EXTENSION;
    currScene.getStylesheets().remove(oldCss);
    currScene.getStylesheets().add(cssFilepath);
    System.out.println(gameScenes.contains(currScene));
    for (Scene s : gameScenes) {
      s.getStylesheets().remove(oldCss);
      s.getStylesheets().add(cssFilepath);
    }
  }
}
