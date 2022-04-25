package oogasalad.engine.view.Popup;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * Popup notification that contains a singular message for the user to see
 * Inherits from PopupView
 *
 * @author Cynthia France
 */
public class MessageView extends PopupView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";

  private VBox layout;
  private Button returnToGame;
  private String message;
  private String buttonText;

  /**
   *
   * creates a stage on top containing a message for the user to see
   *
   * @param message the message for the user
   * @param buttonText the text to tell users to close the message
   * @param css the filepath for styling
   * @param language user-specified language in which the UI is displayed in
   */
  public MessageView(String message, String buttonText, String css, String language) {
    super(css, language);
    this.message = message;
    this.buttonText = buttonText;
    setup();
  }

  /**
   *
   * returns the button to return to normal game play, for GameView to use
   *
   * @return button to return to normal game play
   */
  public Button getReturnToGame() {
    return returnToGame;
  }

  /**
   * creates the layout for this message popup
   */
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
