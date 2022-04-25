package oogasalad.builder.view.configure;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.builder.view.BuilderView;

public interface SettingsView {


    default void makeStage() {}


    void setup();

}
