package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.JSONParseable;
import oogasalad.builder.model.element.Element;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;


/**
 * API For creating a game element based on its properties. Provides methods for creating elements
 * and listing required properties.
 *
 * This is class is well-designed as it illustrates the Interface Segregation Principle and provides
 * an API for creating Game Elements. It also allows us to create an Abstract Factory Pattern for
 * creating Game Elements.
 *
 * Commits:
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/55505ff7e5c7f6eb167956c73a3972cc0dc5bc50
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/aa761c5f7e0566063d335ff40994d794ffba2b94
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/77c01f880cc39ff3457d6107f0d138be14f56db5
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/55860a9e82e93c901098a10b9ad5c1fd8b083258
 *
 * @author Shaan Gondalia
 */
public interface ElementFactory extends JSONParseable<Element> {

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

  /**
   * Converts a JSON String into a GameElement
   *
   * @param json the JSON string
   * @return a model made from the JSON string
   */
  Element fromJSON(String json);
}