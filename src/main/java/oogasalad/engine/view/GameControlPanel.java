package oogasalad.engine.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GameControlPanel {

  public static String IMAGES_FOLDER = "images/";
  public static String HOME_IMAGE = IMAGES_FOLDER + "home.png";
  public static String RESTART_IMAGE = IMAGES_FOLDER + "restart.png";
  public static String BACK_IMAGE = IMAGES_FOLDER + "back.png";
  public static String PAUSE_IMAGE = IMAGES_FOLDER + "pause.png";
  public static int ICON_SIZE = 25;

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

  private void createButtons() {
    home = createButton(HOME_IMAGE);
    restart = createButton(RESTART_IMAGE);
    undo = createButton(BACK_IMAGE);
    pause = createButton(PAUSE_IMAGE);
    root.getChildren().addAll(home, restart, undo, pause);
  }

  private Button createButton(String imagePath) {
    ImageView buttonImage = new ImageView(new Image(imagePath));
    buttonImage.setFitWidth(ICON_SIZE);
    buttonImage.setFitHeight(ICON_SIZE);
    Button b = new Button("", buttonImage);
    b.setStyle("-fx-background-color: #7C7C7C");
    return b;
  }

}
