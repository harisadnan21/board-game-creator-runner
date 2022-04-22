package oogasalad.builder.model.property;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Concrete class representing an StringList property. The value of this property is a collection of
 * strings.
 *
 * @author Shaan Gondalia
 */
public class StringListProperty extends AbstractProperty<Collection<String>> {

  private static final String DELIMITER = ",";

  /**
   * Creates a new property with the given name and value.
   *
   * @param name  the name of the property
   * @param value a collection of strings
   * @param defaultValue the default value of the property
   * @param form  the form of the property
   */
  public StringListProperty(String name, Collection<String> value, Collection<String> defaultValue, String form) {
    super(name, value, defaultValue, form);
  }

  /**
   * Creates a new property with the given name and value.
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value a collection of strings
   * @param form  the form of the property
   */
  public StringListProperty(String name, Collection<String> value, String form) {
    super(name, value, form);
  }

  /**
   * Creates a new property with the given name and value.
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value a string representation of a list of strings
   * @param form  the form of the property
   */
  public StringListProperty(String name, String value, String form) {
    super(name, parseList(value), parseList(value), form);
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
  public StringListProperty with(String name, String value, String defaultValue, String form) {
    return new StringListProperty(name, stringToValue(value), stringToValue(defaultValue), form);
  }

  // Converts a collection of string to a single string
  @Override
  protected String valueToString(Collection<String> value) {
    return String.join(DELIMITER, value);
  }

  @Override
  protected Collection<String> stringToValue(String string) {
    return parseList(string);
  }

  private static Collection<String> parseList(String string) {
    return string.isBlank() ? List.of() : List.of(string.split(DELIMITER));
  }

  /**
   * Returns a copy of the value of the property
   *
   * @return a copy of the value of the property
   */
  @Override
  public Collection<String> value() {
    return new ArrayList<>(super.value());
  }

}
