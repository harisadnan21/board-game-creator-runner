package oogasalad.engine;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.BoardView;

import oogasalad.engine.view.ViewManager;

/**
 * Class that runs the engine and starts it off
 */
public class EngineApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    ViewManager manager = new ViewManager(stage);

    Scene scene = manager.getCurrScene();
    //scene.getStylesheets().add(getClass().getResource("/css/engine.css").toExternalForm());

    stage.setTitle("OOGABOOGA Engine");
    stage.setScene(scene);
    stage.show();
  }

}
