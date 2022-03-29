package oogasalad.builder.model.element;


import oogasalad.builder.controller.Property;

import java.util.Collection;

/**
 * Represents some way that the game can end
 */
public class WinCondition extends GameElement {
    public WinCondition(String name, Collection<Property> properties) {
        super(name, properties);
    }
}