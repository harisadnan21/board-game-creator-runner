package oogasalad.engine.model.parser;

import java.util.ResourceBundle;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Parses conditions, storing them in a map so that they can be resolved later
 *
 * @author Shaan Gondalia
 */
public class ConditionParser extends ReferenceParser<Condition> {

  private static final String CONDITIONS = "conditions";
  private static final String CONDITION_RESOURCES_PATH = "engine-resources.Conditions";
  private static final ResourceBundle CONDITION_RESOURCES = ResourceBundle.getBundle(
      CONDITION_RESOURCES_PATH);

  /**
   * Creates a new condition parser
   */
  public ConditionParser() {
    super(CONDITIONS, CONDITION_RESOURCES);
  }
}
