package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.element.Piece;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;


public class PieceFactory extends GameElementFactory<Piece> {

  public PieceFactory() {
    super("elements.OldPiece");
  }

  @Override
  public Piece createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new Piece(name, properties);
  }
}