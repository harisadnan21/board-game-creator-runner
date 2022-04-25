package oogasalad.engine.view.ControlPanel;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Abstract class for control panels that control game and application function
 *
 * @author Cynthia France
 */
public abstract class ControlPanel {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static ResourceBundle imBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Image");
  public static String IMAGES_FOLDER = "/engine-view/images/";
  public static int ICON_SIZE = 25;

  protected VBox root;
  protected ResourceBundle myResources;

  /**
   * creates a panel of buttons with which the user can control game and application settings
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public ControlPanel(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
    root = new VBox();
    root.setId("cp-root");
    createButtons();
  }

  /**
   *
   * returns the root of this panel
   *
   * @return root, a VBox consisting of all panel Buttons
   */
  public Node getRoot() {
    return root;
  }

  //creates all the buttons in a given panel
  protected abstract void createButtons();

  //creates a singular button in the panel with an image from the given image path
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
