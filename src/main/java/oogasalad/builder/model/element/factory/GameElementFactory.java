package oogasalad.builder.model.element.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract class that represents a generic Game Element Factory. Has methods for retrieving
 * required parameters and creating game elements based on properties (performing validation when
 * required).
 *
 * This class is well-designed as it contains common methods that all Game Element Factories
 * require. By creating this abstract class, we are able to reduce the amount of repeated code
 * that is in each concrete factory class. It also has short, well-named methods that make
 * the code easier to understand.
 *
 * Commits:
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/55505ff7e5c7f6eb167956c73a3972cc0dc5bc50
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/aa761c5f7e0566063d335ff40994d794ffba2b94
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/4d44e32370a04e89292e0720148a41f97aebb21c
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/620acec1f4ae34ea08035b55de4300d6320b71dd
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/46f25462dbc7bc9d8b42ba9a447b62d7b2e68cda
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/55860a9e82e93c901098a10b9ad5c1fd8b083258
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/e68d1c81fbdb0b5bd292662369356cc7de492d9f
 *
 * @param <T> The type of game element that the factory should return
 * @author Ricky Weerts & Shaan Gondalia
 */
public abstract class GameElementFactory<T extends GameElement> implements ElementFactory {

  private static final int PROPERTY_PARTS = 3;
  private static final String DELIMITER = "-";
  private static final String LIST_DELIMITER = ",";
  private static final String REQUIRED = "required";
  private static final String NAME = "name";
  private static final String TYPE = "type";
  private static final Logger LOG = LogManager.getLogger(GameElementFactory.class);
  private final ResourceBundle propertiesResources;
  private Collection<Property> properties;
  private final Map<Property, String> propertyTypes;

  /**
   * Creates a new GameElementFactory with the given properties file
   *
   * @param propertiesPath the path to the properties file for the specific game element
   */
  public GameElementFactory(String propertiesPath) {
    propertiesResources = ResourceBundle.getBundle(propertiesPath);
    propertyTypes = new HashMap<>();
    loadProperties();
  }

  /**
   * Creates a game element based on the given parameters
   *
   * @param name       the name of the game element
   * @param properties the properties of the game element
   * @return a game element with the given name and properties
   */
  public abstract T createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException;

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  @Override
  public Collection<Property> getRequiredProperties() {
    return Set.copyOf(properties);
  }

  /**
   * Converts a JSON String into a GameElement
   *
   * @param json the JSON string
   * @return a model made from the JSON string
   */
  public abstract T fromJSON(String json);

  // Gets the properties of a game element from a json string
  protected Collection<Property> propertiesFromJSON(String json) {
    JSONObject obj = new JSONObject(json);
    Collection<Property> properties = new HashSet<>();

    for (String key : obj.keySet()) {
      for (Property property : propertyTypes.keySet()) {
        if (property.name().split(DELIMITER)[1].equals(key)) {
          String classPath = propertyTypes.get(property);
          String value = convertToString(obj.get(key).toString());
          properties.add(
              makePropertyReflection(key, classPath, value, property.form()));
          break;
        }
      }
    }

    return properties;
  }

  // Attempts to convert an array to the proper string representation. Consider refactoring
  private String convertToString(String orig) {
    try{
      JSONArray arr = new JSONArray(orig);
      StringBuilder s = new StringBuilder();
      for (int i = 0; i < arr.length(); i++) {
        s.append(arr.getString(i));
        s.append(LIST_DELIMITER);
      }
      return s.toString();
    } catch (JSONException e) {
      LOG.info(e);
      return orig;
    }
  }


  // Gets the name of a game element from a json string
  protected String nameFromJSON(String json) {
    JSONObject obj = new JSONObject(json);
    for (String key : obj.keySet()) {
      if (key.equals(NAME)) {
        return obj.getString(key);
      }
    }
    return null; // Perhaps throw an exception
  }

  /**
   * Checks whether a property with the given name exists, returning the value if found
   *
   * @param target     the target name for the property
   * @param properties the properties passed during element creation
   * @return the value of the property if found
   * @throws MissingRequiredPropertyException if the property is not found
   */
  private String findProperty(String target, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    for (Property property : properties) {
      if (property.name().equals(target)) {
        return property.valueAsString();
      }
    }
    throw new MissingRequiredPropertyException(target);
  }

  // Validates that the given properties are correct
  protected void validate(Collection<Property> properties) throws MissingRequiredPropertyException {
    String type = null;
    for (Property property : getRequiredProperties()) {
      String namespace = property.name().split(DELIMITER)[0];
      String target = property.name().split(DELIMITER)[1];
      if (namespace.equals(REQUIRED)) {
        String value = findProperty(target, properties);
        if (target.equals(TYPE)) {
          type = value;
        }
      }
    }
    if (!validateType(type)) {
      throw new MissingRequiredPropertyException(type);
    }
    validateNamespace(type, properties);
  }

  // Validates properties in a given namespace
  private void validateNamespace(String type, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    for (Property property : getRequiredProperties()) {
      String namespace = property.name().split(DELIMITER)[0];
      String target = property.name().split(DELIMITER)[1];
      if (namespace.equals(type)) {
        findProperty(target, properties);
      }
    }
  }

  // Checks whether a game element's type is valid
  private boolean validateType(String type) {
    if (type == null) {
      return true;
    }
    return Arrays.asList(getRequiredProperties().stream()
            .filter(prop -> prop.shortName().equals(TYPE) && prop.name().split(DELIMITER)[0].equals(REQUIRED))
            .findFirst().orElseThrow()
            .valueAsString().split(DELIMITER)).contains(type);

  }

  // Loads the required properties based on the resource file provided in the constructor
  private void loadProperties() {
    properties = new HashSet<>();
    propertiesResources.getKeys().asIterator().forEachRemaining(key -> {
      String[] propertyParts = propertiesResources.getString(key).split("\\|");
      if (propertyParts.length != PROPERTY_PARTS) {
        throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance()
            .getString("BadPropertyPartLength", PROPERTY_PARTS));
      }
      Property property = makePropertyReflection(key, propertyParts[1], propertyParts[2],
          propertyParts[0]);
      properties.add(property);
      propertyTypes.put(property, propertyParts[1]);
    });
  }

  // Uses reflection to create a new property with the correct type
  private Property makePropertyReflection(String name, String className, String value,
      String form) {
    try {
      Class<?> clss = Class.forName(className);
      Constructor<?> ctor = clss.getDeclaredConstructor(String.class, String.class, String.class);
      return (Property) ctor.newInstance(name, value, form);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      throw new InvalidFormException(e);
    }
  }

}