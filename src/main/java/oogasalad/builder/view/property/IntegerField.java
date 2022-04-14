package oogasalad.builder.view.property;

import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.Property;

/**
 * Field that takes in integer input, returning an integer property.
 *
 * @author Shaan Gondalia
 */
public class IntegerField extends Field{

  /**
   * Creates a new Field that takes in integer input, returning an integer property.
   *
   * @param property the property that will be "filled in" by the Field
   */
  public IntegerField(Property property){
    super(property);
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    String[] nameParts = property().name().split("-");
    return new IntegerProperty(nameParts[nameParts.length - 1], text(), property().form());
  }
}
