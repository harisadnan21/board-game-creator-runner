package oogasalad.engine.view.OptionSelect;

import oogasalad.engine.view.MouseSound;

/**
 * @author Cynthia France
 */
public class MouseSoundSelect extends OptionSelect {

  public MouseSoundSelect() {
    super();
  }

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
