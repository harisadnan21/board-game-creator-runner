package oogasalad.builder.model.element;

import oogasalad.builder.controller.Property;

import java.util.Collection;

/**
 * Represents a piece in the game
 */
public class Piece extends GameElement {
    public Piece(String name, Collection<Property> properties) {
        super(name, properties);
    }
}