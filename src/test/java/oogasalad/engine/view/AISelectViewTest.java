package oogasalad.engine.view;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.game.GameView;
import oogasalad.engine.view.setup.OpeningView;
import oogasalad.engine.view.setup.SelectionView.AISelectView;
import oogasalad.engine.view.setup.dashboard.GameIcon;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class AISelectViewTest extends DukeApplicationTest {

  AISelectView aiSelectView;
  Controller controller;
  File game;

  @Override
  public void start(Stage stage) throws IOException {
    game = new File("data/games/checkers");
    GameParser parser = new GameParser(new File("data/games/checkers/config.json"));
    Board backEndBoard = parser.parseBoard();
    controller = new Controller(backEndBoard, parser);

    aiSelectView = new AISelectView(800, 800, "/css/light.css", "English", game,
        new Stage(), this::startGame);
    Scene scene = aiSelectView.makeScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

  private void startGame(File file, Stage stage, String[] strings) {
  }

  @Test
  void testButton() {
    clickOn("#p-mode-button");
  }

}
