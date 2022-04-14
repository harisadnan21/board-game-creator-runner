package oogasalad.builder.model;


/**
 * API for converting a json string to an Object
 *
 * @author Shaan Gondalia
 */
public interface JSONParseable<T> {

  /**
   * Converts a json string to an object of type T
   *
   * @param json a json string
   * @return an object of type T built from the json String
   */
  T fromJSON(String json) throws Exception;

}
