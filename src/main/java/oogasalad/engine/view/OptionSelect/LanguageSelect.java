package oogasalad.engine.view.OptionSelect;


/**
 * @author Cynthia France
 */
public class LanguageSelect extends OptionSelect {

  public LanguageSelect() {
    super();
    this.setId("language-select");
  }

  @Override
  protected void setup() {
    options = new String[]{"English", "Spanish", "French", "German"};
    super.setup();
  }

}
