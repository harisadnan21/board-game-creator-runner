package oogasalad.engine.view;

import java.io.File;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.Popup.MessageView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class MessageViewTest extends DukeApplicationTest {

  MessageView messageView;
  Stage popupStage;
  Boolean hidden = false;

  @Override
  public void start (Stage stage) throws IOException {
    messageView = new MessageView("message", "clickMe", "/css/light.css", "English");
    popupStage = messageView.getStage();
    messageView.getReturnToGame().setOnAction(event -> {
      popupStage.hide();
    });
    popupStage.setOnHidden(e -> {hidden = true;});
    popupStage.show();
  }

  @Test
  void testResume() {
    clickOn(messageView.getReturnToGame());
    Assertions.assertTrue(hidden);
  }

}
