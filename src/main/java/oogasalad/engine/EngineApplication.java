package oogasalad.engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.view.BoardView;
import oogasalad.engine.view.ViewManager;

public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {


    BoardView board = new BoardView(8, 8, 350, 350);

    Controller controller = new Controller(board, 8, 8);

    board.addController(controller);
    Group root = new Group();
    root.getChildren().add(board.getRoot());

    ViewManager manager = new ViewManager();

    Scene scene = manager.createGameView(board, controller).makeScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }
}
