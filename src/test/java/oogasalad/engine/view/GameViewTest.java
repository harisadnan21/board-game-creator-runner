package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.game.GameView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameViewTest extends DukeApplicationTest {

  ViewManager manager;
  GameView gameView;
  Node root;
  BoardView board;
  Controller controller;
  Scene scene;
  Stage s;
  File game;

  @Override
  public void start (Stage stage) throws IOException {
    game = new File("data/games/checkers");
    //board = new BoardView(new File("data/games/checkers"), 8, 8, 300, 300, "/css/light.css", "English");

    GameParser parser = new GameParser(new File("data/games/checkers/config.json"));
    Board backEndBoard = parser.parseBoard();

    board = new BoardView(controller, game, backEndBoard, 600, 600, "/css/light.css", "English");
    Consumer<Set<Position>> setMarkersLambda = board::setValidMarkers;

    controller.startEngine(parser, setMarkersLambda, board::endGame);
    controller.getGame().addListener(board);
    root = board.getRoot();

    manager = new ViewManager(stage);
    gameView = manager.createGameView(board, controller, game);

    //gameView = new GameView(board, controller, 800, 800, "/css/light.css", "English", new File("data/games/checkers"));
    scene = gameView.makeScene();

    s = stage;
    s.setScene(scene);
    s.show();
  }

  @Test
  void testCSS() throws AWTException {
    clickOn("#cp-button");
    clickOn(gameView.getCssDropdown());
    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_ENTER);
    assertEquals("dark", gameView.getCssDropdown().getElement());
  }

  @Test
  void testLanguage() {
    clickOn(gameView.getSoundDropdown());
    assertEquals("Click", gameView.getSoundDropdown().getElement());
  }

}
