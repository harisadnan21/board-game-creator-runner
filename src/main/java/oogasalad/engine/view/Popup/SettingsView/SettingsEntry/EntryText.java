package oogasalad.engine.view.Popup.SettingsView.SettingsEntry;

import javafx.scene.text.Text;

public class EntryText {
  Text entryText;

  public EntryText(String text) {
    entryText = new Text(text);
    entryText.setId("theme-text");
  }

  public Text getText() {
    return entryText;
  }
}
