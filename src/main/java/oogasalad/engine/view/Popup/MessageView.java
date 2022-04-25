package oogasalad.engine.view.Popup;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageView extends PopupView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private VBox layout;
  private Button returnToGame;
  private String message;
  private String buttonText;

  public MessageView(String message, String buttonText, String css, String language) {
    super(css, language);
    this.message = message;
    this.buttonText = buttonText;
    setup();
  }

  public Stage getStage() {
    return popupStage;
  }

  public Button getReturnToGame() {
    return returnToGame;
  }

  @Override
  protected void setup() {
    layout = new VBox();
    layout.setId("message-screen-layout");
    root.setCenter(layout);
    makeMessage(message);
    makeButton(buttonText);
  }

  private void makeMessage(String message) {
    Text text = new Text(message);
    text.setId("message-screen-text");
    layout.getChildren().add(text);
  }

  private void makeButton(String buttonText) {
    returnToGame = new Button(buttonText);
    returnToGame.setId("message-screen-button");
    layout.getChildren().add(returnToGame);
  }
}
