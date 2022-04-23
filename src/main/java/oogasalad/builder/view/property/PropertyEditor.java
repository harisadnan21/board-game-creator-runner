package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Describes generic behavior for the property editing portion of a Game Element Tab. Allows users
 * to input and edit the properties of new and existing game elements.
 *
 * @author Ricky Weerts and Shaan Gondalia & Mike Keohane
 */
public class PropertyEditor extends VBox {

  private static final String TYPE_PROPERTY_NAME = "type";
  private static final String REQUIRED = "required";
  private static final String DELIMITER = "-";

  private final CallbackDispatcher callbackDispatcher;

  private final Map<Property, PropertySelector> selectors = new HashMap<>();
  private final Map<Property, Node> selectorNodes = new HashMap<>();

  private Collection<Property> allProperties;

  /**
   * Creates a new PropertyEditor.
   */
  public PropertyEditor(CallbackDispatcher dispatcher) {
    this.callbackDispatcher = dispatcher;
  }

  /**
   * Sets the properties of an element to display to the user
   *
   * @param properties The required properties of an element
   */
  public void setElementProperties(Collection<Property> properties) {
    getChildren().clear();
    selectors.clear();
    properties.forEach(this::addProperty);
  }

  /**
   * Creates all the elements that correspond to a specified type.
   *
   * @param typeName
   */
  public void setCorrespondingElementProperties(String typeName) {
    // Remove all non-type and non-required properties
    List<Property> nonType = new ArrayList<>();
    selectors.forEach((prop, selector) -> {
      if(!(isTypeProperty(prop) || isRequiredProperty(prop)) ) {
        getChildren().remove(selectorNodes.get(prop).getParent());
        selectorNodes.remove(prop);
        nonType.add(prop);
      }
    });
    nonType.forEach(selectors::remove);

    for (Property prop : allProperties) {
      if (getPropertyNamespace(prop).equals(typeName)) {
        addProperty(prop);
      }
    }
  }

  // Returns whether the property is a type property
  private boolean isTypeProperty(Property prop) {
    return getLastPropertyNameSegment(prop).equals(TYPE_PROPERTY_NAME);
  }

  // Returns true if the property is in the required namespace
  private boolean isRequiredProperty(Property prop) {
    return prop.name().split(DELIMITER)[0].equals(REQUIRED);
  }

  // Gets the last name of a property, disregarding namespace
  private String getLastPropertyNameSegment(Property prop) {
    String[] nameParts = prop.name().split(DELIMITER);
    return nameParts[nameParts.length - 1];
  }

  private String getPropertyNamespace(Property prop) {
    String[] nameParts = prop.name().split(DELIMITER);
    StringBuilder namespace = new StringBuilder();
    // Ignore the required indicator and the actual name of the property
    for(int i = isRequiredProperty(prop) ? 1 : 0; i < nameParts.length - 1; i++) {
      namespace.append(nameParts[i]).append(DELIMITER);
    }

    // Cutoff the last -
    return namespace.length() == 0 ? "" : namespace.substring(0, namespace.length() - 1);
  }

  /**
   * Given a Collection of all the properties, finds the type-selector and makes the corresponding
   * element for it
   *
   * @param properties
   */
  public void setElementPropertyTypeChoice(Collection<Property> properties) {
    getChildren().clear();
    allProperties = properties;
    selectors.clear();
    for (Property prop : properties) {
      if (isTypeProperty(prop) || isRequiredProperty(prop)) {
        addProperty(prop);
      }
    }
  }

  /**
   * Gets a property based on its name
   *
   * @param name the name of the property to retrieve
   * @return a property with the value field filled out
   */
  private Property getElementProperty(String name) {
    return selectors.entrySet().stream()
        .filter(entry -> entry.getKey().name().equals(name))
        .map(entry -> entry.getValue().getProperty())
        .findFirst()
        .orElseThrow();
  }

  /**
   * Gets a Collection of all properties for an element after the user has entered them.
   *
   * @return a collection of properties that have been entered by the user
   */
  public Collection<Property> getElementProperties() {
    return selectors.keySet().stream()
        .map(prop -> getElementProperty(prop.name()))
        .collect(Collectors.toList());
  }

  // Adds a property to the display, using the form of the property
  private void addProperty(Property property) {
    HBox propertyBox = new HBox();
    PropertySelector propertySelector = makePropertySelector(property);
    selectors.put(property, propertySelector);
    if (isTypeProperty(property)) {
      propertySelector.addListener(
          (observable, oldValue, newValue) -> setCorrespondingElementProperties(
              (String) newValue));
    }
    String[] propertyNameParts = property.name().split("-");
    selectorNodes.put(property, propertySelector.display());
    propertyBox.getChildren().addAll(
        new Label(propertyNameParts[propertyNameParts.length - 1]),
        selectorNodes.get(property)
    );
    getChildren().add(propertyBox);
  }

  // Makes a PropertySelector Using reflection, based on the form of the required property
  private PropertySelector makePropertySelector(Property property) {
    try {
      String className = property.form();
      Class<?> clss = Class.forName(className);
      Constructor<?> ctor = clss.getDeclaredConstructor(Property.class, CallbackDispatcher.class);
      return (PropertySelector) ctor.newInstance(property, callbackDispatcher);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InvalidFormException(e.getMessage());
    }
  }

}