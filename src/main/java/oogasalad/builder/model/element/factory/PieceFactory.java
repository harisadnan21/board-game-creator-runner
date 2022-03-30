package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Piece;

import java.util.Set;

public class PieceFactory extends GameElementFactory {

    public PieceFactory() {
        super("/elements/Piece.properties");
    }

    @Override
    public Piece createElement(String name, Collection<Property> properties) {
        return new Piece(name, properties);
    }
}