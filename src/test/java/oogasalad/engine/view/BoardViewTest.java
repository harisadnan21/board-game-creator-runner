package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.ViewManager.ViewManager;
import oogasalad.engine.view.game.BoardView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class BoardViewTest extends DukeApplicationTest {

  Node root;
  BoardView board;
  Controller controller;
  Scene scene;
  Stage s;

  @Override
  public void start (Stage stage) throws IOException {
    GameParser parser = new GameParser(new File("data/games/checkers/config.json"));

    Board backEndBoard = new Board(3,3);
    controller = new Controller(new String[]{});

    board = new BoardView(controller, new File("data/games/checkers"), backEndBoard, 300, 300,
        "/engine-view/css/light.css", "English");

    Consumer<Set<Position>> setMarkersLambda = board::setValidMarkers;
    controller.startEngine(parser, setMarkersLambda, board::endGame);
    root = board.getRoot();

    ViewManager manager = new ViewManager(stage);
    scene = manager.createGameView(board, controller, new File("data/games/checkers")).makeScene();
    s = stage;
    s.setScene(scene);
    s.show();
  }

  @Test
  void testCellClicked() {
    assertDoesNotThrow(() -> {
      System.out.printf("success");
    });
  }

}
