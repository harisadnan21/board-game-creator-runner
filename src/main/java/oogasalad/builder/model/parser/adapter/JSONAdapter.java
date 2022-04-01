package oogasalad.builder.model.parser.adapter;


/**
 * API For adapting Back-End Objects to their JSON Format
 *
 * @author Shaan Gondalia
 */
public interface JSONAdapter<T> {

  /**
   * Converts an Object into a String representing the object's JSON Format
   *
   * @param t the object to convert
   * @return a String representation of the objects JSON Format
   */
  public String toJSON(T t);

  /**
   * Converts a JSON String into an object
   *
   * @param json the JSON string
   * @return an object of type T made from the JSON string
   */
  public T fromJSON(String json);

}
