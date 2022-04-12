package oogasalad.builder.model.property;


import java.util.Collection;
import java.util.HashSet;
import org.json.JSONArray;

/**
 * Concrete class representing an StringList property. The value of this property is a collection of
 * strings.
 *
 * @author Shaan Gondalia
 */
public class StringListProperty extends AbstractProperty<Collection<String>>{

  /**
   * Creates a new property with the given name and value.
   *
   * @param name the name of the property
   * @param value a collection of strings
   */
  public StringListProperty(String name, Collection<String> value) {
    super(name, value);
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

  // Converts a collection of string to a single string
  private static String collectionToString(Collection<String> values){
    JSONArray arr = new JSONArray(values);
    return arr.toString();
  }

}
