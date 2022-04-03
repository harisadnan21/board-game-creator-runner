package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.Property;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {

  public Piece(String name, Collection<Property> properties) {
    super(name, properties);
  }

}