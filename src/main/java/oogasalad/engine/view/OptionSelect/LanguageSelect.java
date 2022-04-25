package oogasalad.engine.view.OptionSelect;

import oogasalad.engine.view.OptionSelect.OptionSelect;

public class LanguageSelect extends OptionSelect {

  public LanguageSelect() {
    super();
    this.setId("language-select");
  }

  public String getLanguage() {
    if (this.getValue() == null) {
      return "English";
    }
    else {
      return this.getValue();
    }
  }

  @Override
  protected void setup() {
    options = new String[]{"English", "Spanish", "French", "German"};
    super.setup();
  }

}
