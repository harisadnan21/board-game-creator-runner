package oogasalad.engine.model.parser;
import java.io.FileWriter;
import java.io.IOException;
import oogasalad.builder.view.property.PropertyEditor;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.driver.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Class that creates new json file based off the current game and its configuration.
 * @author Haris Adnan
 */
public class CreateJSONFile {

  private static final Logger LOG = LogManager.getLogger(CreateJSONFile.class);

  private Controller myController;
  private Game myGame;

  /**
   * The constructor for the Class that is used to define the controller and the Game.
   * @param controller
   */
  public CreateJSONFile(Controller controller){
    myController = controller;
    myGame = myController.getGame();
  }

  /**
   * Method that creates the JSON file and saves it
   */
  public FileWriter createFile(){
    JSONObject obj = new JSONObject();
    obj.put("Key", "Value");
    try(FileWriter file = new FileWriter("newJSON.json")){
    file.write(obj.toString());
    file.flush();
    file.close();
    return file;

  }catch(IOException e){
    LOG.error(e);
    return null;
  }
  }
  //returns JSONObjects that are made by reading the game.
  private JSONObject makeJSONObjects(){
    return null;
  }
}
