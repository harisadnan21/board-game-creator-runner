package oogasalad.engine.view.ControlPanel;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Abstract class for control panels that control game and application function
 *
 * @author Cynthia France
 */
public abstract class ControlPanel {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static String IMAGES_FOLDER = "/engine-view/images/";

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
  protected abstract void createButtons(String language);
}
