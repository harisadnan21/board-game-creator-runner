package oogasalad.engine.view.ControlPanel;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class ControlPanel {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/image-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/languages/";
  public static ResourceBundle imBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Image");
  public static String IMAGES_FOLDER = "images/";
  public static int ICON_SIZE = 25;

  protected VBox root;
  protected ResourceBundle myResources;

  public ControlPanel(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
    root = new VBox();
    root.setId("cp-root");
    createButtons();
  }

  protected abstract void createButtons();

  protected Button createButton(String imagePath) {
    ImageView buttonImage = new ImageView(new Image(imagePath));
    buttonImage.setId("cp-button-image");
    buttonImage.setFitWidth(ICON_SIZE);
    buttonImage.setFitHeight(ICON_SIZE);
    Button b = new Button("", buttonImage);
    b.setId("cp-button");
    return b;
  }
}
