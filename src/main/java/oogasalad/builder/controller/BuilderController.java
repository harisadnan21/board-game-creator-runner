package oogasalad.builder.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import oogasalad.builder.model.BuilderModel;
import oogasalad.builder.model.GameConfiguration;
import oogasalad.builder.model.element.ElementRecord;

/**
 * Controller for the Builder. Interfaces between the Builder View and Builder Model.
 *
 * @author Shaan Gondalia
 */
public class BuilderController {

    private BuilderModel gameConfig;

    /**
     * Default constructor
     */
    public BuilderController() {
        gameConfig = new GameConfiguration();
    }

    /**
     * @param type 
     * @return
     */
    private Collection<Property> getElementProperties(String type) {
        // TODO implement here
        return null;
    }

    /**
     * @param element 
     * @return
     */
    private void update(ElementRecord element) {
        // TODO implement here
    }

    /**
     * Saves the existing Game Configuration to a JSON file
     *
     * @param file the File to save the Game Configuration to.
     */
    public void save(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(gameConfig.toJSON());
            writer.close();
        } catch (IOException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    /**
     * Loads a Game Configuration from a JSON File
     *
     * @param file the file to load the game configuration from
     */
    private void load(File file) {
        // TODO implement here
    }

}