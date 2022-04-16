package oogasalad.builder.model;


/**
 * API For adapting Back-End Objects to their JSON Format
 *
 * @author Shaan Gondalia
 */
public interface JSONSerializable {

  /**
   * Converts the object into a String representing the object's JSON Format
   *
   * @return a String representation of the objects JSON Format
   */
  String toJSON();

}