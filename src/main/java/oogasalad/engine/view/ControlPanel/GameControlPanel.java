package oogasalad.engine.view.ControlPanel;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.view.ControlPanel.ControlPanel;

public class GameControlPanel extends ControlPanel {
  public static String HOME_IMAGE = IMAGES_FOLDER + imBundle.getString("Home");
  public static String RESTART_IMAGE = IMAGES_FOLDER + imBundle.getString("Restart");
  public static String BACK_IMAGE = IMAGES_FOLDER + imBundle.getString("Back");
  public static String PAUSE_IMAGE = IMAGES_FOLDER + imBundle.getString("Pause");

  Controller myController;
  Button home;
  Button restart;
  Button undo;
  Button pause;

  public GameControlPanel(Controller controller) {
    super();
    myController = controller;
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    home = createButton(HOME_IMAGE);
    restart = createButton(RESTART_IMAGE);
    restart.setOnAction(e -> myController.startGame());
    undo = createButton(BACK_IMAGE);
    pause = createButton(PAUSE_IMAGE);
    root.getChildren().addAll(home, restart, undo, pause);
  }

  public Button getPause() {
    return pause;
  }
}
