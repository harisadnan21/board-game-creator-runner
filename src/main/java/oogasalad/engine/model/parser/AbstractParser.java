package oogasalad.engine.model.parser;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import oogasalad.engine.model.parser.exception.ReferenceNotFoundException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Abstract parser class that contains some common methods for parsing objects
 *
 * @author Shaan Gondalia
 */
public abstract class AbstractParser<T> implements Parser<T> {

  private static final String REFLECTION_DELIMITER = "\\|";

  /**
   * Returns an object that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  public abstract T parse(File configFile) throws FileNotFoundException;


  /**
   * Finds the root of a json configuration file
   *
   * @param configFile the configuration file to convert to a json object
   * @return a JSONObject representing the configuration file
   * @throws FileNotFoundException if configFile is not find
   */
  protected JSONObject fileToJSON(File configFile) throws FileNotFoundException {
    InputStream is = new DataInputStream(new FileInputStream(configFile));
    JSONTokener tokener = new JSONTokener(is);
    return new JSONObject(tokener);
  }

  /**
   * Finds an attribute from a root json object
   *
   * @param root      the root JSON Object to search from
   * @param attribute the key of the attribute to search for
   * @return a JSONObject representing the attribute
   */
  protected JSONObject findAttribute(JSONObject root, String attribute) {
    return root.getJSONObject(attribute);
  }

  /**
   * Returns an object that is found using reflection and a resource bundle
   *
   * @param type The name of the class that will be created using reflection
   * @param parameters An integer array of parameters used to construct the object
   * @return a new object created using reflection
   */
  protected Object getObjectReflection(String type, int[] parameters, ResourceBundle resources) {
    try {
      String className = resources.getString(type).split(REFLECTION_DELIMITER)[0];
      Class clazz = Class.forName(className);
      Constructor ctor = clazz.getConstructor(int[].class);
      return ctor.newInstance(parameters);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      throw new ReferenceNotFoundException(e.getMessage()); // TODO: Handle this properly
    }
  }

}
