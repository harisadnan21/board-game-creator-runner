package oogasalad.builder.model.element;


import java.util.Collection;
import oogasalad.builder.model.property.GenericProperty;

/**
 * Represents some way that the game can end
 */
public class WinCondition extends GameElement {

  public WinCondition(String name, Collection<GenericProperty> properties) {
    super(name, properties);
  }
}