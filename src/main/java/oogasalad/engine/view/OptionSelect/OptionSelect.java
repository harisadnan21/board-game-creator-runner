package oogasalad.engine.view.OptionSelect;

import javafx.scene.control.ChoiceBox;

/**
 *
 * abstract class for dropdown selection menus
 *
 * @author Cynthia France
 */
public abstract class OptionSelect extends ChoiceBox<String> {
  protected String[] options;

  /**
   * creates a generic dropdown/ChoiceBox menu
   */
  public OptionSelect() {
    super();
    setup();
  }

  /**
   *
   * returns the user-selected value
   *
   * @return user-selected value
   */
  public String getElement() {
    if (this.getValue() == null) {
      return options[0];
    }
    else {
      return this.getValue();
    }
  }

  //add in all values to the menu
  protected void setup() {
    this.getItems().addAll(options);
    this.setId("option-select");
  }
}
