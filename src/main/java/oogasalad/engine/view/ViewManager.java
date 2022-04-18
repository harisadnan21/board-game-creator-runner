package oogasalad.engine.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.text.html.CSS;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;


import oogasalad.engine.view.dashboard.Dashboard;
import oogasalad.engine.view.dashboard.GameSelection;

import oogasalad.engine.model.parser.GameParser;


public class ViewManager {
  private FileInputStream fis;
  public static double WIDTH;
  public static double HEIGHT;
  public static double BOARDX;
  public static double BOARDY ;
  public static String CSS_RESOURCE = "/css/";

  private OpeningView openingView;
  private GameView gameView;
  private Scene currScene;
  private Stage stage;
  private String cssFilepath;

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
    openingView.getPlayGame().setOnAction(e -> startGame());
    return openingView;
  }

  public GameView createGameView(BoardView board, Controller controller) {
    gameView = new GameView(board, controller, WIDTH, HEIGHT, cssFilepath);
    return gameView;
  }

  private void startGame() {
    try {
      GameParser parser = new GameParser(openingView.getFileChoice());
      Board board = parser.parseBoard();
      BoardView boardView = new BoardView(board.getHeight(), board.getWidth(), BOARDX, BOARDY);
      Controller controller = new Controller(board);
      boardView.addController(controller);
      currScene = createGameView(boardView, controller).makeScene();
      updateStage();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void updateStage() {
    stage.setScene(currScene);
    stage.show();
  }

}
