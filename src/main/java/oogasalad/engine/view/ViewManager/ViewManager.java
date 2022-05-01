package oogasalad.engine.view.ViewManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oogasalad.engine.cheat_codes.CheatCode;
import oogasalad.engine.cheat_codes.IncrementPlayer;
import oogasalad.engine.cheat_codes.CopyRandomPiece;
import oogasalad.engine.cheat_codes.Player1Turn;
import oogasalad.engine.cheat_codes.Player2Turn;
import oogasalad.engine.cheat_codes.PlayerOneWins;
import oogasalad.engine.cheat_codes.PlayerTwoWins;
import oogasalad.engine.cheat_codes.RemovePlayer0Piece;
import oogasalad.engine.cheat_codes.RemovePlayer1Piece;
import oogasalad.engine.cheat_codes.Reset;
import oogasalad.engine.cheat_codes.ShuffleBoard;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;


import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.view.ApplicationAlert;
import oogasalad.engine.view.MouseSound;
import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.game.GameView;
import oogasalad.engine.view.setup.SelectionView.AISelectView;
import oogasalad.engine.view.setup.SelectionView.PlayerModeView;
import oogasalad.engine.view.setup.dashboard.Dashboard;
import oogasalad.engine.view.setup.dashboard.GameIcon;

import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.setup.OpeningView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Manages all the views of the engine
 *
 * @author Cynthia France, Robert Cranston, Haris Adnan
 */
public class ViewManager {
  public static final Map<KeyCode, Object> cheatCodes = Map.of(
      KeyCode.O, new RemovePlayer1Piece(),
      KeyCode.Z, new RemovePlayer0Piece(),
      KeyCode.S, new ShuffleBoard(),
      KeyCode.DIGIT1, new PlayerOneWins(),
      KeyCode.DIGIT2, new PlayerTwoWins(),
      KeyCode.R, new Reset(),
      KeyCode.L, new Player1Turn(),
      KeyCode.K, new Player2Turn(),
      KeyCode.I, new IncrementPlayer(),
      KeyCode.P, new CopyRandomPiece());

  public static double WIDTH;
  public static double HEIGHT;
  public static double GAME_SELECTION_WIDTH = 1000;
  public static double GAME_SELECTION_HEIGHT = 600;

  private static final Logger LOG = LogManager.getLogger(ViewManager.class);
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";

  private FileInputStream fis;

  public static double BOARDX;
  public static double BOARDY ;
  public static String CSS_RESOURCE = "/engine-view/css/";
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

  /**
   *
   * creates the opening view and starts mouse sounds
   *
   * @param s the stage
   * @throws IOException
   */
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

  /**
   *
   * returns the current scene
   *
   * @return the current scene
   */
  public Scene getCurrScene() {
    return currScene;
  }

  /**
   *
   * creates the opening view
   *
   * @return the opening view
   */
  public OpeningView createOpeningView() {
    openingView = new OpeningView(WIDTH, HEIGHT, cssFilepath, language);
    currGame = openingView.getFileChoice();
    openingView.getLanguageSelect().setOnAction(e -> setLanguage(openingView.getLanguageSelect().getElement()));
    openingView.getContSel().setOnAction(e -> selectMode(openingView.getFileChoice()));
    openingView.getDashboard().setOnAction(e -> showGames());
    return openingView;
  }


  /**
   *
   * creates the game view
   *
   * @return the game view
   */
  public GameView createGameView(BoardView board, Controller controller, File game) {
    GameView gameView = new GameView(board, controller, WIDTH, HEIGHT, cssFilepath, language, game, this::goHome);
    return gameView;
  }

  private void setLanguage(String currLanguage) {
    language = currLanguage;
    LOG.debug(language);
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
    pmv.getOnePlayer().setOnAction(e -> selectAI(game, newStage));
    pmv.getTwoPlayer().setOnAction(e -> startGame(game, newStage, new String[]{"multiPlayer", null}));
    try {
      int numPlayers = getParser(game).readNumberOfPlayers();
      pmv.getOnePlayer().setDisable(numPlayers > 2);
      if (numPlayers == 1) {
        pmv.getTwoPlayer().setDisable(true);
        pmv.getOnePlayer().setOnAction(e -> startGame(game, newStage, new String[]{"singlePlayer", null}));
      }
    } catch (FileNotFoundException e) {
      // don't do anything if parser throws error here (it will throw an error later)
    }

    newStage.show();
  }

  private GameParser getParser(File game) {
    return new GameParser(
        Objects.requireNonNull(game.listFiles(GameIcon.getConfigFile))[0]);
  }

  private void selectAI(File game, Stage newStage) {
    AISelectView aiView = new AISelectView(WIDTH, HEIGHT, cssFilepath, language, game, newStage, this::startGame);
    newStage.setScene(aiView.makeScene());
  }

  private void startGame(File game, Stage newStage, String[] players) {
    try {
      GameParser parser;
      try {
        parser = getParser(game);
      }
      catch (NullPointerException e) {
        LOG.info(e);
        parser = new GameParser(game);
      }
      Board board = parser.parseBoard();
      controller = new Controller();
      BoardView boardView = new BoardView(controller, game, board, BOARDX, BOARDY, cssFilepath, language);

      Consumer<Set<Position>> setMarkersLambda = boardView::setValidMarkers;
      controller.startEngine(players, parser, setMarkersLambda, boardView::endGame);
      controller.getGame().addListener(boardView);

      GameView gameView = createGameView(boardView, controller, game);
      gameView.getCssDropdown().setOnAction(e -> updateSceneCSS(gameView.getCssDropdown().getElement()));
      gameView.getSoundDropdown().setOnAction(e -> sound.setSound(gameView.getSoundDropdown().getElement()));
      Scene newScene = gameView.makeScene();
      addKeyPress(newScene);
      newStage.setScene(newScene);
      gameScenes.add(newScene);
      gameStages.add(newStage);
    }
    catch (IOException e) {
      LOG.error(e);
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
      Board board = cheatCode.accept(controller.getGame().getBoard(), controller);
      controller.setBoard(board);
      controller.getEngine().pingActivePlayer();
      controller.getEngine().checkWin();

    }
  }

  private void goHome(Scene scene) {
    currScene = createOpeningView().makeScene();
    gameStages = closeStage(scene);
    updateStage();
  }

  private List<Stage> closeStage(Scene scene) {
    StageCloser stageCloser = new StageCloser();
    return stageCloser.closeStage(List.copyOf(gameStages), scene);
  }

  private void updateStage() {
    stage.setScene(currScene);
    stage.show();
  }

  private void updateSceneCSS(String style) {
    LOG.debug(cssFilepath);
    String oldCss = cssFilepath;
    cssFilepath = CSS_RESOURCE + style + CSS_EXTENSION;
    currScene.getStylesheets().remove(oldCss);
    currScene.getStylesheets().add(cssFilepath);
    LOG.debug(gameScenes.contains(currScene));
    for (Scene s : gameScenes) {
      s.getStylesheets().remove(oldCss);
      s.getStylesheets().add(cssFilepath);
    }
  }
}
