package oogasalad.engine.view.game;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Text displayed in the game view during gameplay to update the user(s) on state of the game
 *
 * @author Cynthia France
 */
public class GameUpdateText {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";
  private String updateText;
  private Text text;
  private ResourceBundle myResources;
  private static final Logger LOG = LogManager.getLogger(GameUpdateText.class);

  /**
   *
   * creates the text that updates users on game state
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public GameUpdateText(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    updateText = myResources.getString("BeginGame");
    text = makeText(updateText);
  }

  /**
   *
   * updates the text to the correct player's turn
   *
   * @param player the player who's turn it is
   */
  public void updateText(int player) {
    updateText = MessageFormat.format(myResources.getString("PlayerTurn"), player+1);
    text.setText(updateText);
  }

  /**
   *
   * updates the text to display the winner
   *
   * @param player the winner
   */
  public void gameIsWon(int player) {
    updateText = MessageFormat.format(myResources.getString("GameOver"), player+1);
    text.setText(updateText);
  }

  /**
   *
   * returns the updateText
   *
   * @return returns the text
   */
  public Text getUpdateText() {
    return text;
  }

  private Text makeText(String text) {
    Text myText = new Text(text);
    myText.setId("player-text");
    return myText;
  }
}
