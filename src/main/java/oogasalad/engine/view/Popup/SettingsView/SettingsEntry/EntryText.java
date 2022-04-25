package oogasalad.engine.view.Popup.SettingsView.SettingsEntry;

import javafx.scene.text.Text;

/**
 *
 * The label text for an entry in the settings menu
 *
 * @author Cynthia France
 */
public class EntryText {
  Text entryText;

  /**
   *
   * creates a Text object that displays the label/description for a dropdown menu in the settings menu
   *
   * @param text the label/text displayed
   */
  public EntryText(String text) {
    entryText = new Text(text);
    entryText.setId("theme-text");
  }

  /**
   *
   * returns the text object
   *
   * @return the text object
   */
  public Text getText() {
    return entryText;
  }
}
