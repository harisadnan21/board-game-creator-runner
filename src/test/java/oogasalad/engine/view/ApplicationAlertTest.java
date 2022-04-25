package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oogasalad.engine.view.setup.OpeningView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ApplicationAlertTest extends DukeApplicationTest {

  ApplicationAlert alert;

  @Override
  public void start(Stage stage) throws IOException {
    alert = new ApplicationAlert("notification", "test");
//    openingView = new OpeningView(800, 800, "/css/light.css", "English");
//    Scene scene = openingView.makeScene();
//
//    stage.setTitle("OOGABOOGA Engine");
//    stage.setScene(scene);
//    stage.show();
  }

  @Test
  void testExit() {
    assertNotNull(alert);
  }

}
