package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import org.json.JSONObject;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {
    public Piece(String name, Collection<Property> properties) {
        super(name, properties);
    }

    /**
     * Converts a Piece into a String representing the object's JSON Format
     *
     * @return a String representation of the object's JSON Format
     */
    @Override
    public String toJSON() {
        JSONObject obj = new JSONObject();
        return this.toRecord().toJSON();
    }

    /**
     * Converts a JSON String into a piece
     *
     * @param json the JSON string
     * @return a piece made from the JSON string
     */
    @Override
    public Element fromJSON(String json) {
        return null;
    }
}