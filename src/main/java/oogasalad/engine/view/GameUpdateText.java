package oogasalad.engine.view;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameUpdateText {
  private String updateText;
  private Text text;

  public GameUpdateText() {
    updateText = "Begin Game";
    text = makeText(updateText);
  }

  public void updateText(int player) {
    updateText = String.format("Player %s's turn", player);
    text.setText(updateText);
  }

  public Text getUpdateText() {
    return text;
  }

  private Text makeText(String text) {
    Text myText = new Text(text);
    myText.setFont(Font.font("Montserrat", 20));
    myText.setFill(Color.web("464646"));
    return myText;
  }
}
