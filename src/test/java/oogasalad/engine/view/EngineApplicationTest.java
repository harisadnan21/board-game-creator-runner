package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.view.setup.OpeningView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class EngineApplicationTest extends DukeApplicationTest {

  ViewManager viewManager;

  @Override
  public void start(Stage stage) throws IOException {
    viewManager = new ViewManager(stage);
    Scene scene = viewManager.getCurrScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testOpening() {
    assertEquals(800, viewManager.getCurrScene().getHeight());
    assertEquals(800, viewManager.getCurrScene().getWidth());
  }




}
