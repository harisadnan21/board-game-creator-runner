package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Field that takes in integer input, returning an integer property.
 *
 * @author Shaan Gondalia
 */
public class IntegerSelector implements PropertySelector{

  private Property property;
  private Spinner<Integer> numberPicker;
  /**
   * Creates a new Field that takes in integer input, returning an integer property.
   *
   * @param property the property that will be "filled in" by the Field
   */
  public IntegerSelector(Property property, CallbackDispatcher dispatcher) {
    this.property = property;
    //TODO: REPLACE MAGIC VALUES
    numberPicker = new Spinner<>(0, 50, 0, 1);
    numberPicker.setEditable(true);
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    String[] nameParts = property.name().split("-");
    return new IntegerProperty(nameParts[nameParts.length - 1], numberPicker.getValue().toString(), property.defaultValueAsString(), property.form()); //FIXME
  }

  @Override
  public Node display(){
    return numberPicker;
  }

  public void addListener(ChangeListener updateFields){
  }
}
