package oogasalad.builder.model.element;


import java.util.Collection;
import oogasalad.builder.controller.Property;

import java.util.Set;
import org.json.JSONObject;

/**
 * Represents some way that the game can end
 */
public class WinCondition extends GameElement {
    public WinCondition(String name, Collection<Property> properties) {
        super(name, properties);
    }

    /**
     * Converts a Win Condition into a String representing the object's JSON Format
     *
     * @return a String representation of the object's JSON Format
     */
    @Override
    public String toJSON() {
        JSONObject obj = new JSONObject();
        return this.toRecord().toJSON();
    }

    /**
     * Converts a JSON String into a win condition
     *
     * @param json the JSON string
     * @return a win condition made from the JSON string
     */
    @Override
    public Element fromJSON(String json) {
        return null;
    }
}