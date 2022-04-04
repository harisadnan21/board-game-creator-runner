package oogasalad.builder.model.element.factory;


import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;

public abstract class GameElementFactory<T extends GameElement> implements ElementFactory {

  private static final int PROPERTY_PARTS = 3;
  private final ResourceBundle propertiesResources;
  private Collection<Property> properties;

  public GameElementFactory(String propertiesPath) {
    propertiesResources = ResourceBundle.getBundle(propertiesPath);
    loadProperties();
  }

  private void loadProperties() {
    properties = new HashSet<>();
    propertiesResources.getKeys().asIterator()
        .forEachRemaining(key -> {
          String[] propertyParts = propertiesResources.getString(key).split("\\|");
          if (propertyParts.length != PROPERTY_PARTS) {
            throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance()
                .getString("BadPropertyPartLength", PROPERTY_PARTS));
          }
          try {
            properties.add(
                new Property(Class.forName(propertyParts[0]), propertyParts[1], propertyParts[2]));
          } catch (ClassNotFoundException e) {
            throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance()
                .getString("BadPropertyClass", propertyParts[0]));
          }
        });
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
   * @return
   */
  public Set<Property> getRequiredProperties() {
    return Set.copyOf(properties);
  }

}