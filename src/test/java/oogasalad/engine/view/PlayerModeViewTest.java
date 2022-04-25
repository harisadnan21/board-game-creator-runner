package oogasalad.engine.view;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.view.setup.OpeningView;
import oogasalad.engine.view.setup.SelectionView.PlayerModeView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayerModeViewTest extends DukeApplicationTest {

  PlayerModeView pmv;

  @Override
  public void start(Stage stage) throws IOException {
    pmv = new PlayerModeView(800, 800, "/css/light.css", "English");
    Scene scene = pmv.makeScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void test() {
    clickOn(pmv.getOnePlayer());
    clickOn(pmv.getTwoPlayer());
    Assertions.assertDoesNotThrow(() -> {
      ;
    });
  }
}
