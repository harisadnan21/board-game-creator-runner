package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import oogasalad.engine.model.logicelement.actions.Action;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;
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

  private static final String RULES = "rules";
  private static final String REPRESENTATIVE_POINT_X = "representativeX";
  private static final String REPRESENTATIVE_POINT_Y = "representativeY";
  private static final String IS_PERSISTENT = "isPersistent";
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
      Action[] actions = actionParser.resolveActions(rule);
      Condition[] conditions = conditionParser.resolveConditions(rule);
      boolean isPersistent = getIsPersistent(rule);
      rules.add(new Move(name, conditions, actions, repPoint, isPersistent));
    }
    return rules;
  }

  private boolean getIsPersistent(JSONObject rule) {
    int isPersistentInt = rule.getInt(IS_PERSISTENT);
    return isPersistentInt == 0 ? false : true;
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
