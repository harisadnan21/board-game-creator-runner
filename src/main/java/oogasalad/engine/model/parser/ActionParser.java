package oogasalad.engine.model.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import oogasalad.engine.model.actions.Action;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses actions, storing them in a map so that they can be resolved later
 *
 * @author Shaan Gondalia
 */
public class ActionParser extends ReferenceParser<Action> {

  private static final String ACTIONS = "actions";
  private static final String ACTION_RESOURCES_PATH = "engine-resources.Actions";
  private static final ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(
      ACTION_RESOURCES_PATH);

  /**
   * Creates a new action parser
   */
  public ActionParser() {
    super(ACTIONS, ACTION_RESOURCES);
  }

  /**
   * Resolved all actions in an object
   *
   * @param obj the object to resolve the actions of
   * @return an array of actions that have been resolved
   */
  public Action[] resolveActions(JSONObject obj) {
    Collection<Action> actions = new HashSet<>();
    JSONArray actionsJSON = obj.getJSONArray(ACTIONS);
    for (int i = 0; i < actionsJSON.length(); i++) {
      actions.add(resolve(actionsJSON.getString(i)));
    }
    return actions.toArray(new Action[0]);
  }
}
