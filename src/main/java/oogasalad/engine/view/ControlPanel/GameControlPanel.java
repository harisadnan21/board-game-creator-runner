package oogasalad.engine.view.ControlPanel;

import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.BoardHistoryException;
import oogasalad.engine.model.parser.CreateJSONFile;
import oogasalad.engine.view.ApplicationAlert;

public class GameControlPanel extends ControlPanel {
  public static String HOME_IMAGE = IMAGES_FOLDER + imBundle.getString("Home");
  public static String RESTART_IMAGE = IMAGES_FOLDER + imBundle.getString("Restart");
  public static String BACK_IMAGE = IMAGES_FOLDER + imBundle.getString("Back");
  public static String PAUSE_IMAGE = IMAGES_FOLDER + imBundle.getString("Pause");
  public static String SAVE_IMAGE = IMAGES_FOLDER + imBundle.getString("Save");

  private Controller myController;
  private Button home;
  private Button restart;
  private Button undo;
  private Button pause;
  private Button save;
  private Consumer<Board> updateBoard;

  public GameControlPanel(Controller controller, Consumer<Board> updateBoard, String language) {
    super(language);
    myController = controller;
    this.updateBoard = updateBoard;
  }

  public Node getRoot() {
    return root;
  }

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
  //TODO: Save Game
  private void saveGame() {
    CreateJSONFile jsonCreator = new CreateJSONFile(myController);
    jsonCreator.createFile();
  }

  private void undoMove() {
    try {
      myController.undoGameOnce();
    } catch (BoardHistoryException ex) {
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Notif"), ex.getMessage());
    }
  }

  private void restartGame() {
    myController.resetGame();
  }

  private void updateBoard(Board b) {
    updateBoard.accept(b);
  }

  public Button getPause() {
    return pause;
  }

  public Button getHome() {
    return home;
  }

  public Button getSave(){
    return save;
  }
}
