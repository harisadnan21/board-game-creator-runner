package oogasalad.engine.model.parser;

import java.util.ResourceBundle;
import oogasalad.engine.model.actions.Action;

/**
 * Parses actions, storing them in a map so that they can be resolved later
 *
 * @author Shaan Gondalia
 */
public class ActionParser extends ReferenceParser<Action> {

  private static final String ACTIONS = "actions";
  private static final String ACTION_RESOURCES_PATH = "engine-resources.Actions";
  private static final ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);

  /**
   * Creates a new action parser
   */
  public ActionParser() {
    super(ACTIONS, ACTION_RESOURCES);
  }
}
