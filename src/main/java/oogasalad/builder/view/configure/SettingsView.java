package oogasalad.builder.view.configure;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class SettingsView {
    public static final String DEFAULT_RESOURCE_PACKAGE = "/builder/view/languages/";
    protected ResourceBundle myResources;
    protected String cssFilePath;
    protected Stage popupStage;
    protected BorderPane root;
    protected String langauge;

    public SettingsView() {
        String language = new String();
        String css = new String();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        cssFilePath = "/builder/view/css/";

        root = new BorderPane();
        root.setId("settings");
        makeStage();
    }

    protected void makeStage() {
        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(cssFilePath).toExternalForm());
        popupStage.setScene(scene);
    }

    protected abstract void setup();

}
