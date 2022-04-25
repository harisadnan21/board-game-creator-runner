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
import oogasalad.engine.view.ApplicationAlert;
import oogasalad.engine.view.OptionSelect.LanguageSelect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class OpeningView {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";
  private static final Logger LOG = LogManager.getLogger(OpeningView.class);

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
  private DirectoryOpener directoryOpener;
  private File myFileChoice;
  private ResourceBundle myResources;
  private String cssFilePath;
  private LanguageSelect ls;

  public OpeningView(double w, double h, String css, String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    cssFilePath = css;
    width = w;
    height = h;
    root = new BorderPane();
    ls = new LanguageSelect();
    setupText();
    makeButtonLayout();
    elements = new VBox(50);
    elements.getChildren().addAll(gameText, buttonLayout, ls);
    elements.setAlignment(Pos.CENTER);
    root.setCenter(elements);
    directoryOpener = new DirectoryOpener();
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

  public LanguageSelect getLanguageSelect() {return ls;}

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
        File script = directoryOpener.fileChoice(myStage);
        myFileChoice = script;
        contSel.setDisable(false);
      } catch (Exception err) {
        LOG.error(err);
        ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), err.getMessage());
      }
    });
  }

  private Button makeButton(String buttonText) {
    Button b = new Button(buttonText);
    b.setId("opening-button");
    return b;
  }
}
