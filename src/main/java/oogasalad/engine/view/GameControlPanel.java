package oogasalad.engine.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameControlPanel extends ControlPanel {
  public static String HOME_IMAGE = IMAGES_FOLDER + "home.png";
  public static String RESTART_IMAGE = IMAGES_FOLDER + "restart.png";
  public static String BACK_IMAGE = IMAGES_FOLDER + "back.png";
  public static String PAUSE_IMAGE = IMAGES_FOLDER + "pause.png";

  VBox root;
  Button home;
  Button restart;
  Button undo;
  Button pause;

  public GameControlPanel() {
    root = new VBox();
    root.setSpacing(5);
    createButtons();
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    home = createButton(HOME_IMAGE);
    restart = createButton(RESTART_IMAGE);
    undo = createButton(BACK_IMAGE);
    pause = createButton(PAUSE_IMAGE);
    root.getChildren().addAll(home, restart, undo, pause);
  }
}
