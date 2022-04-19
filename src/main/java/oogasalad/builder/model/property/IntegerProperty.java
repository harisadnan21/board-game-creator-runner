package oogasalad.builder.model.property;


/**
 * Concrete class representing an Integer property. The value of this property is an Integer.
 *
 * @author Shaan Gondalia
 */
public class IntegerProperty extends AbstractProperty<Integer> {

  /**
   * Creates a new property with a name and integer value
   *
   * @param name  the name of the property
   * @param value an integer
   * @param defaultValue the default value of the property
   * @param form  the form of the property
   */
  public IntegerProperty(String name, Integer value, Integer defaultValue, String form) {
    super(name, value, value, form);
  }

  /**
   * Creates a new property with a name and integer value
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value an integer
   * @param form  the form of the property
   */
  public IntegerProperty(String name, Integer value, String form) {
    this(name, value, value, form);
  }

  /**
   * Creates a new property with a name and integer value
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value a string representation of an integer
   * @param form  the form of the property
   */
  public IntegerProperty(String name, String value, String form) {
    this(name, Integer.valueOf(value), form);
  }

  /**
   * Returns a property identical to this one, except with a different value
   *
   * @param newValue the value to give the new property
   * @return this new property with a different value
   */
  @Override
  public IntegerProperty withValue(Integer newValue) {
    return new IntegerProperty(name(), newValue, defaultValue(), form());
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
