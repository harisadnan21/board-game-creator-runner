package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.parser.exception.MissingRequiredPropertyException;
import oogasalad.engine.model.parser.exception.ReferenceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Abstract parser that deals with resolving references to objects. This is needed in the case of
 * actions and conditions, which are referenced in rules by name only. This class was made to reduce
 * the amount of repeated code in the action and condition parsers.
 *
 * @author Shaan Gondalia
 */
public abstract class ReferenceParser<T> extends AbstractParser<Void> {

  private static final Logger LOG = LogManager.getLogger(ReferenceParser.class);

  private static final String REFLECTION_DELIMITER = "\\|";
  private static final String PARAMETER_DELIMITER = "-";

  private final String referenceType;
  private final ResourceBundle referenceResources;
  private final Map<String, Map<String, String>> referenceMap;

  /**
   * Creates a new reference parser with the given type of reference
   *
   * @param type                the type of the reference for the parser
   * @param reflectionResources the resource bundle used to get info about required params and
   *                            reflection class paths
   */
  public ReferenceParser(String type, ResourceBundle reflectionResources) {
    referenceType = type;
    referenceResources = reflectionResources;
    referenceMap = new HashMap<>();
  }

  /**
   * Parses the referenced portion of the configuration file. This saves values into a map that is
   * used later to resolve references when they are referenced in specific rules.
   *
   * @param configFile the configuration file to parse from
   * @return Void
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public Void parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONArray json = root.getJSONArray(referenceType);
    for (int i = 0; i < json.length(); i++) {
      JSONObject obj = json.getJSONObject(i);
      Map<String, String> properties = new HashMap<>();
      for (String key : obj.keySet()) {
        if (!key.equals("name")) {
          properties.put(key, obj.get(key).toString());
        }
      }
      referenceMap.put(obj.getString("name"), properties);
    }
    return null;
  }

  /**
   * Resolves a reference based on the previously loaded referenceMap as well as the name of the
   * reference
   *
   * @param name the name of the reference to resolve
   * @return a new object that was created based on the name that was provided
   */
  public T resolve(String name) {
    String type = findPropertyValue(name, "type");
    int[] params = paramsToIntArray(name, type);
    return getReferenceReflection(type, params);
  }

  // Converts all required parameters (based on type) of a reference to an ordered integer array
  private int[] paramsToIntArray(String name, String type) {
    //TODO: Make this line easier to understand
    String[] requiredParams = referenceResources.getString(type)
        .split(REFLECTION_DELIMITER)[1].split(PARAMETER_DELIMITER);

    LOG.info("Type: {}\n", type);
    LOG.info("Required params {}\n", Arrays.toString(requiredParams));
    int[] params = new int[requiredParams.length];
    for (int i = 0; i < requiredParams.length; i++) {
      //TODO: Implement variables here
      params[i] = Integer.parseInt(findPropertyValue(name, requiredParams[i]));
      //TODO: THIS SUCKS! Standardize how we differentiate (x,y) and (i,j)
      if (requiredParams[i].equals("y")) {
        int temp = params[i - 1];
        params[i - 1] = -params[i];
        params[i] = temp;
      }
    }
    LOG.info("Int params {}\n", Arrays.toString(params));
    return params;
  }

  // Finds the value of a reference's property given its name and the reference's name
  private String findPropertyValue(String name, String propertyName) {
    Map<String, String> reference = referenceMap.get(name);
    if (reference == null) {
      throw new ReferenceNotFoundException();
    }
    if (!reference.containsKey(propertyName)) {
      throw new MissingRequiredPropertyException();
    }
    return reference.get(propertyName);
  }

  // Makes an object of type T using reflection
  private T getReferenceReflection(String type, int[] parameters) {
    try {
      String className = referenceResources.getString(type).split(REFLECTION_DELIMITER)[0];
      Class clazz = Class.forName(className);
      Constructor ctor = clazz.getConstructor(int[].class);
      return (T) ctor.newInstance(parameters);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      throw new ReferenceNotFoundException(e.getMessage()); // TODO: Handle this properly
    }
  }
}
