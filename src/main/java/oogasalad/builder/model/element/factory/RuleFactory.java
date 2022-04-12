package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.Rule;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

public class RuleFactory extends GameElementFactory<Rule> {

  public RuleFactory() {
    super("elements.Rule");
  }

  @Override
  public Rule createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new Rule(name, properties);
  }
}