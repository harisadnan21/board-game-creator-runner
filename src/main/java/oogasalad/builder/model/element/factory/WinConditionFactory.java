package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.WinCondition;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

public class WinConditionFactory extends GameElementFactory<WinCondition> {

  public WinConditionFactory() {
    super("elements.WinCondition");
  }

  @Override
  public WinCondition createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new WinCondition(name, properties);
  }

  /**
   * Creates a new win condition from a JSON string
   *
   * @param json the JSON string
   * @return a new win condition made from a JSON string
   */
  @Override
  public WinCondition fromJSON(String json) {
    Collection<Property> properties = propertiesFromJSON(json);
    String name = nameFromJSON(json);
    return new WinCondition(name, properties);
  }
}