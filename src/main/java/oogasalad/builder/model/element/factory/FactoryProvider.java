package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

/**
 * Class that provides a specific GameElementFactory based on the type of the desired game element.
 * Originally was designed to provide Factory Objects, but we realized that the API was small enough
 * for this to actually return GameElements instead of the factories themselves
 *
 * This code is well-designed because it implements the AbstractFactory Pattern. Users should not
 * care about what kind of factory they have to create when creating game elements, they just want
 * to call an API that provides the data that they need. The API is quite small and hard to misuse,
 * as only immutable objects are returned.
 *
 * Commits:
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/769d05070b83ef1894c591ae4f8661113748bdfc
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/579b9b1682d79d9b9b27935ed83ad977189b5603
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/d8429f88d30f720df9ba82000cfcd28c353d98e4
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/d05fab7bce27255638f5c244d9948f687d2c75ee
 *
 * @author Shaan Gondalia
 */
public class FactoryProvider {

  private final PieceFactory pieceFactory;
  private final RuleFactory ruleFactory;
  private final WinConditionFactory winConditionFactory;
  private final ActionFactory actionFactory;
  private final ConditionFactory conditionFactory;
  private final MetaDataFactory metaDataFactory;

  /**
   * Creates a new factory provider
   */
  public FactoryProvider() {
    ruleFactory = new RuleFactory();
    pieceFactory = new PieceFactory();
    winConditionFactory = new WinConditionFactory();
    actionFactory = new ActionFactory();
    conditionFactory = new ConditionFactory();
    metaDataFactory = new MetaDataFactory();
  }

  /**
   * Creates a game element based on the given parameters
   *
   * @param type       the type of the game element
   * @param name       the name of the game element
   * @param properties the properties of the game element
   * @return a game element with the given name and properties
   */
  public GameElement createElement(String type, String name, Collection<Property> properties)
      throws InvalidTypeException, MissingRequiredPropertyException {
    return getFactory(type).createElement(name, properties);
  }

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  public Collection<Property> getRequiredProperties(String type) throws InvalidTypeException {
    return getFactory(type).getRequiredProperties();
  }

  /**
   * Returns a GameElementFactory based on the given type
   *
   * @param type the type of element requested
   * @return a GameElementFactory based on the given type
   */
  private GameElementFactory getFactory(String type) throws InvalidTypeException {
    // TODO: Replace this with reflection
    return switch (type) {
      case "piece" -> pieceFactory;
      case "rule" -> ruleFactory;
      case "winCondition" -> winConditionFactory;
      case "action" -> actionFactory;
      case "condition" -> conditionFactory;
      case "metadata" -> metaDataFactory;
      default -> throw new InvalidTypeException();
    };
  }

  /**
   * Creates a game element from a JSON string
   *
   * @param type the type of the game element
   * @param json the JSON string to make an element from
   * @return a new game element made from the json string
   */
  public GameElement fromJSON(String type, String json) {
    return getFactory(type).fromJSON(json);
  }
}
