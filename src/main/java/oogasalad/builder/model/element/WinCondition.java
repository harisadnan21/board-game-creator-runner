package oogasalad.builder.model.element;


import oogasalad.builder.controller.Property;

import java.util.Set;

/**
 * Represents some way that the game can end
 */
public class WinCondition extends GameElement {
    public WinCondition(String name, Set<Property> properties) {
        super(name, properties);
    }
}