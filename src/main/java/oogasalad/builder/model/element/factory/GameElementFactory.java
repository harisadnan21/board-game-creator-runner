package oogasalad.builder.model.element.factory;

import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;

/**
 * Abstract class that represents a generic Game Element Factory. Has methods for retrieving
 * required parameters and creating game elements based on properties (performing validation
 * when required).
 *
 * @param <T> The type of game element that the factory should return
 * @author Ricky Weerts
 */
public abstract class GameElementFactory<T extends GameElement> implements ElementFactory {

  private static final int PROPERTY_PARTS = 2;
  private final ResourceBundle propertiesResources;
  private Collection<Property> properties;

  /**
   * Creates a new GameElementFactory with the given properties file
   *
   * @param propertiesPath the path to the properties file for the specific game element
   */
  public GameElementFactory(String propertiesPath) {
    propertiesResources = ResourceBundle.getBundle(propertiesPath);
    loadProperties();
  }

  /**
   * Creates a game element based on the given parameters
   *
   * @param name       the name of the game element
   * @param properties the properties of the game element
   * @return a game element with the given name and properties
   */
  public abstract T createElement(String name, Collection<Property> properties);

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  @Override
  public Collection<Property> getRequiredProperties() {
    return Set.copyOf(properties);
  }

  // Loads the required properties based on the resource file provided in the constructor
  private void loadProperties() {
    properties = new HashSet<>();
    propertiesResources.getKeys().asIterator().forEachRemaining(key -> {
          String[] propertyParts = propertiesResources.getString(key).split("\\|");
          if (propertyParts.length != PROPERTY_PARTS) {
            throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance()
                .getString("BadPropertyPartLength", PROPERTY_PARTS));
          }
          try {
            properties.add(
                new Property(Class.forName(propertyParts[0]), key, propertyParts[1]));
          } catch (ClassNotFoundException e) {
            throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance()
                .getString("BadPropertyClass", propertyParts[0]));
          }
        });
  }

}