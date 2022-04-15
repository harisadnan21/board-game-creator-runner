package oogasalad.engine.view;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.GameSelection;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.setup.parsing.GameParser;

public class ViewManager {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  private OpeningView openingView;
  private GameView gameView;
  private Scene currScene;
  private Stage stage;

  public ViewManager(Stage s) {
    //currScene = createOpeningView().makeScene();
    currScene = new Scene(new GameSelection(), WIDTH, HEIGHT);
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
      Board board = GameParser.readInitialBoard(openingView.getFileObject());
      BoardView boardView = new BoardView(board.getHeight(), board.getWidth(), 350, 350);
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
