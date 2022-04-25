package oogasalad.builder.view.configure;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.builder.view.BuilderView;

/**
 * API for the Settings tab. Allows us to have a small pop up within the actual builder, instead of using tabs
 *
 * @author Thivya Sivarajah
 */
public interface SettingsView {

    /**
     * displays the elements
     */
    default void makeStage() {}

    /**
     * loads the elements in a scene and
     */
    void setup();

}
