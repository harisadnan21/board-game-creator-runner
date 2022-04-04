package oogasalad.builder;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.BuilderView;

public class BuilderMain extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    BuilderController controller = new BuilderController(stage);
  }
}
