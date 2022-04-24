package oogasalad.engine.view;

public class LanguageSelect extends OptionSelect {

  public LanguageSelect() {
    super();
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
