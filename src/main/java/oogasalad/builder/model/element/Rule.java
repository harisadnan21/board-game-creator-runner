package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.model.property.GenericProperty;

/**
 * Represents a rule governing how the game works
 */
public class Rule extends GameElement {

  public Rule(String name, Collection<GenericProperty> properties) {
    super(name, properties);
  }
}