package oogasalad.engine.view;

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
