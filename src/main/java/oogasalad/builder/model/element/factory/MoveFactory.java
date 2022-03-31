package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Move;


public class MoveFactory extends GameElementFactory<Move> {

    public MoveFactory() {
        super("elements.Move");
    }

    @Override
    public Move createElement(String name, Collection<Property> properties) {
        return new Move(name, properties);
    }
}