package oogasalad.engine.view.setup.SelectionView;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * Selection window for the users to choose between modes of playing
 * inherits from SelectionView
 *
 * @author Cynthia France
 */
public class PlayerModeView extends SelectionView {
  Button twoPlayer;
  Button onePlayer;

  /**
   *
   * creates the window for users to select playing mode
   *
   * @param w width of window
   * @param h height of window
   * @param css the filepath for styling
   * @param language user-specified language in which the UI is displayed in
   */
  public PlayerModeView(double w, double h, String css, String language) {
    super(w, h, css, language);
  }

  /**
   *
   * returns the button for one player game play
   *
   * @return button for one player game play
   */
  public Button getOnePlayer() {
    return onePlayer;
  }

  /**
   *
   * returns the button for two player game play
   *
   * @return button for two player game play
   */
  public Button getTwoPlayer() {
    return twoPlayer;
  }

  protected void setup() {
    windowLayout = new VBox();
    windowLayout.setId("p-mode-window-layout");
    makeText();
    makeButtons();
    windowLayout.getChildren().addAll(insns, buttonLayout);
  }

  protected void makeText() {
    insns = new Text(myResources.getString("PlayerMode"));
    insns.setId("p-mode-insns");
  }

  protected void makeButtons() {
    buttonLayout = new FlowPane();
    buttonLayout.setId("p-mode-button-layout");
    onePlayer = makeButton(myResources.getString("1Player"));
    twoPlayer = makeButton(myResources.getString("2Player"));
    buttonLayout.getChildren().addAll(onePlayer, twoPlayer);
  }

  protected Button makeButton(String text) {
    Button b = new Button(text);
    b.setId("p-mode-button");
    return b;
  }
}
