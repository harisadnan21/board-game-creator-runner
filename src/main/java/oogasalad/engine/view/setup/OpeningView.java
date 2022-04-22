package oogasalad.engine.view.setup;

import java.io.File;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

public class OpeningView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private Double width;
  private Double height;
  private BorderPane root;
  private VBox buttonLayout;
  private VBox gameText;
  private VBox elements;
  private Text title;
  private JSONObject fileObject;
  private Button uploadFile;
  private Button gameBuilder;
  private Button dashboard;
  private Button contSel;
  private FileOpener fileOpener;
  private File myFileChoice;
  private ResourceBundle myResources;
  private String cssFilePath;

  public OpeningView(double w, double h, String css) {
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    root = new BorderPane();
    setupText();
    makeButtonLayout();
    elements = new VBox(50);
    elements.getChildren().addAll(gameText, buttonLayout);
    elements.setAlignment(Pos.CENTER);
    root.setCenter(elements);
    fileOpener = new FileOpener();
  }

  public Scene makeScene() {
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
    return scene;
  }

  public Button getContSel() {
    return contSel;
  }

  public Button getDashboard() {
    return dashboard;
  }

  public File getFileChoice() {
    return myFileChoice;
  }

  private void setupText() {
    title = new Text(myResources.getString("Title"));
    title.setId("opening-text");
    gameText = new VBox();
    gameText.setId("game-text");
    gameText.getChildren().add(title);
  }

  private void makeButtonLayout() {
    buttonLayout = new VBox();
    buttonLayout.setId("opening-button-layout");
    makeButtons();
    buttonLayout.getChildren().addAll(makeButtonRow(), contSel);
  }

  private HBox makeButtonRow() {
    HBox row = new HBox();
    row.setId("opening-button-row");
    row.getChildren().addAll(uploadFile, dashboard, gameBuilder);
    return row;
  }

  private void makeButtons() {
    uploadFile = makeButton(myResources.getString("UploadFile"));
    setupFileUpload();
    gameBuilder = makeButton(myResources.getString("GameBuilder"));
    contSel = makeButton(myResources.getString("Continue"));
    contSel.setDisable(true);
    dashboard = makeButton(myResources.getString("Dashboard"));
  }

  private void setupFileUpload() {
    Stage myStage = new Stage();
    uploadFile.setOnAction(e -> {
      try {
        File script = fileOpener.fileChoice(myStage);
        myFileChoice = script;
        handleInput(fileOpener.getRootObject(script));
        contSel.setDisable(false);
      } catch (NullPointerException nullPointerException) {
        System.out.println(nullPointerException.getMessage());
      } catch (Exception err) {
        System.out.println(err.getMessage());
      }
    });
  }

  private void handleInput(JSONObject object) {
    fileObject = object;
  }

  private Button makeButton(String buttonText) {
    Button b = new Button(buttonText);
    b.setId("opening-button");
    return b;
  }
}
