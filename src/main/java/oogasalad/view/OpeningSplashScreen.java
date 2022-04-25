package oogasalad.view;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.builder.BuilderMain;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.BuilderView;
import oogasalad.engine.view.ViewManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpeningSplashScreen {

  private static final Logger LOG = LogManager.getLogger(OpeningSplashScreen.class);

  private Text welcome;
  private Button gameBuilder;
  private Button gameEngine;
  private HBox buttons;
  private BorderPane root;
  private Stage stage;

  public OpeningSplashScreen(Stage stage) {
    this.stage = stage;
    gameBuilder = new Button("game builder");
    gameBuilder.setOnAction(e -> startBuilder());
    gameEngine = new Button("game engine");
    gameEngine.setOnAction(e -> startEngine());
    buttons = new HBox(20);
    buttons.getChildren().addAll(gameBuilder, gameEngine);
    buttons.setAlignment(Pos.CENTER);
    root = new BorderPane();
    root.setCenter(buttons);
  }

  public Scene makeScene() {
    Scene scene = new Scene(root, 600, 400);
    return scene;
  }

  private void startBuilder() {
    new BuilderController(new BuilderView(stage));
  }

  private void startEngine() {
    try {
      ViewManager manager = new ViewManager(stage);
      Scene scene = manager.getCurrScene();
      stage.setTitle("OOGABOOGA Engine");
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e) {
      LOG.fatal(e);
    }
  }

}