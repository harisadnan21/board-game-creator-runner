package oogasalad.builder.model.property;


/**
 * Concrete class representing an Integer property. The value of this property is an Integer.
 *
 * @author Shaan Gondalia
 */
public class IntegerProperty extends AbstractProperty<Integer>{

  /**
   * Creates a new property with a name and integer value
   *
   * @param name the name of the property
   * @param value an integer
   * @param form the form of the property
   */
  public IntegerProperty(String name, Integer value, String form) {
    super(name, value, form);
  }

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  public String valueAsString() {
    return value().toString();
  }

}
