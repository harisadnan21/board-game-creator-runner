package oogasalad.engine.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import oogasalad.engine.view.setup.OpeningView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class OpeningViewTest extends DukeApplicationTest {

  OpeningView openingView;

  @Override
  public void start(Stage stage) throws IOException {
    openingView = new OpeningView(800, 800, "/css/light.css", "English");
    Scene scene = openingView.makeScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testContinue() {
    assertTrue(openingView.getContSel().isDisabled() == true);
  }

  @Test
  void testLanguage() throws AWTException {
    clickOn("#language-select");
    type(KeyEvent.VK_ENTER);
    assertEquals("English", openingView.getLanguageSelect().getLanguage());
  }

}
