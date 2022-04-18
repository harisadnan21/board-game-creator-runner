package oogasalad.builder.model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.property.Property;

/**
 * Describes the external API of the Builder Model.
 *
 * @author Shaan Gondalia
 */
public interface BuilderModel extends JSONSerializable, JSONParseable<BuilderModel> {

  /**
   * Creates a new board with the given dimensions.
   *
   * @param width  the width of the board (in cells)
   * @param height the height of the board (in cells)
   */
  void makeBoard(int width, int height);

  /**
   * Returns an Element Record that contains information of the element with the given type/name
   *
   * @param type the type of the element to find
   * @param name the name of the element to find
   * @return an element record containing information about the game element
   */
  ElementRecord findElementInfo(String type, String name) throws ElementNotFoundException;

  /**
   * Provides a list of element names that are of the given type
   *
   * @param type the type of the elements to name
   * @return a collection of names that are of a certain type (e.g. piece)
   */
  Collection<String> getElementNames(String type) throws ElementNotFoundException;

  /**
   * Adds a Game Element to the game, updating an existing element if possible
   *
   * @param type       the type of the game element
   * @param name       the name of the game element
   * @param properties the properties of the game element
   */
  void addGameElement(String type, String name, Collection<Property> properties)
      throws InvalidTypeException, MissingRequiredPropertyException;

  /**
   * Attempts to place a piece at the given coordinates
   *
   * @param x    the x location to place
   * @param y    the y location to place
   * @param name the name of the piece to place
   * @throws NullBoardException    if the board has not been initialized
   */
  void placeBoardPiece(int x, int y, String name)
      throws NullBoardException, ElementNotFoundException;

  /**
   * Finds the name of the piece at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the name of the piece
   * @throws NullBoardException if the board has not been initialized
   */
  String findBoardPieceAt(int x, int y) throws NullBoardException, ElementNotFoundException;

  /**
   * Clears the cell on the board at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  void clearBoardCell(int x, int y) throws NullBoardException;

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  Collection<Property> getRequiredProperties(String type) throws InvalidTypeException;

  /**
   * Converts a Builder Model into a String representing the model's JSON Format
   *
   * @return a String representation of the model's JSON Format
   */
  String toJSON() throws NullBoardException, ElementNotFoundException;

  /**
   * Converts a JSON String into a Builder Model
   *
   * @param json the JSON string
   * @return a model made from the JSON string
   */
  BuilderModel fromJSON(String json);

  /**
   * Copies the original files to a new directory, using the data stored in the file mapper.
   *
   * @param directory The new directory to copy the game configuration resources to
   */
  void copyFiles(File directory) throws IOException;

}