package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.WinCondition;

public class WinConditionFactory extends GameElementFactory<WinCondition> {

  public WinConditionFactory() {
    super("elements.WinCondition");
  }

  @Override
  public WinCondition createElement(String name, Collection<Property> properties) {
    return new WinCondition(name, properties);
  }
}