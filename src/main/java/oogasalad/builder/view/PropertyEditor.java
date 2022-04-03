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

    public PropertyEditor() {
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

        propertyBox.getChildren().addAll(
                new Label(property.name()),
                valueField
        );
        getChildren().add(propertyBox);
    }

    /**
     * @param properties 
     * @return
     */
    public void setElementProperties(Collection<Property> properties) {
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
                .map(entry -> new Property(entry.getKey().name(), entry.getValue().getText()))
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