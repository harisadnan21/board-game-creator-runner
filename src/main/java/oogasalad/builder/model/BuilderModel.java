package oogasalad.builder.model;

import java.util.*;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.exception.OccupiedCellException;

/**
 * Describes the external API of the Builder Model.
 */
public interface BuilderModel {

    /**
     * Creates a new board with the given dimensions.
     *
     * @param width the width of the board (in cells)
     * @param height the height of the board (in cells)
     */
    public void makeBoard(int width, int height);

    /**
     * Adds a Game Element to the game.
     *
     * @param type the type of the game element
     * @param name the name of the game element
     * @param properties the properties of the game element
     */
    public void addGameElement(String type, String name, Collection<Property> properties);

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x the x location to place
     * @param y the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     */
    public void placeBoardPiece(int x, int y, String name) throws OccupiedCellException;

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     */
    public String findBoardPieceAt(int x, int y);

}