package oogasalad.engine.view.game;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class PopupView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";
  protected ResourceBundle myResources;
  protected String cssFilePath;
  protected Stage popupStage;
  protected BorderPane root;

  public PopupView(String css, String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;

    root = new BorderPane();
    root.setId("popup-root");
    makeStage();
  }

  protected void makeStage() {
    popupStage = new Stage(StageStyle.TRANSPARENT);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    popupStage.setScene(scene);
  }

  protected abstract void setup();

}
