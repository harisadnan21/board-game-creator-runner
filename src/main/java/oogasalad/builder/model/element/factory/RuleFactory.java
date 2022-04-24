package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.Rule;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

public class RuleFactory extends GameElementFactory<Rule> {

  public RuleFactory() {
    super("builder.elements.Rule");
  }

  @Override
  public Rule createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new Rule(name, properties);
  }

  /**
   * Creates a new rule from a JSON string
   *
   * @param json the JSON string
   * @return a new rule made from a JSON string
   */
  @Override
  public Rule fromJSON(String json) {
    Collection<Property> properties = propertiesFromJSON(json);
    String name = nameFromJSON(json);
    return new Rule(name, properties);
  }
}