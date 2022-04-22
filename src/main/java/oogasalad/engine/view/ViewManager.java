package oogasalad.engine.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;


import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.game.GameView;
import oogasalad.engine.view.setup.PlayerModeView;
import oogasalad.engine.view.setup.dashboard.Dashboard;
import oogasalad.engine.view.setup.dashboard.GameIcon;

import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.setup.OpeningView;


public class ViewManager {

  public static double WIDTH = 600;
  public static double HEIGHT = 400;
  public static double GAME_SELECTION_WIDTH = 1000;
  public static double GAME_SELECTION_HEIGHT = 600;

  private FileInputStream fis;

  public static double BOARDX;
  public static double BOARDY ;
  public static String CSS_RESOURCE = "/css/";


  private OpeningView openingView;
  private GameView gameView;
  private Scene currScene;
  private Stage stage;
  private String cssFilepath;
  private File currGame;


  public ViewManager(Stage s) throws IOException {
    stage = s;
    cssFilepath = CSS_RESOURCE + "light.css";
    fis = new FileInputStream("data/Properties/ViewManagerProperties.properties");
    Properties prop = new Properties();
    prop.load(fis);

    WIDTH = Double.parseDouble(prop.getProperty("WIDTH"));
    HEIGHT = Double.parseDouble(prop.getProperty("HEIGHT"));
    BOARDX = Double.parseDouble(prop.getProperty("BOARDX"));
    BOARDY = Double.parseDouble(prop.getProperty("BOARDY"));
    //currScene = createGameView(new BoardView(2, 2, 200, 200), new Controller(new Board(3, 3))).makeScene();
    currScene = createOpeningView().makeScene();

  }

  public Scene getCurrScene() {
    return currScene;
  }

  public OpeningView createOpeningView() {
    openingView = new OpeningView(WIDTH, HEIGHT, cssFilepath);
    //openingView.getPlayGame().setOnAction(e -> startGame(openingView.getFileChoice()));
    currGame = openingView.getFileChoice();
    openingView.getContSel().setOnAction(e -> selectMode(openingView.getFileChoice()));
    openingView.getDashboard().setOnAction(e -> showGames());
    return openingView;
  }

  public GameView createGameView(BoardView board, Controller controller) {
    gameView = new GameView(board, controller, WIDTH, HEIGHT, cssFilepath);
    gameView.getHome().setOnAction(e -> goHome());
    return gameView;
  }

//  private void showGames(){
//    currScene = new Scene(new Dashboard(this::startGame), GAME_SELECTION_WIDTH, GAME_SELECTION_HEIGHT);
//    currScene.getStylesheets().add(getClass().getResource(cssFilepath).toExternalForm());
//    updateStage();
//  }

  private void showGames() {
    currScene = new Scene(new Dashboard(this::selectMode), GAME_SELECTION_WIDTH, GAME_SELECTION_HEIGHT);
    currScene.getStylesheets().add(getClass().getResource(cssFilepath).toExternalForm());
    updateStage();
  }

  private void selectMode(File game) {
    PlayerModeView pmv = new PlayerModeView(WIDTH, HEIGHT, cssFilepath, game, this::startGame);
    currScene = pmv.makeScene();
    currScene.getStylesheets().add(getClass().getResource(cssFilepath).toExternalForm());
    pmv.getOnePlayer().setOnAction(e -> startGame(game));
    updateStage();
  }

  private void startGame(File game) {
    try {
      Stage newStage= new Stage();
      newStage.setTitle(game.getName());
      GameParser parser;
      try {
        parser = new GameParser(
            Objects.requireNonNull(game.listFiles(GameIcon.getConfigFile))[0]);
      }
      catch (NullPointerException e) {
        parser = new GameParser(game);
      }
      Board board = parser.parseBoard();
      BoardView boardView = new BoardView(game, board.getHeight(), board.getWidth(), BOARDX, BOARDY, cssFilepath);
      Controller controller = new Controller(board, parser);
      boardView.addController(controller);
      newStage.setScene(createGameView(boardView, controller).makeScene());
      newStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void goHome() {
    currScene = createOpeningView().makeScene();
    updateStage();
  }

  private void updateStage() {
    stage.setScene(currScene);
    stage.show();
  }

}
