package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.element.Move;

import java.util.Set;

public class MoveFactory extends GameElementFactory {

    public MoveFactory() {
        super("elements.Move");
    }

    @Override
    public GameElement createElement(String name, Collection<Property> properties) {
        return new Move(name, properties);
    }
}