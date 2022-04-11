package oogasalad.engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.parsing.GameParser;
import oogasalad.engine.view.BoardView;
import oogasalad.engine.view.ViewManager;

/**
 * Class that runs the engine and starts it off
 */
public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    Board board = GameParser.readInitialBoard(Constants.TIC_TAC_TOE_FILE);

    BoardView boardView = new BoardView(board.getHeight(), board.getWidth(), 350, 350);

    Controller controller = new Controller(board);

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
