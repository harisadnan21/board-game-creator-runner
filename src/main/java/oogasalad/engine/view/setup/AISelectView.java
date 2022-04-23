package oogasalad.engine.view.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.engine.model.ai.enums.Difficulty;

public class AISelectView extends SelectionView {
//  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";
//  private ResourceBundle myResources;
//  private String cssFilePath;
//  private Text insns;
//  private FlowPane buttonLayout;
//  private VBox windowLayout;
//  private BorderPane root;
//  private double width;
//  private double height;

  Map<String, Button> AIButtons = new HashMap<>();

  public AISelectView(double w, double h, String css, String language) {
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

  protected void setup() {
    windowLayout = new VBox();
    windowLayout.setId("ai-window-layout");
    makeText();
    makeButtons();
    windowLayout.getChildren().addAll(insns, buttonLayout);
  }

  protected void makeText() {
    insns = new Text(myResources.getString("AIInstructions"));
    insns.setId("ai-insns");
  }

  protected void makeButtons() {
    buttonLayout = new FlowPane();
    buttonLayout.setId("ai-button-layout");
    Difficulty[] aiDifficulties = Difficulty.class.getEnumConstants();
    for (Difficulty d : aiDifficulties) {
      Button b = makeButton(d.toString());
      buttonLayout.getChildren().add(b);
    }
    System.out.println(buttonLayout.getWidth());
    buttonLayout.setPrefWrapLength(500);
  }

  protected Button makeButton(String text) {
    Button b = new Button(text);
    b.setId("p-mode-button");
    return b;
  }
}
