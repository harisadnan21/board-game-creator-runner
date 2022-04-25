package oogasalad.engine.view.Popup.SettingsView.SettingsEntry;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import oogasalad.engine.view.OptionSelect.OptionSelect;

/**
 *
 * abstract class for the layout of an entry/element in the settings menu
 *
 * @author Cynthia France
 */
public abstract class SettingsEntry extends HBox {

  private Text text;
  private OptionSelect dropdown;

  /**
   *
   * creates the layout for an element in the settings menu, consisting of a Text label and
   * a dropdown menu
   *
   * @param txt what the label should display
   * @param select the dropdown menu
   */
  public SettingsEntry(String txt, OptionSelect select) {
    createText(txt);
    createDropdown(select);
    this.setId("theme-menu");
    this.getChildren().addAll(text, dropdown);
  }

  /**
   *
   * returns the dropdown menu for outside use
   *
   * @return the specified dropdown menu
   */
  public OptionSelect getDropdown() {
    return dropdown;
  }

  private void createText(String txt) {
    text = new EntryText(txt).getText();
  }

  private void createDropdown(OptionSelect select) {
    dropdown = select;
  }

}
