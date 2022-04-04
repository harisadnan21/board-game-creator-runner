package oogasalad.builder.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javafx.stage.Stage;
import oogasalad.builder.model.BuilderModel;
import oogasalad.builder.model.GameConfiguration;
import oogasalad.builder.model.board.RectangularBoard;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.view.BuilderView;

/**
 * Controller for the Builder. Interfaces between the Builder View and Builder Model.
 *
 * @author Shaan Gondalia
 */
public class BuilderController {

    private BuilderModel gameConfig;
    private BuilderView view;

    /**
     * Creates a BuilderController Object that interfaces between the view and model.
     *
     * @param stage the stage of the application (needed for the view)
     */
    public BuilderController(Stage stage) {
        gameConfig = new GameConfiguration();
        view = new BuilderView(stage);
    }

    /**
     * Creates a new board with the given dimensions.
     *
     * @param width  the width of the board (in cells)
     * @param height the height of the board (in cells)
     */
    public void makeBoard(int width, int height) {
        gameConfig.makeBoard(width, height);
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x    the x location to place
     * @param y    the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     * @throws NullBoardException    if the board has not been initialized
     */
    public void placePiece(int x, int y, String name)
        throws OccupiedCellException, NullBoardException {
        gameConfig.placeBoardPiece(x, y, name);
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     * @throws NullBoardException if the board has not been initialized
     */
    public String findPieceAt(int x, int y) throws NullBoardException {
        return gameConfig.findBoardPieceAt(x, y);
    }

    /**
     * Clears the cell on the board at the given coordinates
     *
     * @param x the x location to clear
     * @param y the y location to clear
     */
    public void clearCell(int x, int y) throws NullBoardException {
        gameConfig.clearBoardCell(x, y);
    }

    /**
     * Gets the properties of an element specified by its type and name
     *
     * @param type the type of the element
     * @param name the name of the element
     * @return the properties of an element
     */
    public Collection<Property> getElementProperties(String type, String name)
        throws ElementNotFoundException {
        ElementRecord elementRecord = gameConfig.findElementInfo(type, name);
        return elementRecord.properties();
    }

    /**
     * Updates the game configuration with an element record, either adding or modifying a game
     * element based on the parameters
     *
     * @param type the type of the element to update
     * @param name the name of the element to update
     * @param properties the properties of the element
     */
    public void update(String type, String name, Collection<Property> properties) {
       gameConfig.addGameElement(type, name, properties);
    }

    /**
     * Saves the existing Game Configuration to a JSON file
     *
     * @param file the File to save the Game Configuration to.
     */
    public void save(File file) throws NullBoardException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(gameConfig.toJSON());
            writer.close();
        } catch (IOException | ElementNotFoundException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    /**
     * Loads a Game Configuration from a JSON File
     *
     * @param file the file to load the game configuration from
     */
    public void load(File file) {
        // TODO implement here
    }

}