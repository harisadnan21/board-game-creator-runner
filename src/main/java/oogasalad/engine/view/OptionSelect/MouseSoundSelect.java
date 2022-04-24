package oogasalad.engine.view.OptionSelect;

import oogasalad.engine.view.MouseSound;

public class MouseSoundSelect extends OptionSelect {

  public MouseSoundSelect() {
    super();
  }

  public String getSound() {
    if (this.getValue() == null) {
      return "Click";
    }
    else {
      return this.getValue();
    }
  }

  @Override
  protected void setup() {
    options = new String[MouseSound.SOUNDS.length + 1];
    options[0] = "none";
    for (int i = 1; i <= MouseSound.SOUNDS.length; i++) {
      options [i] = MouseSound.SOUNDS[i-1];
    }
    super.setup();
  }
}
