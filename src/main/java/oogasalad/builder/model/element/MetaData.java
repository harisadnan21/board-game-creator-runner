package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.Property;

/**
 * Holds the MetaData of a game configuration. This includes the name of the game, author, and
 * description.
 *
 * @author Shaan Gondalia
 */
public class MetaData extends GameElement {

  /**
   * Creates a new MetaData object with the given properties
   *
   * @param name       the name of the game
   * @param properties the metadata properties of the game element
   */
  public MetaData(String name, Collection<Property> properties) {
    super(name, properties);
  }
}
