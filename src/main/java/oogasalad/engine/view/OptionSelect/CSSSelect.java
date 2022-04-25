package oogasalad.engine.view.OptionSelect;


/**
 *
 * Dropdown menu for CSS/theme selection
 * Inherits from OptionSelect
 *
 * @author Cynthia France
 */
public class CSSSelect extends OptionSelect {

  /**
   * creates a selection menu for different theme selection
   */
  public CSSSelect() {
    super();
  }

  /**
   * updates options with the correct values
   */
  @Override
  protected void setup() {
    options = new String[]{"light", "dark", "duke"};
    super.setup();
  }
}
