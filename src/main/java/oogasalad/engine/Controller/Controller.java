package oogasalad.engine.Controller;

import java.util.*;
import oogasalad.engine.Model.Engine;

/**
 * Controller class that communicates with the Model and View
 * so that any updates in the model are conveyed to the view to update it,
 * and any interactions that the user has with the View are conveyed to the Model.
 */
public class Controller {

  /**
   * Default constructor for the Controller class
   */
  public Controller() {
  }

  /**
   * Function to start the engine
   */
  public void startEngine() {
    Engine newEngine = new Engine();
  }

  /**
   * gets states of all available games
   * @return Returns all the games as a single string
   */
  public String getAvailableGames() {
    return "";
  }

}
