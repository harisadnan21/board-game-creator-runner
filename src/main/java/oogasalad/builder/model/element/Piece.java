package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.parser.adapter.ElementRecordAdapter;
import org.json.JSONObject;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {
    public Piece(String name, Collection<Property> properties) {
        super(name, properties);
    }

    @Override
    public String toJSON() {
        JSONObject obj = new JSONObject();
        return this.toRecord().toJSON();
    }

    @Override
    public Element fromJSON(String json) {
        return null;
    }
}