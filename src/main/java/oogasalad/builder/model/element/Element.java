package oogasalad.builder.model.element;


/**
 * API for a generic game element.
 *
 * @author Shaan Gondalia
 */
public interface Element {

  /**
   * Returns whether the parameter matches the name of the game element
   *
   * @param desiredName the name to match to
   * @return whether the parameter matches the name of the game element
   */
  boolean checkName(String desiredName);

  /**
   * Returns an immutable record containing the GameElement's data
   * @return an immutable record containing the GameElement's data
   */
  ElementRecord toRecord();

}