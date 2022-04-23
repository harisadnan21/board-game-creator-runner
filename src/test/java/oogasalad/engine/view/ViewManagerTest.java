package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.engine.view.ViewManager.ViewManager;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ViewManagerTest extends DukeApplicationTest {
  ViewManager viewManager;
  Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    viewManager = new ViewManager(stage);
    scene = viewManager.getCurrScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testScene() {
    Scene scene = viewManager.getCurrScene();
    clickOn("#opening-button");
    clickOn();
    clickOn("#opening-button");
    assertEquals(scene, viewManager.getCurrScene());
  }

  @Test
  void testDash() {
    Scene scene = viewManager.getCurrScene();
    clickOn("#opening-button");
    clickOn();
    clickOn("#opening-button");
    assertEquals(scene, viewManager.getCurrScene());
  }
}
