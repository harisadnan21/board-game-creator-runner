package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents a move the player can make
 */
public class Move extends GameElement {


    public Move(String name, Set<Property> properties) {
        super(name, properties);
    }
}