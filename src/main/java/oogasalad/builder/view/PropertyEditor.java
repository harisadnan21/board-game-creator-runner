package oogasalad.builder.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.property.Property;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 */
public class PropertyEditor extends VBox {

    private final Map<Property, TextField> fields = new HashMap<>();
    private boolean hasProperties = false;

    public PropertyEditor() {
    }

    public boolean hasProperties() {
        return hasProperties;
    }

    private void setup(Collection<Property> properties) {
        getChildren().clear();
        fields.clear();
        properties.forEach(this::addProperty);
    }

    private void addProperty(Property property) {
        HBox propertyBox = new HBox();
        TextField valueField = new TextField(property.value());
        fields.put(property, valueField);

        String[] propertyNameParts = property.name().split("-");
        propertyBox.getChildren().addAll(
                new Label(propertyNameParts[propertyNameParts.length - 1]),
                valueField
        );
        getChildren().add(propertyBox);
    }

    /**
     * @param properties 
     * @return
     */
    public void setElementProperties(Collection<Property> properties) {
        hasProperties = true;
        setup(properties);
    }

    /**
     * @param name 
     * @return
     */
    public Property getElementProperty(String name) {
        // TODO Use Property.withValue() if we add that back
        return fields.entrySet().stream()
                .filter(entry -> entry.getKey().name().equals(name))
                .map(entry -> {
                    String[] propertyNameParts = entry.getKey().name().split("-");
                    return new Property(propertyNameParts[propertyNameParts.length - 1], entry.getValue().getText());
                })
                .findFirst()
                .orElseThrow();
    }

    /**
     * @return
     */
    public Collection<Property> getElementProperties() {
        return fields.keySet().stream()
                .map(prop -> getElementProperty(prop.name()))
                .collect(Collectors.toList());
    }

}