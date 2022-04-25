package oogasalad.engine.view.setup.SelectionView;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * abstract class for generic window that allows users to select between options
 *
 * @author Cynthia France
 */
public abstract class SelectionView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";
  protected ResourceBundle myResources;
  protected String cssFilePath;
  protected Text insns;
  protected FlowPane buttonLayout;
  protected VBox windowLayout;
  protected BorderPane root;
  protected double width;
  protected double height;

  /**
   *
   * creates the window for users to select options
   *
   * @param w width of window
   * @param h height of window
   * @param css the filepath for styling
   * @param language user-specified language in which the UI is displayed in
   */
  public SelectionView(double w, double h, String css, String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    setup();
    root = new BorderPane();
    root.setCenter(windowLayout);
  }

  /**
   *
   * returns the scene
   *
   * @return the scene
   */
  public Scene makeScene() {
    javafx.scene.Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    return scene;
  }

  protected abstract void setup();
  protected abstract void makeText();
  protected abstract void makeButtons();
  protected abstract Button makeButton(String text);
}
