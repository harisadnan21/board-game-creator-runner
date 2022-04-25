package oogasalad.engine.view.game;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Cynthia France
 */
public class GameUpdateText {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";
  private String updateText;
  private Text text;
  private ResourceBundle myResources;
  private static final Logger LOG = LogManager.getLogger(GameUpdateText.class);

  public GameUpdateText(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    updateText = myResources.getString("BeginGame");
    text = makeText(updateText);
  }

  public void updateText(int player) {
    updateText = MessageFormat.format(myResources.getString("PlayerTurn"), player+1);
    text.setText(updateText);
  }

  public void gameIsWon(int player) {
    updateText = MessageFormat.format(myResources.getString("GameOver"), player+1);
    text.setText(updateText);
  }

  public Text getUpdateText() {
    return text;
  }

  private Text makeText(String text) {
    Text myText = new Text(text);
    myText.setId("player-text");
    return myText;
  }
}
