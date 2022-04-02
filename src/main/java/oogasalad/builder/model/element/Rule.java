package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents a rule governing how the game works
 */
public class Rule extends GameElement {

    public Rule(String name, Collection<Property> properties) {
        super(name, properties);
    }
}