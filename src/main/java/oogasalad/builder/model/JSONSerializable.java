package oogasalad.builder.model;


/**
 * API For adapting Back-End Objects to their JSON Format
 *
 * @author Shaan Gondalia
 */
public interface JSONSerializable<T> {

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
  T fromJSON(String json);

}
