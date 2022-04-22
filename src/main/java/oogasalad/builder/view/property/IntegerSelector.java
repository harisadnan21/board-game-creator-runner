package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.IntegerStringConverter;
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
    numberPicker = new Spinner<>(new SpinnerValueFactory<>() {
      {
        setValue(Integer.parseInt(property.defaultValueAsString()));
        setConverter(new IntegerStringConverter());
      }

      @Override
      public void decrement(int i) {
        setValue(getValue() - i);
      }

      @Override
      public void increment(int i) {
        setValue(getValue() + i);
      }
    });

    numberPicker.setEditable(true);
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    return property.with(property.shortName(), numberPicker.getValue().toString());
  }

  @Override
  public Node display(){
    return numberPicker;
  }

  public void addListener(ChangeListener updateFields){
  }
}
