package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents a move the player can make
 */
public class Move extends GameElement {


    public Move(String name, Collection<Property> properties) {
        super(name, properties);
    }
}