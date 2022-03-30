package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract class that represents a game element. Keeps track of properties of the game element.
 *
 * @author Ricky Weerts and Shaan Gondalia
 */
public abstract class GameElement implements Element {

    private final String name;
    private final Collection<Property> properties = new HashSet<>();

    /**
     * Creates a new Game Element with the given parameters.
     *
     * @param name the name of the game element
     * @param properties the properties of the game element
     */
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