package oogasalad.engine.view.OptionSelect;


/**
 * @author Cynthia France
 */
public class CSSSelect extends OptionSelect {

  public CSSSelect() {
    super();
  }

  @Override
  protected void setup() {
    options = new String[]{"light", "dark", "duke"};
    super.setup();
  }
}
