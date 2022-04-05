package oogasalad.builder.model.property;

import java.util.Objects;
import oogasalad.builder.model.JSONSerializable;

/**
 * Interface for any Generic Property. Properties have a name, and value
 *
 * @author Shaan Gondalia
 */
public interface Property extends JSONSerializable<Property> {

  /**
   * Checks equality between properties (same name and value)
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */
  @Override
  boolean equals(Object o);

  /**
   * Hashes a property
   *
   * @return the hashcode of the property
   */
  @Override
  int hashCode();

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  @Override
  String toString();

  /**
   * Converts the object into a String representing the object's JSON Format
   *
   * @return a String representation of the objects JSON Format
   */
  String toJSON() throws Exception;

  /**
   * Converts a JSON String into an object
   *
   * @param json the JSON string
   * @return an object of type T made from the JSON string
   */
  Property fromJSON(String json);
}
