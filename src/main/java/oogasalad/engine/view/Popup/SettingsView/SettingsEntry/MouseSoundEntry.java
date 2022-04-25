package oogasalad.engine.view.Popup.SettingsView.SettingsEntry;

import oogasalad.engine.view.OptionSelect.OptionSelect;


/**
 *
 * settings entry specific for mouse sound selection
 * inherits from SettingsEntry
 *
 * @author Cynthia France
 */
public class MouseSoundEntry extends SettingsEntry {

  /**
   *
   * creates a settings entry for mouse sound selection
   *
   * @param txt what the label should display
   * @param select the dropdown menu
   */
  public MouseSoundEntry(String txt, OptionSelect select) {
    super(txt, select);
  }
}
