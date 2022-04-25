package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Describes generic behavior for the property editing portion of a Game Element Tab. Allows users
 * to input and edit the properties of new and existing game elements.
 *
 * @author Ricky Weerts and Shaan Gondalia & Mike Keohane
 */
public class PropertyEditor extends VBox {

  private final CallbackDispatcher callbackDispatcher;

  private final Map<Property, PropertySelector> selectors = new HashMap<>();
  private final Map<Property, Node> selectorNodes = new HashMap<>();

  private Collection<Property> allProperties;

  private final PropertyNameAnalyzer propertyNameAnalyzer = new PropertyNameAnalyzer();

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
      if(!propertyNameAnalyzer.isTypeProperty(prop) && !propertyNameAnalyzer.isRequiredProperty(prop)) {
        getChildren().remove(selectorNodes.get(prop).getParent());
        selectorNodes.remove(prop);
        nonType.add(prop);
      }
    });
    nonType.forEach(selectors::remove);

    for (Property prop : allProperties) {
      if (propertyNameAnalyzer.getPropertyNamespace(prop).equals(typeName)) {
        addProperty(prop);
      }
    }
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
      if (propertyNameAnalyzer.isTypeProperty(prop) || propertyNameAnalyzer.isRequiredProperty(prop)) {
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
    if (propertyNameAnalyzer.isTypeProperty(property)) {
      propertySelector.addListener(
          (observable, oldValue, newValue) -> setCorrespondingElementProperties(
              (String) newValue));
    }

    selectorNodes.put(property, propertySelector.display());
    propertyBox.getChildren().addAll(
        new Label(getAndCatchResource(property.shortName())),
        selectorNodes.get(property)
    );
    getChildren().add(propertyBox);
  }

  private String getAndCatchResource(String key){
    try{
      return ViewResourcesSingleton.getInstance().getString(key);
    } catch (Exception e) {
      LogManager.getLogger().log(Level.ERROR, e.getMessage());
    }
    return key;
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