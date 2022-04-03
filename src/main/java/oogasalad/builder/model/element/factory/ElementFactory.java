package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;


/**
 * API For creating a game element based on its properties.
 *
 * @Author Shaan Gondalia
 */
public interface ElementFactory {

  /**
   * Creates a game element based on the given parameters
   *
   * @param name the name of the game element
   * @param properties the properties of the game element
   * @return a game element with the given name and properties
   */
  GameElement createElement(String name, Collection<Property> properties);
}