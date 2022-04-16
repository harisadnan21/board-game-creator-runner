package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.move.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser that is responsible for parsing rules. This involves parsing conditions and actions and
 * assigning them to rules accordingly.
 *
 * @author Shaan Gondalia
 */
public class RuleParser extends AbstractParser<Collection<Rule>>{

  private static final String RULES = "rules";
  private static final String REPRESENTATIVE_POINT = "representativePoint";
  public static final String ACTIONS = "actions";
  public static final String CONDITIONS = "conditions";
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
  public Collection<Rule> parse(File configFile) throws FileNotFoundException {
    Collection<Rule> rules = new HashSet<>();
    parseConditionsAndActions(configFile);
    JSONObject root = fileToJSON(configFile);
    JSONArray rulesJSON = root.getJSONArray(RULES);
    for (int i = 0; i < rulesJSON.length(); i++) {
      JSONObject rule = rulesJSON.getJSONObject(i);
      Position repPoint = getRepresentativePoint(rule.getJSONObject(REPRESENTATIVE_POINT));
      Action[] actions = resolveActions(rule);
      PieceCondition[] conditions = resolveConditions(rule);
      rules.add(new Rule(conditions, actions, repPoint.i(), repPoint.j()));
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
  private PieceCondition[] resolveConditions(JSONObject ruleObj) {
    Collection<PieceCondition> conditions = new HashSet<>();
    JSONArray conditionsJSON = ruleObj.getJSONArray(CONDITIONS);
    for (int i = 0; i < conditionsJSON.length(); i++) {
      conditions.add(conditionParser.resolve(conditionsJSON.getString(i)));
    }
    return conditions.toArray(new PieceCondition[0]);
  }

  // Initial parsing for conditions and actions without resolving them
  private void parseConditionsAndActions(File configFile) throws FileNotFoundException {
    actionParser.parse(configFile);
    conditionParser.parse(configFile);
  }

  // Gets a representative point from a JSONObject
  private Position getRepresentativePoint(JSONObject representativePoint) {
    //TODO: Remove magic values and make sure this is correct
    int i = -representativePoint.getInt("y");
    int j = representativePoint.getInt("x");
    return new Position(i, j);
  }

}
