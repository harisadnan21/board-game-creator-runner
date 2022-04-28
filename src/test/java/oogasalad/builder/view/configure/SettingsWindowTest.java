package oogasalad.builder.view.configure;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.builder.view.BuilderView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SettingsWindowTest extends DukeApplicationTest {

    private BuilderView builderView;
    private SettingsWindow setWindow;
    private VBox layout;

    @BeforeEach
    @Override
    public void start(Stage stage) {
        builderView = new BuilderView(stage);

        setWindow = new SettingsWindow(builderView, stage);
        layout = new VBox();
        layout.setId("rightPane");

    }

}