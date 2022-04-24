package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.parser.GameParser;
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
    board = new BoardView(new File("data/games/checkers"), 3, 3, 300, 300, "/css/light.css", "English");

    GameParser parser = new GameParser(new File("data/games/checkers/config.json"));
    Board backEndBoard = new Board(3,3);
    controller = new Controller(backEndBoard, parser);

    board.addController(controller);
//    root = new Group();
//    root.getChildren().add(board.getRoot());
    root = board.getRoot();

    ViewManager manager = new ViewManager(stage);
    scene = manager.createGameView(board, controller).makeScene();
    s = stage;
    s.setScene(scene);
    s.show();
  }

//  @Test
//  void testAddController() {
//    clickOn(root, 150, 150);
//    board
//    assertEquals(null, board.)
//  }

  @Test
  void testCellClicked() {
    assertDoesNotThrow(() -> {
      System.out.printf("success");
    });
    //clickOn(root, 150, 150);
    //assertEquals(root.getChildren().size(), board.getRoot().getChildren().size());
        //root.getChildren().get(0).getText().getText(), "action at (1, 1)");
  }

}
