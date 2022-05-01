package oogasalad.engine.view.ControlPanel.ControlButton;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ControlButton extends Button {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static ResourceBundle imBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Image");
  public static String IMAGES_FOLDER = "/engine-view/images/";
  public static int ICON_SIZE = 25;

  protected ResourceBundle myResources;
  protected String imagePath;

  public ControlButton(String language) {
    super();
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
  }

  protected void createButton() {
    ImageView buttonImage = getButtonImage(imagePath);
    this.setGraphic(buttonImage);
    this.setId("cp-button");
  }

  private ImageView getButtonImage(String path) {
    ImageView buttonImage = new ImageView(new Image(path));
    buttonImage.setId("cp-button-image");
    buttonImage.setFitWidth(ICON_SIZE);
    buttonImage.setFitHeight(ICON_SIZE);
    return buttonImage;
  }

  protected void setAction(Runnable setButton) {
    this.setOnAction(e -> setButton.run());
  }
}
