package oogasalad.builder.model.property;


/**
 * Concrete class representing a String property. The value of this property is a String.
 *
 * @author Shaan Gondalia
 */
public class StringProperty extends AbstractProperty<String>{

  /**
   * Creates a new property with a name and string value
   *
   * @param name the name of the property
   * @param value a string
   */
  public StringProperty(String name, String value) {
    super(name, value);
  }

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  public String valueAsString() {
    return value();
  }

}
