package oogasalad.builder.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.board.RectangularBoard;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.element.factory.GameElementFactory;
import oogasalad.builder.model.exception.OccupiedCellException;

/**
 * The GameConfiguration stores all data about the game elements and board. This serves as a central
 * data structure that can be serialized/deserialized to JSON format. Depends on GameElement and
 * Board.
 *
 * @author Shaan Gondalia
 */
public class GameConfiguration implements BuilderModel{

    private RectangularBoard board;
    private Map<String, Collection<GameElement>> elements;

    /**
     * Creates an empty GameConfiguration
     */
    public GameConfiguration() {
        board = null; // Board is unknown without initial setup
        elements = new HashMap<>();
    }

    /**
     * Adds a Game Element to the game.
     *
     * @param type the type of the game element
     * @param name the name of the game element
     * @param properties the properties of the game element
     */
    public void addGameElement(String type, String name, Collection<Property> properties){
        // TODO: Call GameElementFactory Here
        // TODO: Create Game Element and add it to HashMap
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x the x location to place
     * @param y the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     */
    public void placeBoardPiece(int x, int y, String name) throws OccupiedCellException {
        // TODO: Throw exception if board is null
        board.placePiece(x, y, name);
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     */
    public String findBoardPieceAt(int x, int y) {
        // TODO: Throw exception if board is null
        return board.findPieceAt(x, y);
    }

}