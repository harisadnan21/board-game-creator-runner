package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.rule.Move;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser that is responsible for parsing rules. This involves parsing conditions and actions and
 * assigning them to rules accordingly.
 *
 * @author Shaan Gondalia
 */
public class RuleParser extends AbstractParser<Collection<Move>> {

  public static final String ACTIONS = "actions";
  public static final String CONDITIONS = "conditions";
  private static final String RULES = "rules";
  private static final String REPRESENTATIVE_POINT_X = "representativeX"; //?? "representativeX";
  private static final String REPRESENTATIVE_POINT_Y = "representativeY"; //?? "representativeY";
  private static final String NAME = "name";
  private final ActionParser actionParser;
  private final ConditionParser conditionParser;

  /**
   * Creates a new RuleParser
   */
  public RuleParser() {
    actionParser = new ActionParser();
    conditionParser = new ConditionParser();
  }

  /**
   * Returns an object that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public Collection<Move> parse(File configFile) throws FileNotFoundException {
    Collection<Move> rules = new HashSet<>();
    parseConditionsAndActions(configFile);
    JSONObject root = fileToJSON(configFile);
    JSONArray rulesJSON = root.getJSONArray(RULES);
    for (int i = 0; i < rulesJSON.length(); i++) {
      JSONObject rule = rulesJSON.getJSONObject(i);
      String name = rule.getString(NAME);
      Position repPoint = getRepresentativePoint(rule);
      Action[] actions = resolveActions(rule);
      Condition[] conditions = resolveConditions(rule);
      rules.add(new Move(name, conditions, actions, repPoint.i(), repPoint.j()));
    }
    return rules;
  }

  // Resolves all actions in a rule
  private Action[] resolveActions(JSONObject ruleObj) {
    Collection<Action> actions = new HashSet<>();
    JSONArray actionsJSON = ruleObj.getJSONArray(ACTIONS);
    for (int i = 0; i < actionsJSON.length(); i++) {
      actions.add(actionParser.resolve(actionsJSON.getString(i)));
    }
    return actions.toArray(new Action[0]);
  }

  // Resolves all conditions in a rule
  private Condition[] resolveConditions(JSONObject ruleObj) {
    Collection<Condition> conditions = new HashSet<>();
    JSONArray conditionsJSON = ruleObj.getJSONArray(CONDITIONS);
    for (int i = 0; i < conditionsJSON.length(); i++) {
      conditions.add(conditionParser.resolve(conditionsJSON.getString(i)));
    }
    return conditions.toArray(new Condition[0]);
  }

  // Initial parsing for conditions and actions without resolving them
  private void parseConditionsAndActions(File configFile) throws FileNotFoundException {
    actionParser.parse(configFile);
    conditionParser.parse(configFile);
  }

  // Gets a representative point from a JSONObject representing a rule
  private Position getRepresentativePoint(JSONObject rule) {
    int i = -rule.getInt(REPRESENTATIVE_POINT_Y);
    int j = rule.getInt(REPRESENTATIVE_POINT_X);
    return new Position(i, j);
  }

}
