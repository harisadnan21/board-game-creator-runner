package oogasalad.engine.view.setup;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerModeView extends SelectionView {
//  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";
//  private ResourceBundle myResources;
//  private String cssFilePath;
  Button twoPlayer;
  Button onePlayer;
//  private Text insns;
//  private HBox buttonLayout;
//  private VBox windowLayout;
//  private BorderPane root;
//  private double width;
//  private double height;
//  private File game;

  public PlayerModeView(double w, double h, String css, String language) {
    super(w, h, css, language);
//    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
//    cssFilePath = css;
//    width = w;
//    height = h;
//    setup();
//    root = new BorderPane();
//    root.setCenter(windowLayout);
  }

//  public Scene makeScene() {
//    Scene scene = new Scene(root, width, height);
//    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
//    return scene;
//  }

  public Button getOnePlayer() {
    return onePlayer;
  }
  public Button getTwoPlayer() {
    return twoPlayer;
  }

  protected void setup() {
    windowLayout = new VBox();
    windowLayout.setId("p-mode-window-layout");
    makeText();
    makeButtons();
    windowLayout.getChildren().addAll(insns, buttonLayout);
  }

  protected void makeText() {
    insns = new Text(myResources.getString("PlayerMode"));
    insns.setId("p-mode-insns");
  }

  protected void makeButtons() {
    buttonLayout = new FlowPane();
    buttonLayout.setId("p-mode-button-layout");
    onePlayer = makeButton(myResources.getString("1Player"));
    twoPlayer = makeButton(myResources.getString("2Player"));
    buttonLayout.getChildren().addAll(onePlayer, twoPlayer);
  }

  protected Button makeButton(String text) {
    Button b = new Button(text);
    b.setId("p-mode-button");
    return b;
  }
}
