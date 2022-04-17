package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.Action;
import oogasalad.builder.model.element.MetaData;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

/**
 * Factory responsible for creating Action objects based on name and properties. Performs validation
 * on input to make sure all required properties are present.
 *
 * @author Shaan Gondalia
 */
public class MetaDataFactory extends GameElementFactory<MetaData> {

  /**
   * Creates a new ActionFactory. See elements.Action properties file for the required properties of
   * an Action.
   */
  public MetaDataFactory() {
    super("elements.MetaData");
  }

  /**
   * Returns an MetaData object based on the given properties
   *
   * @param name       the name of the game
   * @param properties the metadata properties of the game
   * @return a MetaData object created from the given parameters
   * @throws MissingRequiredPropertyException if the required parameters are not found
   */
  @Override
  public MetaData createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new MetaData(name, properties);
  }

  /**
   * Creates a new metadata object from a JSON string
   *
   * @param json the JSON string
   * @return a new metadata object made from a JSON string
   */
  @Override
  public MetaData fromJSON(String json) {
    Collection<Property> properties = propertiesFromJSON(json);
    String name = nameFromJSON(json);
    return new MetaData(name, properties);
  }
}