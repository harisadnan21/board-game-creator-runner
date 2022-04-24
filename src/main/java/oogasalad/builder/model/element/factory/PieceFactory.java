package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.model.element.Piece;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;


public class PieceFactory extends GameElementFactory<Piece> {

  public PieceFactory() {
    super("builder.elements.Piece");
  }

  @Override
  public Piece createElement(String name, Collection<Property> properties)
      throws MissingRequiredPropertyException {
    validate(properties);
    return new Piece(name, properties);
  }

  /**
   * Creates a new piece from a JSON string
   *
   * @param json the JSON string
   * @return a new piece made from a JSON string
   */
  @Override
  public Piece fromJSON(String json) {
    Collection<Property> properties = propertiesFromJSON(json);
    String name = nameFromJSON(json);
    return new Piece(name, properties);
  }
}