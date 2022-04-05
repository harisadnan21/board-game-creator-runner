package oogasalad.builder.model.element;


import java.util.Collection;
import oogasalad.builder.model.property.Property;

/**
 * Represents some way that the game can end
 */
public class WinCondition extends GameElement {

  public WinCondition(String name, Collection<Property> properties) {
    super(name, properties);
  }
}