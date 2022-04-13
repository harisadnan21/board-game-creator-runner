package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.Condition;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

/**
 * Factory responsible for creating Condition objects based on name and properties. Performs
 * validation on input to make sure all required properties are present.
 *
 * @author Shaan Gondalia
 */
public class ConditionFactory extends GameElementFactory<Condition> {

  /**
   * Creates a new ConditionFactory. See elements.Condition properties file for the required
   * properties of a Condition.
   */
  public ConditionFactory() {
    super("elements.Condition");
  }

  /**
   * Returns a Condition object based on the given properties
   *
   * @param name       the name of the condition
   * @param properties the properties of the condition
   * @return a Condition object created from the given parameters
   * @throws MissingRequiredPropertyException if the required parameters are not found
   */
  @Override
  public Condition createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new Condition(name, properties);
  }

  /**
   * Creates a new condition from a JSON string
   *
   * @param json the JSON string
   * @return a new condition made from a JSON string
   */
  @Override
  public Condition fromJSON(String json) {
    Collection<Property> properties = propertiesFromJSON(json);
    String name = nameFromJSON(json);
    return new Condition(name, properties);
  }
}