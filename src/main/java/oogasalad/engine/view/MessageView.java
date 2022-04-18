package oogasalad.engine.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.message.Message;

public class MessageView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private ResourceBundle myResources;
  private String cssFilePath;
  private Stage popupStage;
  private VBox pauseRoot;
  private Button button;

  public MessageView(String message, String buttonText, String css) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    cssFilePath = css;

    makeMessage(message);
    makeButton(buttonText);
    makeStage();
  }

  public Stage getStage() {
    return popupStage;
  }

  public Button getButton() {
    return button;
  }

  private void makeStage() {
    popupStage = new Stage(StageStyle.TRANSPARENT);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    Scene pauseScene = new Scene(pauseRoot);
    pauseScene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    popupStage.setScene(pauseScene);
  }

  private void makeMessage(String message) {
    pauseRoot = new VBox();
    pauseRoot.setId("message-screen-root");
    Text text = new Text(message);
    text.setId("message-screen-text");
    pauseRoot.getChildren().add(text);
  }

  private void makeButton(String buttonText) {
    button = new Button(buttonText);
    button.setId("message-screen-button");
    pauseRoot.getChildren().add(button);
  }
}
