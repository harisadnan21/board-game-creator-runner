package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;

/**
 * Class that provides a specific GameElementFactory based on the type of the desired game element.
 *
 * @author Shaan Gondalia
 */
public class FactoryProvider {

  final PieceFactory pieceFactory;
  final RuleFactory ruleFactory;
  final WinConditionFactory winConditionFactory;
  final ActionFactory actionFactory;
  final ConditionFactory conditionFactory;

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
  public GameElement createElement(String type, String name, Collection<Property> properties){
    return getFactory(type).createElement(name, properties);
  }

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  public Collection<Property> getRequiredProperties(String type) {
    return getFactory(type).getRequiredProperties();
  }

  /**
   * Returns a GameElementFactory based on the given type
   *
   * @param type the type of element requested
   * @return a GameElementFactory based on the given type
   */
  private GameElementFactory getFactory(String type) {
    // TODO: Replace this with reflection
    return switch (type) {
      case "piece" -> pieceFactory;
      case "rule" -> ruleFactory;
      case "win condition" -> winConditionFactory;
      case "action" -> actionFactory;
      case "condition" -> conditionFactory;
      default -> null; // TODO: Throw an exception if type is unknown
    };
  }

}
