package oogasalad.builder.model.property;

import java.util.Collection;
import org.json.JSONArray;

/**
 * Util class that offers methods for creating properties from objects that are not strings
 *
 * @author Shaan Gondalia
 */
public class PropertyFactory {

  // Private constructor for Util Class
  private PropertyFactory() {}

  /**
   * Makes a property from a string
   *
   * @param name the name of the property
   * @param value the string value of the property
   * @return a new property created from the string
   */
  public static Property makeProperty(String name, String value){
    return new Property(name, value);
  }

  /**
   * Makes a property from an integer
   *
   * @param name the name of the property
   * @param value the integer value of the property
   * @return a new property created from the integer
   */
  public static Property makeProperty(String name, Integer value){
    return new Property(name, value.toString());
  }

  /**
   * Makes a property from a collection of strings
   *
   * @param name the name of the property
   * @param value the collection of strings to store in the property
   * @return a new property created from the collection of strings
   */
  public static Property makeProperty(String name, Collection<String> value){
    return new Property(name, collectionToString(value));
  }

  // Converts a collection of string to a single string
  private static String collectionToString(Collection<String> values){
    JSONArray arr = new JSONArray(values);
    return arr.toString();
  }


}
