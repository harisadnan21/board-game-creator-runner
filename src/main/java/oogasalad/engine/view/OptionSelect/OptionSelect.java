package oogasalad.engine.view.OptionSelect;

import javafx.scene.control.ChoiceBox;

/**
 * @author Cynthia France
 */
public abstract class OptionSelect extends ChoiceBox<String> {
  protected String[] options;

  public OptionSelect() {
    super();
    setup();
  }

  public String getElement() {
    if (this.getValue() == null) {
      return options[0];
    }
    else {
      return this.getValue();
    }
  }

  protected void setup() {
    this.getItems().addAll(options);
    this.setId("option-select");
  }
}
