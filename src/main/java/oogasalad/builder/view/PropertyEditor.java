package oogasalad.builder.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;

import java.util.stream.Collectors;
import oogasalad.builder.view.property.PropertySelector;

/**
 * Describes generic behavior for the property editing portion of a Game Element Tab. Allows users
 * to input and edit the properties of new and existing game elements.
 *
 * @author Ricky Weerts and Shaan Gondalia
 */
public class PropertyEditor extends VBox {

    private final Map<Property, PropertySelector> selectors = new HashMap<>();

    /**
     * Creates a new PropertyEditor.
     */
    public PropertyEditor() {
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
     * Gets a property based on its name
     *
     * @param name the name of the property to retrieve
     * @return a property with the value field filled out
     */
    private Property getElementProperty(String name) {
        // TODO Use Property.withValue() if we add that back
        return selectors.entrySet().stream()
                .filter(entry -> entry.getKey().name().equals(name))
                .map(entry -> {
                    String[] propertyNameParts = entry.getKey().name().split("-");
                    return PropertyFactory.makeProperty(propertyNameParts[propertyNameParts.length - 1], entry.getValue().getPropertyValue(), entry.getKey().form());
                })
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
            Constructor<?> ctor = clss.getDeclaredConstructor(Property.class);
            return (PropertySelector) ctor.newInstance(property);
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
            InstantiationException | IllegalAccessException e) {
            throw new InvalidFormException(e.getMessage()); // TODO: Handle this properly
        }
    }

}