package oogasalad.builder.model.element.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

/**
 * Abstract class that represents a generic Game Element Factory. Has methods for retrieving
 * required parameters and creating game elements based on properties (performing validation
 * when required).
 *
 * @param <T> The type of game element that the factory should return
 * @author Ricky Weerts
 */
public abstract class GameElementFactory<T extends GameElement> implements ElementFactory {

  private static final int PROPERTY_PARTS = 3;
  private static final String DELIMITER = "-";
  private static final String REQUIRED = "required";
  private static final String TYPE = "type";
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
  public abstract T createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException;

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  @Override
  public Collection<Property> getRequiredProperties() {
    return Set.copyOf(properties);
  }

  /**
   * Checks whether a property with the given name exists, returning the value if found
   *
   * @param target the target name for the property
   * @param properties the properties passed during element creation
   * @return the value of the property if found
   * @throws MissingRequiredPropertyException if the property is not found
   */
  private String findProperty(String target, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    for (Property property : properties) {
      if (property.name().equals(target)) {
        return property.valueAsString();
      }
    }
    throw new MissingRequiredPropertyException();
  }

  // Validates that the given properties are correct
  protected void validate(Collection<Property> properties) throws MissingRequiredPropertyException {
    String type = null;
    for (Property property : getRequiredProperties()) {
      String namespace = property.name().split(DELIMITER)[0];
      String target = property.name().split(DELIMITER)[1];
      if (namespace.equals(REQUIRED)) {
        String value = findProperty(target, properties);
        if (target.equals(TYPE)) {
          type = value;
        }
      }
    }
    if (!validateType(type)) {
      throw new MissingRequiredPropertyException();
    }
    validateNamespace(type, properties);
  }

  // Validates properties in a given namespace
  private void validateNamespace(String type, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    for (Property property : getRequiredProperties()) {
      String namespace = property.name().split(DELIMITER)[0];
      String target = property.name().split(DELIMITER)[1];
      if (namespace.equals(type)) {
        findProperty(target, properties);
      }
    }
  }

  // Checks whether a game element's type is valid
  private boolean validateType(String type) {
    if (type == null) {
      return true;
    }
    for (Property property : getRequiredProperties()) {
      String namespace = property.name().split(DELIMITER)[0];
      String target = property.name().split(DELIMITER)[1];
      if (namespace.equals(REQUIRED) && target.equals(TYPE)) {
        String[] validTypes = property.valueAsString().split(DELIMITER);
        return Arrays.asList(validTypes).contains(type);
      }
    }
    return false; // TODO: Remove unreachable statement by making code smarter
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
      properties.add(makePropertyReflection(key, propertyParts));
    });
  }

  // Uses reflection to create a new property with the correct type
  private Property makePropertyReflection(String name, String[] propertyParts) {
    try {
      String className = propertyParts[1];
      Class<?> clss = Class.forName(className);
      Constructor<?> ctor = clss.getDeclaredConstructor(String.class, String.class, String.class);
      return (Property) ctor.newInstance(name, propertyParts[2], propertyParts[0]);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      throw new InvalidFormException(e.getMessage()); // TODO: Handle this properly
    }
  }

}