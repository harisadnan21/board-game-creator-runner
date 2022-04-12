package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import oogasalad.builder.model.property.Property;

/**
 * The most basic form of a PropertySelector, essentially just a wrapper for a JavaFX text box.
 *
 * @author Shaan Gondalia
 */
public class Field implements PropertySelector{

  private final Property property;
  private final TextField valueField;

  /**
   * Creates a new Field, which is the most simple property selector (Just a textfield)
   *
   * @param property the property that will be "filled in" by the Field
   */
  public Field(Property property){
    this.property = property;
    valueField = new TextField(property.value());
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
   * Returns the actual text input of the user that should be stored in the property
   *
   * @return the text input corresponding to the property value that should be stored
   */
  @Override
  public String getPropertyValue() {
    return valueField.getText();
  }
}
