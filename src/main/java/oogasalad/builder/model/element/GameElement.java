package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GameElement {

    private String name;

    private Set<Property> properties = new HashSet<>();

    public GameElement(String name, Set<Property> properties) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(properties);
        this.name = name;
        this.properties.addAll(properties);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Set<Property> getProperties() {
        return properties;
    }

    /**
     * @param property 
     * @return
     */
    public void putProperty(Property property) {
        properties.add(property);
    }

}