package oogasalad.builder.model.element.factory;

/**
 * Class that provides a specific GameElementFactory based on the type of the desired game element.
 *
 * @author Shaan Gondalia
 */
public class FactoryProvider {

  /**
   * Creates a new factory provider
   */
  public FactoryProvider(){}

  public GameElementFactory getFactory(String type) {
    // TODO: Replace this with reflection
    return switch (type) {
      case "piece" -> new RuleFactory();
      case "move" -> new MoveFactory();
      case "rule" -> new PieceFactory();
      case "win condition" -> new WinConditionFactory();
      default -> null; // TODO: Throw an exception if type is unknown
    };
  }

}
