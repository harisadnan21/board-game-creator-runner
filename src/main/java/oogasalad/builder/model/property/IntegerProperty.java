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
   * Creates a new property with a name and integer value
   *
   * @param name  the name of the property
   * @param value a string representation of an integer
   * @param defaultValue a string representation of the default integer value
   * @param form  the form of the property
   */
  public IntegerProperty(String name, String value, String defaultValue, String form) {
    this(name, Integer.valueOf(value), Integer.valueOf(defaultValue), form);
  }

  /**
   * Returns an instance of this property but with the information specified
   *
   * @param name the name of the new property
   * @param value the value of the new property as a string
   * @param defaultValue the default value of the new property as a string
   * @param form the form of the new property
   * @return a new property of the same type but with the provided information instead
   */
  public IntegerProperty with(String name, String value, String defaultValue, String form) {
    return new IntegerProperty(name, stringToValue(value), stringToValue(defaultValue), form);
  }

  @Override
  protected String valueToString(Integer value) {
    return value.toString();
  }

  @Override
  protected Integer stringToValue(String string) {
    return Integer.parseInt(string);
  }

}
