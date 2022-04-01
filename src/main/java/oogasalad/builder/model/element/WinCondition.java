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