package oogasalad.engine.model.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import oogasalad.engine.model.conditions.Condition;
import org.json.JSONArray;
import org.json.JSONObject;

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

  /**
   * Resolves all conditions in an object
   *
   * @param obj the object to resolve the conditions of
   * @return an array of conditions
   */
  public Condition[] resolveConditions(JSONObject obj) {
    Collection<Condition> conditions = new HashSet<>();
    JSONArray conditionsJSON = obj.getJSONArray(CONDITIONS);
    for (int i = 0; i < conditionsJSON.length(); i++) {
      conditions.add(resolve(conditionsJSON.getString(i)));
    }
    return conditions.toArray(new Condition[0]);
  }
}
