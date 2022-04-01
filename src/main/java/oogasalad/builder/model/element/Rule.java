package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;

import java.util.Set;
import org.json.JSONObject;

/**
 * Represents a rule governing how the game works
 */
public class Rule extends GameElement {

    public Rule(String name, Collection<Property> properties) {
        super(name, properties);
    }

    /**
     * Converts a Rule into a String representing the object's JSON Format
     *
     * @return a String representation of the object's JSON Format
     */
    @Override
    public String toJSON() {
        JSONObject obj = new JSONObject();
        return this.toRecord().toJSON();
    }

    /**
     * Converts a JSON String into a rule
     *
     * @param json the JSON string
     * @return a rule made from the JSON string
     */
    @Override
    public Element fromJSON(String json) {
        return null;
    }
}