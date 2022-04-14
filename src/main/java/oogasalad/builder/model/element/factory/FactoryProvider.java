package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;

/**
 * Class that provides a specific GameElementFactory based on the type of the desired game element.
 *
 * @author Shaan Gondalia
 */
public class FactoryProvider {

  private final PieceFactory pieceFactory;
  private final RuleFactory ruleFactory;
  private final WinConditionFactory winConditionFactory;
  private final ActionFactory actionFactory;
  private final ConditionFactory conditionFactory;

  /**
   * Creates a new factory provider
   */
  public FactoryProvider() {
    ruleFactory = new RuleFactory();
    pieceFactory = new PieceFactory();
    winConditionFactory = new WinConditionFactory();
    actionFactory = new ActionFactory();
    conditionFactory = new ConditionFactory();
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
      case "win condition" -> winConditionFactory;
      case "action" -> actionFactory;
      case "condition" -> conditionFactory;
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
