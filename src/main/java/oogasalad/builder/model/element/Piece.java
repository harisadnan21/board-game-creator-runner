package oogasalad.builder.model.element;

import java.util.Collection;
import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {
    public Piece(String name, Collection<Property> properties) {
        super(name, properties);
    }
}