package oogasalad.builder.model.property;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;

/**
 * Concrete class representing an StringList property. The value of this property is a collection of
 * strings.
 *
 * @author Shaan Gondalia
 */
public class StringListProperty extends AbstractProperty<Collection<String>> {

  private static final String DELIMITER = "-";

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
   * Creates a new property with a name and integer value
   * Sets the set value to be the default value
   *
   * @param name  the name of the property
   * @param value a string representation of an integer
   * @param form  the form of the property
   */
  public StringListProperty(String name, String value, String form) {
    super(name, value.isBlank() ? List.of() : List.of(value.split(DELIMITER)), form);
  }

  /**
   * Returns a property identical to this one, except with a different value
   *
   * @param newValue the value to give the new property
   * @return this new property with a different value
   */
  @Override
  public StringListProperty withValue(Collection<String> newValue) {
    return new StringListProperty(name(), newValue, form());
  }

  // Converts a collection of string to a single string
  private static String collectionToString(Collection<String> values) {
    JSONArray arr = new JSONArray(values);
    return arr.toString();
  }

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  public String valueAsString() {
    return collectionToString(value());
  }

  /**
   * Returns a copy of the value of the property
   *
   * @return a copy of the value of the property
   */
  @Override
  public Collection<String> value() {
    return new HashSet<>(super.value());
  }

}
