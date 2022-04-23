package oogasalad;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oogasalad.view.OpeningSplashScreen;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        OpeningSplashScreen opening = new OpeningSplashScreen(stage);
        Scene scene = opening.makeScene();
        stage.setTitle("OOGABOOGA");
        stage.setScene(scene);
        stage.show();
    }
}
