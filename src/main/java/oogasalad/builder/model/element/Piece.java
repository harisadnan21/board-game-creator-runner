package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.GenericProperty;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {

  public Piece(String name, Collection<GenericProperty> properties) {
    super(name, properties);
  }

}