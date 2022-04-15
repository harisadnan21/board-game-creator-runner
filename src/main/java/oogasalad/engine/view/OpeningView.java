package oogasalad.engine.view;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.JSONObject;

public class OpeningView {
  public static final String TITLE = "oogabooga";
  public static final Pair<Integer, Integer> BUTTON_SIZE = new Pair<>(120, 45);

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
  private Button playGame;
  private FileOpener fileOpener;

  public OpeningView(double w, double h) {
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
    scene.setFill(Color.web("#EEEEEE"));

    return scene;
  }

  public Button getPlayGame() {
    return playGame;
  }

  public JSONObject getFileObject() {
    return fileObject;
  }

  private void setupText() {
    title = new Text(TITLE);
    title.setFont(Font.font("Montserrat", 48));
    title.setFill(Color.web("464646"));
    gameText = new VBox();
    gameText.getChildren().add(title);
    gameText.setAlignment(Pos.CENTER);
  }

  private void makeButtonLayout() {
    buttonLayout = new VBox(20);
    buttonLayout.setAlignment(Pos.CENTER);
    makeButtons();
    buttonLayout.getChildren().addAll(makeButtonRow(), playGame);
  }

  private HBox makeButtonRow() {
    HBox row = new HBox(20);
    row.getChildren().addAll(uploadFile, gameBuilder);
    row.setAlignment(Pos.CENTER);
    return row;
  }

  private void makeButtons() {
    uploadFile = makeButton("upload file");
    setupFileUpload();
    gameBuilder = makeButton("game builder");
    playGame = makeButton("play game");
    playGame.setDisable(true);
  }

  private void setupFileUpload() {
    Stage myStage = new Stage();
    uploadFile.setOnAction(e -> {
      try {
        File script = fileOpener.fileChoice(myStage);
        handleInput(fileOpener.getRootObject(script));
        playGame.setDisable(false);
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
    b.setFont(Font.font("Montserrat", 17));
    b.setMaxWidth(BUTTON_SIZE.getKey());
    b.setMaxHeight(BUTTON_SIZE.getValue());
    b.setMinWidth(BUTTON_SIZE.getKey());
    b.setMinHeight(BUTTON_SIZE.getValue());
    b.setAlignment(Pos.CENTER);
    return b;
  }
}
