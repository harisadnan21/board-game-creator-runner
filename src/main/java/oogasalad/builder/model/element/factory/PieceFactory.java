package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Piece;


public class PieceFactory extends GameElementFactory<Piece> {

    public PieceFactory() {
        super("elements.Piece");
    }

    @Override
    public Piece createElement(String name, Collection<Property> properties) {
        return new Piece(name, properties);
    }
}