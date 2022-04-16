package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.builder.model.element.Action;
import oogasalad.builder.model.element.GameElement;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses actions, storing them in a map so that they can be resolved later
 *
 * @author Shaan Gondalia
 */
public class ActionParser extends AbstractParser<Void> {

  public static final String ACTIONS = "actions";
  private Map<String, Map<String, String>> actionMap;

  /**
   * Creates a new action parser
   */
  public ActionParser() {
    actionMap = new HashMap<>();
  }

  /**
   * Parses the actions portion of the configuration file. This saves values into a map that is
   * used later to resolve actions when they are referenced in specific rules.
   *
   * @param configFile the configuration file to parse from
   * @return Void
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public Void parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONArray actionsJSON = root.getJSONArray(ACTIONS);
    for (int i = 0; i < actionsJSON.length(); i++) {
      JSONObject obj = actionsJSON.getJSONObject(i);
      Map<String, String> properties = new HashMap<>();
      for (String key : obj.keySet()) {
        if (!key.equals("name")) {
          properties.put(key, obj.getString(key));
        }
      }
      actionMap.put(obj.getString("name"), properties);
    }
    return null;
  }

  /**
   * Resolves an action based on the previously loaded actionMap as well as the name of the action
   *
   * @param name the name of the action to resolve
   * @return a new action that was created based on the name that was provided
   */
  public Action resolveAction(String name) {
    return null;
  }

}
