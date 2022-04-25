package oogasalad.engine.view.ControlPanel;

import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.BoardHistoryException;
import oogasalad.engine.model.parser.CreateJSONFile;
import oogasalad.engine.view.ApplicationAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Holds all game-related controls, including restart, undo, pause, save, home
 * Inherits from ControlPanel
 *
 * @author Cynthia France
 */
public class GameControlPanel extends ControlPanel {
  public static String HOME_IMAGE = IMAGES_FOLDER + imBundle.getString("Home");
  public static String RESTART_IMAGE = IMAGES_FOLDER + imBundle.getString("Restart");
  public static String BACK_IMAGE = IMAGES_FOLDER + imBundle.getString("Back");
  public static String PAUSE_IMAGE = IMAGES_FOLDER + imBundle.getString("Pause");
  public static String SAVE_IMAGE = IMAGES_FOLDER + imBundle.getString("Save");

  private static final Logger LOG = LogManager.getLogger(GameControlPanel.class);

  private Controller myController;
  private Button home;
  private Button restart;
  private Button undo;
  private Button pause;
  private Button save;
  private Consumer<Board> updateBoard;

  /**
   *
   * creates a control panel that handles game-related requests
   *
   * @param controller the game controller
   * @param updateBoard consumer, updates the backend board
   * @param language user-specified language in which the UI is displayed in
   */
  public GameControlPanel(Controller controller, Consumer<Board> updateBoard, String language) {
    super(language);
    myController = controller;
    this.updateBoard = updateBoard;
  }

  /**
   *
   * returns the pause button for GameView to use
   *
   * @return the pause button in this panel
   */
  public Button getPause() {
    return pause;
  }

  /**
   *
   * returns the home button for ViewManager to use
   *
   * @return the home button in this panel
   */
  public Button getHome() {
    return home;
  }

  /**
   *
   * returns the save button for GameView to use
   *
   * @return the save button in this panel
   */
  public Button getSave(){
    return save;
  }

  //creates all the buttons in the given panel and adds to the root
  protected void createButtons() {
    home = createButton(HOME_IMAGE);
    restart = createButton(RESTART_IMAGE);
    restart.setOnAction(e -> restartGame());
    undo = createButton(BACK_IMAGE);
    undo.setOnAction(e -> undoMove());
    pause = createButton(PAUSE_IMAGE);
    save = createButton(SAVE_IMAGE);
    save.setOnAction(e -> saveGame());
    root.getChildren().addAll(home, restart, undo, pause, save);

  }

  private void saveGame() {
    CreateJSONFile jsonCreator = new CreateJSONFile(myController);
    jsonCreator.createFile();
  }

  private void undoMove() {
    try {
      myController.undoGameOnce();
    } catch (BoardHistoryException ex) {
      LOG.error(ex);
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Notif"), ex.getMessage());
    }
  }

  private void restartGame() {
    myController.resetGame();
  }
}
