package oogasalad.builder;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.BuilderView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuilderMain extends Application {

  @Override
  public void start(Stage stage) {
    final Logger logger = LogManager.getLogger(BuilderMain.class);
    try {
      BuilderController controller = new BuilderController(new BuilderView(stage));
      Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
        logger.throwing(throwable);
        controller.showError(throwable);
      });
    } catch(Exception e) {
      logger.error("Exception occurred while initializing builder", e);
    }
  }
}
