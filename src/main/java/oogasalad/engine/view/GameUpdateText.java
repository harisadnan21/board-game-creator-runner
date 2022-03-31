package oogasalad.engine.view;

import javafx.scene.text.Text;
import javafx.util.Pair;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Position;

public class GameUpdateText {
  private String updateText;
  private Text text;

  public GameUpdateText() {
    updateText = "Begin Game";
    text = new Text(updateText);
  }

  public void updateText(int i, int j) {
    updateText = String.format("%s at (%d, %d)", "action", i, j);
    text.setText(updateText);
  }

  public Text getUpdateText() {
    return text;
  }
}
