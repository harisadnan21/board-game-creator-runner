package oogasalad.builder.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import oogasalad.builder.model.BuilderModel;
import oogasalad.builder.model.GameConfiguration;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.callback.*;

/**
 * Controller for the Builder. Interfaces between the Builder View and Builder Model.
 *
 * @author Shaan Gondalia
 */
public class BuilderController {

    private final BuilderModel gameConfig;
    private final BuilderView builderView;

    /**
     * Creates a BuilderController Object that interfaces between the view and model.
     *
     */
    public BuilderController(BuilderView view) {
        gameConfig = new GameConfiguration();
        builderView = view;

        registerHandlers();
    }

    private void registerHandlers() {
        builderView.registerCallbackHandler(GetPropertiesCallback.class, this::getRequiredProperties);
        builderView.registerCallbackHandler(GetElementPropertiesCallback.class, this::getElementProperties);
        builderView.registerCallbackHandler(GetElementNamesCallback.class, this::getElementNames);
        builderView.registerCallbackHandler(SaveCallback.class, this::save);
        builderView.registerCallbackHandler(UpdateGameElementCallback.class, this::update);
        builderView.registerCallbackHandler(ClearCellCallback.class, this::clearCell);
        builderView.registerCallbackHandler(PlacePieceCallback.class, this::placePiece);
        builderView.registerCallbackHandler(GetElementPropertyByKeyCallback.class, this::getElementPropertyByKey);
        builderView.registerCallbackHandler(MakeBoardCallback.class, this::makeBoard);
    }

    /**
     * Creates a new board with the given dimensions.
     *
     * @param callback callback containing the width and height of the board (in cells)
     */
    public Void makeBoard(MakeBoardCallback callback) {
        gameConfig.makeBoard(callback.width(), callback.height());
        return null;
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param callback callback object representing what piece and where to place it
     * @throws OccupiedCellException if the cell at x, y is already occupied by a piece
     * @throws NullBoardException    if the board has not been initialized
     */
    private Void placePiece(PlacePieceCallback callback)
        throws OccupiedCellException, NullBoardException, ElementNotFoundException {
        gameConfig.placeBoardPiece(callback.x(), callback.y(), callback.piece());
        return null;
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     * @throws NullBoardException if the board has not been initialized
     */
    public String findPieceAt(int x, int y) throws NullBoardException, ElementNotFoundException {
        return gameConfig.findBoardPieceAt(x, y);
    }

    /**
     * Clears the cell on the board at the given coordinates
     *
     * @param callback callback object containing the x and y location to clear
     */
    private Void clearCell(ClearCellCallback callback) throws NullBoardException {
        gameConfig.clearBoardCell(callback.x(), callback.y());
        return null;
    }

    /**
     * Gets the properties of an element specified by its type and name
     *
     * @param callback a callback object containing the type and name of the element to get properties for
     * @return the properties of an element
     */
    private Collection<Property> getElementProperties(GetElementPropertiesCallback callback)
        throws ElementNotFoundException {
        ElementRecord elementRecord = gameConfig.findElementInfo(callback.type(), callback.name());
        return elementRecord.properties();
    }

    /**
     * Gets the value of one property of an element specified by its type, name, and property key
     *
     * @param callback callback object containing the type, name, and property key
     * @return the value of the property
     */
    private String getElementPropertyByKey(GetElementPropertyByKeyCallback callback)
        throws ElementNotFoundException {
        ElementRecord elementRecord = gameConfig.findElementInfo(callback.type(), callback.name());
        for (Property prop : elementRecord.properties()) {
            if (prop.name().equals(callback.key())) {
                return prop.value();
            }
        }
        throw new ElementNotFoundException();
    }



    /**
     * Provides a list of element names that are of the given type
     *
     * @param callback callback object containing the type of the elements to name
     * @return a collection of names that are of a certain type (e.g. piece)
     */
    public Collection<String> getElementNames(GetElementNamesCallback callback) throws ElementNotFoundException {
        return gameConfig.getElementNames(callback.type());
    }

    /**
     * Updates the game configuration with an element record, either adding or modifying a game
     * element based on the parameters
     *
     *
     * @param callback a callback object containing the type, name, and properties of the element to update
     */
    private Void update(UpdateGameElementCallback callback)
        throws InvalidTypeException, MissingRequiredPropertyException {
       gameConfig.addGameElement(callback.type(), callback.name(), callback.properties());
       return null;
    }

    /**
     * Returns the required properties of a game element
     *
     * @param callback a callback object cont
     * @return the required properties of a game element
     */
    private Collection<Property> getRequiredProperties(GetPropertiesCallback callback) throws InvalidTypeException {
        return gameConfig.getRequiredProperties(callback.type());
    }

    /**
     * Saves the existing Game Configuration to a JSON file
     *
     * @param callback callback object containing the File to save the Game Configuration to.
     */
    private Void save(SaveCallback callback) throws NullBoardException {
        try {
            FileWriter writer = new FileWriter(callback.file());
            writer.write(gameConfig.toJSON());
            writer.close();
        } catch (IOException | ElementNotFoundException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
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