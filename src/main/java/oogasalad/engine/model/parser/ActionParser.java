package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import oogasalad.engine.model.actions.Action;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses actions, storing them in a map so that they can be resolved later
 *
 * @author Shaan Gondalia
 */
public class ActionParser extends AbstractParser<Void> {

  private static final String ACTIONS = "actions";
  private static final String ACTION_RESOURCES_PATH = "engine-resources.Actions";
  private static final ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);
  private static final String REFLECTION_DELIMITER = "\\|";
  private static final String PARAMETER_DELIMITER = "-";

  private final Map<String, Map<String, String>> actionMap;

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
          properties.put(key, obj.get(key).toString());
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
  public Action resolveAction(String name) throws ClassNotFoundException, InvocationTargetException,
      NoSuchMethodException, InstantiationException, IllegalAccessException {
    String actionType = findPropertyValue(name, "type");
    int[] params = paramsToIntArray(name, actionType);
    return getActionReflection(actionType, params);
  }

  // Converts all required parameters (based on type) of an action to an ordered integer array
  private int[] paramsToIntArray(String actionName, String actionType) {
    //TODO: Make this line easier to understand
    String[] requiredParams = ACTION_RESOURCES.getString(actionType).split(REFLECTION_DELIMITER)[1].split(PARAMETER_DELIMITER);
    int[] params = new int[requiredParams.length];
    for (int i = 0; i < requiredParams.length; i++) {
      //TODO: Implement variables here
      params[i] = Integer.parseInt(findPropertyValue(actionName, requiredParams[i]));
    }
    return params;
  }

  // Finds the value of an action's property given its name and the action's name
  private String findPropertyValue(String actionName, String propertyName) {
    return actionMap.get(actionName).get(propertyName);
  }

  // Makes an action using reflection
  private Action getActionReflection(String type, int[] parameters)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String className = ACTION_RESOURCES.getString(type).split(REFLECTION_DELIMITER)[0];
    Class clazz = Class.forName(className);
    Constructor ctor = clazz.getConstructor(int[].class);
    return (Action) ctor.newInstance(parameters);
  }

}
