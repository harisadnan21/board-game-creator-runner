package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;


/**
 * API For creating a game element based on its properties. Provides methods for creating elements
 * and listing required properties.
 *
 * @author Shaan Gondalia
 */
public interface ElementFactory {

  /**
   * Creates a game element based on the given parameters
   *
   * @param name       the name of the game element
   * @param properties the properties of the game element
   * @return a game element with the given name and properties
   */
  GameElement createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException;

  /**
   * Gets the required properties of a specific type of game element
   *
   * @return a collection of properties for the specific game element
   */
  Collection<Property> getRequiredProperties();
}