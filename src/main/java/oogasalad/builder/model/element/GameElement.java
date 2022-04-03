package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import org.json.JSONObject;


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

    /**
     * Converts an Element into a String representing the object's JSON Format
     *
     * @return a String representation of the objects JSON Format
     */
    @Override
    public String toJSON(){
        JSONObject obj = new JSONObject();
        return this.toRecord().toJSON();
    }

    /**
     * Converts a JSON String into an element
     *
     * @param json the JSON string
     * @return an element made from the JSON string
     */
    @Override
    public Element fromJSON(String json){
        return null;
    }


}