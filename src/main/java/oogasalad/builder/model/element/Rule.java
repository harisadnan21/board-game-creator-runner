package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents a rule governing how the game works
 */
public class Rule extends GameElement {

    public Rule(String name, Set<Property> properties) {
        super(name, properties);
    }
}