package oogasalad.engine.view.setup.SelectionView;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Cynthia France
 */
public class PlayerModeView extends SelectionView {
  Button twoPlayer;
  Button onePlayer;

  public PlayerModeView(double w, double h, String css, String language) {
    super(w, h, css, language);
  }

  public Button getOnePlayer() {
    return onePlayer;
  }
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
