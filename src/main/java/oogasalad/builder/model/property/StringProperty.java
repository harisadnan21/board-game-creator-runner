package oogasalad.builder.model.property;


/**
 * Concrete class representing a String property. The value of this property is a String.
 *
 * @author Shaan Gondalia
 */
public class StringProperty extends AbstractProperty<String> {

  /**
   * Creates a new property with a name and string value
   *
   * @param name  the name of the property
   * @param value a string
   * @param defaultValue the default value of the property
   * @param form  the form of the property
   */
  public StringProperty(String name, String value, String defaultValue, String form) {
    super(name, value, defaultValue, form);
  }

  /**
   * Creates a new property with a name and string value
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value a string
   * @param form  the form of the property
   */
  public StringProperty(String name, String value, String form) {
    this(name, value, value, form);
  }

  /**
   * Returns a property identical to this one, except with a different value
   *
   * @param newValue the value to give the new property
   * @return this new property with a different value
   */
  @Override
  public StringProperty withValue(String newValue) {
    return new StringProperty(name(), newValue, defaultValue(), form());
  }

  // These are useless here, but at least things are unified
  @Override
  protected String valueToString(String value) {
    return value;
  }

  @Override
  protected String stringToValue(String string) {
    return string;
  }

}
