package oogasalad.engine.view.dashboard;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.view.ViewManager;
import oogasalad.engine.view.game.BoardView;
import oogasalad.engine.view.setup.dashboard.Dashboard;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class DashboardTest extends DukeApplicationTest {
  Node root;
  BoardView board;
  Controller controller;
  Scene scene;
  Stage s;
  Dashboard dashboard;

  @Override
  public void start (Stage stage) throws IOException {
    Board modelBoard = new Board(3,3);

    board = new BoardView(controller, new File("data/games/checkers"), modelBoard, 300, 300, "/css/light.css", "English");
    Consumer<File> fakeStart = this::fakeStart;
    dashboard = new Dashboard(fakeStart);
    root = board.getRoot();

    ViewManager manager = new ViewManager(stage);
    scene = manager.createGameView(board, controller, new File("data/games/checkers")).makeScene();
    s = stage;
    s.setScene(scene);
    s.show();
  }
  public void fakeStart(File file){}


  @Test
  public void testClickOnGameIcon(){

  }
}