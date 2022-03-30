package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class GameElement {

    private String name;
    private Collection<Property> properties = new HashSet<>();

    public GameElement(String name, Collection<Property> properties) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(properties);
        this.name = name;
        this.properties.addAll(properties);
    }

    /**
     * Returns whether the parameter matches the name of the game element
     *
     * @param desiredName the name to match to
     * @return whether the parameter matches the name of the game element
     */
    public boolean checkName(String desiredName) {
        return name.equals(desiredName);
    }

    /**
     * Returns an immutable record containing the GameElement's data
     * @return an immutable record containing the GameElement's data
     */
    public ElementRecord toRecord() {
        return new ElementRecord(name, properties);
    }

}