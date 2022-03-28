package oogasalad.engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.view.BoardView;

public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    Controller controller = new Controller();

    BoardView board = new BoardView(3, 3);

    Group root = new Group();

    Scene scene = new Scene(root, 400, 400);

  }
}
