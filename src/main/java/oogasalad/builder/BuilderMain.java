package oogasalad.builder;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.BuilderView;

public class BuilderMain extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // TODO make this trigger a popup
    // Also possibly need to make this only apply to builder threads and not all threads
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      System.err.println("MAKE A REAL UNCAUGHT EXCEPTION HANDLER");
      e.printStackTrace();
    });
    BuilderController controller = new BuilderController(new BuilderView(stage));
  }
}
