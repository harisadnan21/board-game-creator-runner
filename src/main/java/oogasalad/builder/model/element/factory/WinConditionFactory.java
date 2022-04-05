package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.property.GenericProperty;
import oogasalad.builder.model.element.WinCondition;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;

public class WinConditionFactory extends GameElementFactory<WinCondition> {

  public WinConditionFactory() {
    super("elements.WinCondition");
  }

  @Override
  public WinCondition createElement(String name, Collection<GenericProperty> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new WinCondition(name, properties);
  }
}