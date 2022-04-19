package oogasalad.builder.view.property;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import oogasalad.builder.view.callback.CallbackDispatcher;

import java.util.stream.Collectors;

/**
 * Describes generic behavior for the property editing portion of a Game Element Tab. Allows users
 * to input and edit the properties of new and existing game elements.
 *
 * @author Ricky Weerts and Shaan Gondalia
 */
public class PropertyEditor extends VBox {
  private static final String TYPE_PROPERTY_NAME = "type";

  private final CallbackDispatcher callbackDispatcher;

  private final Map<Property, PropertySelector> selectors = new HashMap<>();

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
   * @param properties The rgequired properties of an element
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
    // Remove all non-type properties
    List<Property> nonType = new ArrayList<>();
    selectors.forEach((prop, selector) -> {
      if(!isTypeProperty(prop)) {
        getChildren().remove(selector.display().getParent()); // FIXME Assumes display() result won't change between calls
        nonType.add(prop);
      }
    });
    nonType.forEach(selectors::remove);

    for (Property prop : allProperties) {
      if (prop.name().contains(typeName)) {
        addProperty(prop);
      }
    }
  }

  private boolean isTypeProperty(Property prop) {
    return getLastPropertyNameSegment(prop).equals(TYPE_PROPERTY_NAME);
  }

  private String getLastPropertyNameSegment(Property prop) {
    String[] nameParts = prop.name().split("-");
    return nameParts[nameParts.length - 1];
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
    boolean hasTypeProperty = false;
    for (Property prop : properties) {
      if (isTypeProperty(prop)) {
        addProperty(prop);
        hasTypeProperty = true;
      }
    }
    if (!hasTypeProperty) {
      properties.forEach(this::addProperty);
    }

  }

  /**
   * Gets a property based on its name
   *
   * @param name the name of the property to retrieve
   * @return a property with the value field filled out
   */
  private Property getElementProperty(String name) {
    // TODO Use Property.withValue() if we add that back
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
    propertyBox.getChildren().addAll(
        new Label(propertyNameParts[propertyNameParts.length - 1]),
        propertySelector.display()
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
      throw new InvalidFormException(e.getMessage()); // TODO: Handle this properly
    }
  }

}