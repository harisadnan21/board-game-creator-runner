package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringProperty;

/**
 * The most basic form of a PropertySelector, essentially just a wrapper for a JavaFX text box.
 *
 * @author Shaan Gondalia
 */
public class StringField extends Field{

  /**
   * Creates a new Field, which is the most simple property selector (Just a textfield)
   *
   * @param property the property that will be "filled in" by the Field
   */
  public StringField(Property property){
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
    return new StringProperty(nameParts[nameParts.length - 1], text(), property().form());
  }

  @Override
  public void addListener(ChangeListener updateFields) {

  }
}
