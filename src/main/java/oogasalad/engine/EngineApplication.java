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

    BoardView board = new BoardView(1, 1, 300, 300);

    Group root = new Group();
    root.getChildren().add(board.getRoot());

    Scene scene = new Scene(root, 400, 400);

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }
}
