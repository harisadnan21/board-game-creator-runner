package oogasalad.engine.view;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;


import oogasalad.engine.view.dashboard.Dashboard;
import oogasalad.engine.view.dashboard.GameSelection;

import oogasalad.engine.model.parser.GameParser;


public class ViewManager {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;
  public static double BOARDX = 400;
  public static double BOARDY = 400;

  private OpeningView openingView;
  private GameView gameView;
  private Scene currScene;
  private Stage stage;

  public ViewManager(Stage s) {
    currScene = createOpeningView().makeScene();
    stage = s;
  }

  public Scene getCurrScene() {
    return currScene;
  }

  public OpeningView createOpeningView() {
    openingView = new OpeningView(WIDTH, HEIGHT);
    openingView.getPlayGame().setOnAction(e -> startGame());
    return openingView;
  }

  public GameView createGameView(BoardView board, Controller controller) {
    gameView = new GameView(board, controller, WIDTH, HEIGHT);
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
