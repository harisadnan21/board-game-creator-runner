package oogasalad.engine.view.OptionSelect;

import oogasalad.engine.view.OptionSelect.OptionSelect;

public class CSSSelect extends OptionSelect {

  public CSSSelect() {
    super();
  }

  public String getCSS() {
    if (this.getValue() == null) {
      return "light";
    }
    else {
      return this.getValue();
    }
  }

  @Override
  protected void setup() {
    options = new String[]{"light", "dark", "duke"};
    super.setup();
  }
}
