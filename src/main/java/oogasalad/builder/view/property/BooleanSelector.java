package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Field that takes in boolean input, returning an integer property with 0 for false and 1 for true
 *
 * @author Shaan Gondalia
 */
public class BooleanSelector implements PropertySelector {

  private Property property;
  private CheckBox checkBox;

  /**
   * Creates a new Field that takes in boolean input, returning an integer property.
   * 0 is false and 1 is true
   *
   * @param property the property that will be "filled in" by the Field
   */
  public BooleanSelector(Property property, CallbackDispatcher dispatcher) {
    this.property = property;
    checkBox = new CheckBox();
    checkBox.setAllowIndeterminate(false);
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    String value = checkBox.isSelected() ? "1" : "0";
    return property.with(property.shortName(), value);
  }

  @Override
  public Node display(){
    return checkBox;
  }

  public void addListener(ChangeListener updateFields){
  }
}
