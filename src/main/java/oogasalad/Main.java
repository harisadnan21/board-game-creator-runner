package oogasalad;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import oogasalad.builder.BuilderMain;
import oogasalad.view.OpeningSplashScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        setupErrorHandling();
        OpeningSplashScreen opening = new OpeningSplashScreen(stage);
        Scene scene = opening.makeScene();
        stage.setTitle("OOGABOOGA");
        stage.setScene(scene);
        stage.show();
    }

    private void setupErrorHandling() {
        final Logger backupLogger = LogManager.getLogger(Main.class);
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            Logger logger = throwable.getStackTrace().length > 0 ? LogManager.getLogger(throwable.getStackTrace()[0].getClassName()) : backupLogger;
            logger.throwing(throwable);
            new Alert(Alert.AlertType.ERROR, throwable.getMessage()).showAndWait();
        });
    }
}
