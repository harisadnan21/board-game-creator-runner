package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.element.Move;

import java.util.Set;

public class MoveFactory extends GameElementFactory<Move> {

    public MoveFactory() {
        super("/elements/Move.properties");
    }

    @Override
    public Move createElement(String name, Set<Property> properties) {
        return new Move(name, properties);
    }
}