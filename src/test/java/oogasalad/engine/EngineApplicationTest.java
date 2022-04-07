package oogasalad.engine;

import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.view.BoardView;
import oogasalad.engine.view.ViewManager;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class EngineApplicationTest extends DukeApplicationTest {

  @Override
  public void start (Stage stage) throws IOException, OutOfBoardException {
    BoardView board = new BoardView(3, 3, 300, 300);
    Controller controller = new Controller(board, 3, 3);

    board.addController(controller);
    Group root = new Group();
    root.getChildren().add(board.getRoot());

    ViewManager manager = new ViewManager();
    Scene scene = manager.createGameView(board, controller).makeScene();
    stage.setScene(scene);
    stage.show();
  }
}
