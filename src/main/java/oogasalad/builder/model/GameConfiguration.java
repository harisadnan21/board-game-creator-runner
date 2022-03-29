package oogasalad.builder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import oogasalad.builder.model.Board;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.OccupiedCellException;

/**
 * The GameConfiguration stores all data about the game elements and board. This serves as a central
 * data structure that can be serialized/deserialized to JSON format. Depends on GameElement and
 * Board.
 *
 * @author Shaan Gondalia
 */
public class GameConfiguration {

    private Board board;
    private Map<String, Collection<GameElement>> elements;

    /**
     * Default constructor f
     */
    public GameConfiguration() {
        board = null; // Board is unknown without initial setup
        elements = new HashMap<>();
    }

    /**
     * @param type 
     * @param element
     * @return
     */
    public void putGameElement(String type, GameElement element) {
        // TODO implement here
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x the x location to place
     * @param y the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     */
    public void placeBoardPiece(int x, int y, String name) {
        // TODO implement here
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     */
    public String findBoardPieceAt(int x, int y) {
        // TODO implement here
        return "";
    }

}