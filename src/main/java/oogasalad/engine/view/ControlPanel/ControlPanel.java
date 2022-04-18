package oogasalad.engine.view.ControlPanel;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class ControlPanel {
  public static String IMAGES_FOLDER = "images/";
  public static int ICON_SIZE = 25;

  protected VBox root;

  public ControlPanel() {
    root = new VBox();
    root.setSpacing(5);
    createButtons();
  }

  protected abstract void createButtons();

  protected Button createButton(String imagePath) {
    ImageView buttonImage = new ImageView(new Image(imagePath));
    buttonImage.setFitWidth(ICON_SIZE);
    buttonImage.setFitHeight(ICON_SIZE);
    Button b = new Button("", buttonImage);
    b.setStyle("-fx-background-color: #7C7C7C");
    return b;
  }
}
