package oogasalad.engine.view.OptionSelect;

import oogasalad.engine.view.MouseSound;

/**
 *
 * Dropdown menu for CSS/theme selection
 * Inherits from OptionSelect
 *
 * @author Cynthia France
 */
public class MouseSoundSelect extends OptionSelect {

  /**
   * creates a selection menu for different mouse sound selection
   */
  public MouseSoundSelect() {
    super();
  }

  /**
   * updates options with the correct values, read in from MouseSound.SOUNDS
   */
  @Override
  protected void setup() {
    options = new String[MouseSound.SOUNDS.length + 1];
    for (int i = 0; i < MouseSound.SOUNDS.length; i++) {
      options[i] = MouseSound.SOUNDS[i];
    }
    options[MouseSound.SOUNDS.length] = "none";
    super.setup();
  }
}
