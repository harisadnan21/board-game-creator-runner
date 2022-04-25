package oogasalad.engine.view.OptionSelect;


/**
 *
 * Dropdown menu for CSS/theme selection
 * Inherits from OptionSelect
 *
 * @author Cynthia France
 */
public class LanguageSelect extends OptionSelect {

  /**
   * creates a selection menu for different language selection
   */
  public LanguageSelect() {
    super();
    this.setId("language-select");
  }

  /**
   * updates options with the correct values
   */
  @Override
  protected void setup() {
    options = new String[]{"English", "Spanish", "French", "German"};
    super.setup();
  }

}
