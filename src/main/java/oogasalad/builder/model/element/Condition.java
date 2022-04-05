package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.GenericProperty;

/**
 * Represents a Condition in the game. Examples of conditions are emptyAt, pieceAt, etc.
 * Conditions are attached to rules (along with actions), to dictate if a move is valid and an
 * action is completed.
 *
 * @author Shaan Gondalia
 */
public class Condition extends GameElement{

  /**
   * Creates a new Condition with the given parameters.
   *
   * @param name       the name of the game element
   * @param properties the properties of the game element
   */
  public Condition(String name, Collection<GenericProperty> properties) {
    super(name, properties);
  }
}
