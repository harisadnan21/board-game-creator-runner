package oogasalad.engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import oogasalad.engine.view.ViewManager.ViewManager;

/**
 * Class that runs the engine and starts it off
 */
public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    ViewManager manager = new ViewManager(stage);

    Scene scene = manager.getCurrScene();

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

}
