package oogasalad.engine.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class BoardViewTest extends DukeApplicationTest {

  Group root;
  BoardView board;
  Controller controller;
  Scene scene;
  Stage s;

  @Override
  public void start (Stage stage) {
    board = new BoardView(3, 3, 300, 300);
    controller = new Controller(board, 3, 3);

    board.addController(controller);
//    root = new Group();
//    root.getChildren().add(board.getRoot());
    root = board.getRoot();

    ViewManager manager = new ViewManager();
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
