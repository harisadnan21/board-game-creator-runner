package oogasalad.engine;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.BoardView;
import oogasalad.engine.view.ViewManager;

/**
 * Class that runs the engine and starts it off
 */
public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    // TODO: Replace this with some way to pick the configuration directory
    GameParser parser = new GameParser(new File("data/checkers/config.json"));
    Board board = parser.parseBoard();
    BoardView boardView = new BoardView(board.getHeight(), board.getWidth(), 350, 350);
    Controller controller = new Controller(board, 3, 3);

    boardView.addController(controller);
    Group root = new Group();
    root.getChildren().add(boardView.getRoot());

    ViewManager manager = new ViewManager();

    Scene scene = manager.createGameView(boardView, controller).makeScene();
    scene.getStylesheets().add(getClass().getResource("/css/engine.css").toExternalForm());

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }
}
