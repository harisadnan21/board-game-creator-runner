package oogasalad.engine.view.OptionSelect;

import javafx.scene.control.ChoiceBox;

public class OptionSelect extends ChoiceBox<String> {
  protected String[] options;

  public OptionSelect() {
    super();
    setup();
  }

  protected void setup() {
    this.getItems().addAll(options);
    this.setId("option-select");
  }
}
