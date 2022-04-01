package oogasalad.builder.model.parser.adapter;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Piece;
import org.json.JSONObject;

/**
 * Class that has methods that can convert an Piece to a JSON string and vice versa
 *
 * @author Shaan Gondalia
 */
public class PieceAdapter implements JSONAdapter<Piece> {

  /**
   * Converts a Piece into a JSON String
   *
   * @param piece the Piece to convert
   * @return a JSON string
   */
  @Override
  public String toJSON(Piece piece) {
    JSONObject obj = new JSONObject();
    ElementRecordAdapter recordAdapter = new ElementRecordAdapter();
    return recordAdapter.toJSON(piece.toRecord());
  }

  /**
   * Converts a JSON String into a Piece
   *
   * @param json the JSON String to convert
   * @return a Piece with the given parameters
   */
  @Override
  public Piece fromJSON(String json) {
    return null;
  }
}