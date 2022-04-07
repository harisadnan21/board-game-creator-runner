package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.Property;

/**
 * Represents an Action in the game. Examples of actions are move, capturePieceAt, place, etc.
 * Actions are attached to rules to dictate what happens in the case of the conditions being met
 * for a move.
 *
 * @author Shaan Gondalia
 */
public class Action extends GameElement{

  /**
   * Creates a new Action with the given parameters.
   *
   * @param name       the name of the game element
   * @param properties the properties of the game element
   */
  public Action(String name, Collection<Property> properties) {
    super(name, properties);
  }
}
