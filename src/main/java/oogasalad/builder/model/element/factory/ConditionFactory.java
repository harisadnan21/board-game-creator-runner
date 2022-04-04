package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Condition;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;

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
}