package oogasalad.engine.view.Popup.SettingsEntry;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import oogasalad.engine.view.OptionSelect.OptionSelect;

public abstract class SettingsEntry extends HBox {

  private Text text;
  private OptionSelect dropdown;

  public SettingsEntry(String txt, OptionSelect select) {
    createText(txt);
    createDropdown(select);
    this.setId("theme-menu");
    this.getChildren().addAll(text, dropdown);
  }

  public OptionSelect getDropdown() {
    return dropdown;
  }

  private void createText(String txt) {
    text = new Text(txt);
    text.setId("theme-text");
  }

  private void createDropdown(OptionSelect select) {
    dropdown = select;
  }

}
