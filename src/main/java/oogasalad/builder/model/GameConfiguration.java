package oogasalad.builder.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.board.RectangularBoard;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.element.factory.FactoryProvider;
import oogasalad.builder.model.element.factory.GameElementFactory;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The GameConfiguration stores all data about the game elements and board. This serves as a central
 * data structure that can be serialized/deserialized to JSON format. Depends on GameElement and
 * Board.
 *
 * @author Shaan Gondalia
 */
public class GameConfiguration implements BuilderModel {

    private RectangularBoard board;
    private Map<String, Collection<GameElement>> elements;
    private FactoryProvider provider;

    /**
     * Creates an empty GameConfiguration
     */
    public GameConfiguration() {
        board = null; // Board is unknown without initial setup
        elements = new HashMap<>();
        provider = new FactoryProvider();
    }

    /**
     * Creates a new board with the given dimensions.
     *
     * @param width the width of the board (in cells)
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
        for (GameElement element : elements.get(type)) {
            if (element.checkName(name)) {
                return element.toRecord();
            }
        }
        throw new ElementNotFoundException();
    }

    /**
     * Adds a Game Element to the game, updating an existing element if needed.
     *
     * @param type the type of the game element
     * @param name the name of the game element
     * @param properties the properties of the game element
     */
    @Override
    public void addGameElement(String type, String name, Collection<Property> properties){
        GameElementFactory factory = provider.getFactory(type);
        GameElement newElement = factory.createElement(name, properties);
        if (!elements.containsKey(type)) {
            elements.put(type, new HashSet<>());
        }
        elements.get(type).removeIf(e -> e.checkName(name));
        elements.get(type).add(newElement);
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x the x location to place
     * @param y the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     * @throws NullBoardException if the board has not been initialized
     */
    @Override
    public void placeBoardPiece(int x, int y, String name)
        throws OccupiedCellException, NullBoardException {
        checkBoardCreated();
        board.placePiece(x, y, name);
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
    public String findBoardPieceAt(int x, int y) throws NullBoardException {
        checkBoardCreated();
        return board.findPieceAt(x, y);
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
     * Converts a Configuration into a String representing the model's JSON Format
     *
     * @return a String representation of the configuration's JSON Format
     */
    @Override
    public String toJSON() {
        JSONObject obj = new JSONObject();
        // TODO: Remove magic values
        obj.put("board", board.toJSON());
        obj.put("pieces", elementsToJSONArray("piece"));
        obj.put("rules", elementsToJSONArray("rule"));
        return obj.toString();
    }

    /**
     * Converts a JSON String into a Game Configuration
     *
     * @param json the JSON string
     * @return a Builder Model made from the JSON string
     */
    @Override
    public BuilderModel fromJSON(String json) {
        return null;
    }

    // Checks if the board has been initialized
    private void checkBoardCreated() throws NullBoardException {
        if (board == null) {
            throw new NullBoardException();
        }
    }

    // Converts all elements of a certain type to a JSONArray
    private JSONArray elementsToJSONArray(String type){
        JSONArray arr = new JSONArray();
        for (GameElement element : elements.get(type)) {
            arr.put(element.toJSON());
        }
        return arr;
    }

}