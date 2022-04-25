package oogasalad.engine.view.Popup.SettingsView.SettingsEntry;

import oogasalad.engine.view.OptionSelect.OptionSelect;

/**
 *
 * settings entry specific for theme selection
 * inherits from SettingsEntry
 *
 * @author Cynthia France
 */
public class ThemeSoundEntry extends SettingsEntry {

  /**
   *
   * creates a settings entry for theme selection
   *
   * @param txt what the label should display
   * @param select the dropdown menu
   */
  public ThemeSoundEntry(String txt, OptionSelect select) {
    super(txt, select);
  }
}
