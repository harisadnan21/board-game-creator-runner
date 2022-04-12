package oogasalad.builder.model.property;

import java.util.Collection;
import org.json.JSONArray;

/**
 * Util class that offers methods for creating properties from objects that are not strings
 *
 * @author Shaan Gondalia
 */
public class PropertyFactory {
  // TODO: Replace this with actual default form classpath
  private static final String DEFAULT_FORM = "oogasalad.builder.view.property.Field";

  // Private constructor for Util Class
  private PropertyFactory() {}

  /**
   * Default "constructor" for a property. Uses the default form, name, and value
   *
   * @param name the name of the property
   * @param value the string value of the property
   * @return a new property created from the string
   */
  public static Property makeProperty(String name, String value){
    return new StringProperty(name, value, DEFAULT_FORM);
  }

  /**
   * Makes a property from an integer
   *
   * @param name the name of the property
   * @param value the integer value of the property
   * @return a new property created from the integer
   */
  public static Property makeProperty(String name, Integer value){
    return new IntegerProperty(name, value, DEFAULT_FORM);
  }

  /**
   * Makes a property from a collection of strings
   *
   * @param name the name of the property
   * @param value the collection of strings to store in the property
   * @return a new property created from the collection of strings
   */
  public static Property makeProperty(String name, Collection<String> value){
    return new StringListProperty(name, value, DEFAULT_FORM);
  }

  /**
   * Makes a property from a string
   *
   * @param name the name of the property
   * @param value the string value of the property
   * @param form the form of the property
   * @return a new property created from the string
   */
  public static Property makeProperty(String name, String value, String form){
    return new StringProperty(name, value, form);
  }

  /**
   * Makes a property from an integer
   *
   * @param name the name of the property
   * @param value the integer value of the property
   * @param form the form of the property
   * @return a new property created from the integer
   */
  public static Property makeProperty(String name, Integer value, String form){
    return new IntegerProperty(name, value, form);
  }

  /**
   * Makes a property from a collection of strings
   *
   * @param name the name of the property
   * @param value the collection of strings to store in the property
   * @param form the form of the property
   * @return a new property created from the collection of strings
   */
  public static Property makeProperty(String name, Collection<String> value, String form){
    return new StringListProperty(name, value, form);
  }

}
