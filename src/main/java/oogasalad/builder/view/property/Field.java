package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * The most basic form of a PropertySelector, essentially just a wrapper for a JavaFX text box.
 *
 * @author Shaan Gondalia
 */
public abstract class Field implements PropertySelector{

  private final Property property;
  private final TextField valueField;

  /**
   * Creates a new Field, which is the most simple property selector (Just a textfield)
   *
   * @param property the property that will be "filled in" by the Field
   */
  public Field(Property property, CallbackDispatcher dispatcher){
    this.property = property;
    valueField = new TextField(property.valueAsString());
  }

  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  @Override
  public Node display() {
    return valueField;
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public abstract Property getProperty();

  /**
   * Returns the property that was passed in during construction of the field
   *
   * @return the property that was passed in during construction of the field
   */
  protected Property property() {
    return property;
  }

  /**
   * Returns the text that was input into the value field
   *
   * @return the property that was passed in during construction of the field
   */
  protected String text() {
    return valueField.getText();
  }
}
