package oogasalad.builder.model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import oogasalad.builder.controller.ResourcesSingleton;
import oogasalad.builder.model.board.Board;
import oogasalad.builder.model.board.RectangularBoard;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.element.FileMapper;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.element.factory.FactoryProvider;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MalformedConfigurationException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.property.Property;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The GameConfiguration stores all data about the game elements and board. This serves as a central
 * data structure that can be serialized/deserialized to JSON format. Depends on GameElement and
 * Board.
 *
 * @author Shaan Gondalia
 */
public class GameConfiguration implements BuilderModel {

  private static final String EMPTY = "empty";
  private static final String ID = "id";
  private static final String PIECE = "piece";
  private static final int INDENT_FACTOR = 4;
  public static final String METADATA = "metadata";
  private static final String NAME = "name";
  private static final String BOARD = "board";
  private final Map<String, Map<String, GameElement>> elements;
  private final FactoryProvider provider;
  private final FileMapper mapper;
  private Board board;
  private static final ResourceBundle resources = ResourceBundle.getBundle("config.Elements");

  /**
   * Creates an empty GameConfiguration
   */
  public GameConfiguration() {
    board = null; // Board is unknown without initial setup
    elements = new HashMap<>();
    provider = new FactoryProvider();
    mapper = new FileMapper();
    resetElements();
  }

  /**
   * Creates a new board with the given dimensions.
   *
   * @param width  the width of the board (in cells)
   * @param height the height of the board (in cells)
   */
  @Override
  public void makeBoard(int width, int height) {
    board = new RectangularBoard(width, height);
  }

  /**
   * Returns an Element Record that contains information of the element with the given type/name
   *
   * @param type the type of the element to find
   * @param name the name of the element to find
   * @return an element record containing information about the game element
   */
  @Override
  public ElementRecord findElementInfo(String type, String name) throws ElementNotFoundException {
    if (!elements.containsKey(type)) {
      throw new ElementNotFoundException();
    }
    if (!elements.get(type).containsKey(name)) {
      throw new ElementNotFoundException();
    }
    ElementRecord mapped = elements.get(type).get(name).toRecord();
    Collection<Property> properties = mapper.unMapProperties(mapped.properties());
    return new ElementRecord(mapped.name(), properties);
  }

  /**
   * Provides a list of element names that are of the given type
   *
   * @param type the type of the elements to name
   * @return a collection of names that are of a certain type (e.g. piece)
   */
  @Override
  public Collection<String> getElementNames(String type) throws ElementNotFoundException {
    if (!elements.containsKey(type)) {
      throw new ElementNotFoundException();
    }
    return elements.get(type).keySet();
  }

  /**
   * Adds a Game Element to the game, updating an existing element if needed.
   *
   * @param type       the type of the game element
   * @param name       the name of the game element
   * @param properties the properties of the game element
   */
  @Override
  public void addGameElement(String type, String name, Collection<Property> properties)
      throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> reMappedProperties = mapper.reMapProperties(properties);
    GameElement newElement = provider.createElement(type, name, reMappedProperties);
    if (!elements.containsKey(type)) {
      throw new ElementNotFoundException();
    }
    elements.get(type).put(name, newElement);
  }

  /**
   * Attempts to place a piece at the given coordinates
   *
   * @param x    the x location to place
   * @param y    the y location to place
   * @param name the name of the piece to place
   * @throws NullBoardException    if the board has not been initialized
   */
  @Override
  public void placeBoardPiece(int x, int y, String name)
      throws NullBoardException, ElementNotFoundException {
    checkBoardCreated();
    board.placePiece(x, y, pieceNameToID(name));
  }

  /**
   * Finds the name of the piece at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the name of the piece
   * @throws NullBoardException if the board has not been initialized
   */
  @Override
  public String findBoardPieceAt(int x, int y) throws NullBoardException, ElementNotFoundException {
    checkBoardCreated();
    int id = board.findPieceAt(x, y);
    for (GameElement element : elements.get(PIECE).values()) {
      ElementRecord record = element.toRecord();
      for (Property property : record.properties()) {
        if (property.name().equals(ID) && Integer.parseInt(property.valueAsString()) == id) {
          return record.name();
        }
      }
    }
    return EMPTY;
  }

  /**
   * Clears the cell on the board at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  public void clearBoardCell(int x, int y) throws NullBoardException {
    checkBoardCreated();
    board.clearCell(x, y);
  }

  /**
   * Clears the background of the cell at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  @Override
  public void clearCellBackground(int x, int y) throws NullBoardException {
    checkBoardCreated();
    board.clearCellBackground(x, y);
  }

  /**
   * Colors the background of the cell at the given coordinates with the given color
   *
   * @param x the x location to color
   * @param y the y location to color
   * @param color the hexadecimal string of the color to set at the cell
   */
  @Override
  public void colorCellBackground(int x, int y, String color) throws NullBoardException {
    checkBoardCreated();
    board.colorCellBackground(x, y, color);
  }

  /**
   * Finds the background color of the cell at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the background color of the cell at the given coordinates
   */
  @Override
  public String findCellBackground(int x, int y){
    return board.findCellBackground(x, y);
  }

  /**
   * Returns the required properties of a game element
   *
   * @return the required properties of a game element
   */
  @Override
  public Collection<Property> getRequiredProperties(String type) throws InvalidTypeException {
    return provider.getRequiredProperties(type);
  }

  /**
   * Returns the width of the board
   *
   * @return the width of the board
   */
  @Override
  public int getWidth() {
    checkBoardCreated();
    return board.getWidth();
  }

  /**
   * Returns the height of the board
   *
   * @return the height of the board
   */
  @Override
  public int getHeight(){
    checkBoardCreated();
    return board.getHeight();
  }

  /**
   * Converts a Configuration into a String representing the model's JSON Format
   *
   * @return a String representation of the configuration's JSON Format
   */
  @Override
  public String toJSON() throws NullBoardException, ElementNotFoundException {
    checkBoardCreated();
    JSONObject obj = new JSONObject();
    obj.put(METADATA, metaDataToJSON());
    obj.put(BOARD, new JSONObject(board.toJSON()));
    for (String key : Collections.list(resources.getKeys())) {
      obj.put(resources.getString(key), elementsToJSONArray(key));
    }
    return obj.toString(INDENT_FACTOR);
  }

  /**
   * Converts a JSON String into a Builder Model
   *
   * @param json the JSON string
   * @param workingDirectory the working directory of the configuration file
   */
  public void fromJSON(String json, String workingDirectory) {
    JSONObject obj = new JSONObject(json);
    board = new RectangularBoard(0, 0).fromJSON(obj.getJSONObject("board").toString());
    resetElements();
    try{
      for (String key : Collections.list(resources.getKeys())) {
        addJSONArray(obj.getJSONArray(resources.getString(key)), key, workingDirectory);
      }
      addJSONObject(obj.getJSONObject(METADATA), METADATA, workingDirectory);
    } catch (JSONException e) {
      throw new MalformedConfigurationException(e.getMessage());
    }
  }

  /**
   * Copies the original files to a new directory, using the data stored in the file mapper.
   *
   * @param directory The new directory to copy the game configuration resources to
   */
  public void copyFiles(File directory) throws IOException {
    mapper.copyFiles(directory);
  }

  // Checks if the board has been initialized
  private void checkBoardCreated() throws NullBoardException {
    if (board == null) {
      throw new NullBoardException();
    }
  }

  // Converts all elements of a certain type to a JSONArray
  private JSONArray elementsToJSONArray(String type) throws ElementNotFoundException {
    JSONArray arr = new JSONArray();
    if (!elements.containsKey(type)) {
      return arr;
    }
    for (GameElement element : elements.get(type).values()) {
      ElementRecord record = element.toRecord();
      arr.put(new JSONObject(record.toJSON()));
    }
    return arr;
  }

  // Adds the contents of a json array to the map of game elements
  private void addJSONArray(JSONArray arr, String type, String workingDir) {
    for (int i = 0; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);
      addJSONObject(obj, type, workingDir);
    }
  }

  // Adds the contents of a json object to the map of game elements
  private void addJSONObject(JSONObject obj, String type, String workingDir) {
    if (obj.get(NAME) != null) {
      GameElement element = provider.fromJSON(type, obj.toString());
      Collection<Property> resolvedProperties = mapper.resolveResourcePaths(element.toRecord()
          .properties(), workingDir);
      addGameElement(type, element.toRecord().name(), resolvedProperties);
    }
  }

  // Converts metadata to a json String
  private JSONObject metaDataToJSON() {
    for (GameElement element : elements.get(METADATA).values()) {
      ElementRecord record = element.toRecord();
      return new JSONObject(record.toJSON());
    }
    return new JSONObject();
  }

  // Gets a piece's id from its name
  private int pieceNameToID(String name) throws ElementNotFoundException {
    ElementRecord record = findElementInfo(PIECE, name);
    for (Property property : record.properties()) {
      if (property.name().equals(ID)) {
        return Integer.parseInt(property.valueAsString());
      }
    }
    throw new ElementNotFoundException();
  }

  // Resets the map of game elements
  private void resetElements() {
    for (String key : Collections.list(resources.getKeys())) {
      elements.put(key, new HashMap<>());
    }
    elements.put(METADATA, new HashMap<>());
  }

}