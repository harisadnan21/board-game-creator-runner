package oogasalad.builder.model.property;

import java.util.Objects;
import org.json.JSONObject;

/**
 * Immutable record that represents a String Property
 *
 * @author Shaan Gondalia
 */
public record StringProperty(String name, String value) implements Property {

  /**
   * Checks equality between properties (same name and value)
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */
  @Override
  public boolean equals(Object o){
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StringProperty property = (StringProperty) o;
    return name.equals(property.name) && value.equals(property.value);
  }

  /**
   * Hashes a property
   *
   * @return the hashcode of the property
   */
  @Override
  public int hashCode(){
    return Objects.hash(name, value);
  }

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  @Override
  public String toString() {
    return value;
  }

  /**
   * Converts the property into a String representing the object's JSON Format
   *
   * @return a String representation of the property's JSON Format
   */
  public String toJSON() throws Exception{
    JSONObject obj = new JSONObject();
    obj.put(name, value);
    return obj.toString();
  }

  /**
   * Converts a JSON String into a property
   *
   * @param json the JSON string
   * @return a property made from the JSON string
   */
  public Property fromJSON(String json){
    //TODO: Implement JSON Loading
    return null;
  }
}
