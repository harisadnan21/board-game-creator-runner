package oogasalad.engine.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SettingsControlPanel {
  public static String IMAGES_FOLDER = "images/";
  public static String INFO_IMAGE = IMAGES_FOLDER + "info.png";
  public static String SETTINGS_IMAGE = IMAGES_FOLDER + "gear.png";
  public static int ICON_SIZE = 25;

  VBox root;
  Button info;
  Button settings;

  public SettingsControlPanel() {
    root = new VBox();
    root.setSpacing(5);
    createButtons();
  }

  public Node getRoot() {
    return root;
  }

  private void createButtons() {
    info = createButton(INFO_IMAGE);
    settings = createButton(SETTINGS_IMAGE);
    root.getChildren().addAll(info, settings);
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
