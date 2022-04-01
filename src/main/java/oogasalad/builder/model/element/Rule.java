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