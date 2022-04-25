package oogasalad.engine.view.Popup;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * abstract class for menus/user-notifications that pop up on top of the current working stage
 *
 * @author Cynthia France
 */
public abstract class PopupView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";
  protected ResourceBundle myResources;
  protected String cssFilePath;
  protected Stage popupStage;
  protected BorderPane root;

  /**
   *
   * creates a generic popup that appears on top of the current stage
   *
   * @param css the filepath for styling
   * @param language user-specified language in which the UI is displayed in
   */
  public PopupView(String css, String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;

    root = new BorderPane();
    root.setId("popup-root");
    makeStage();
  }

  /**
   * makes a new transparent stage on top of the old one
   */
  protected void makeStage() {
    popupStage = new Stage(StageStyle.TRANSPARENT);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    popupStage.setScene(scene);
  }

  /**
   *
   * returns the messageView stage
   *
   * @return messageView stage
   */
  public Stage getStage() {
    return popupStage;
  }

  /**
   * abstract method for inheriting classes to create the necessary elements for this popup
   */
  protected abstract void setup();

}
