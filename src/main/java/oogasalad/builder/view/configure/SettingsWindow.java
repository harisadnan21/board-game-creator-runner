package oogasalad.builder.view.configure;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.builder.view.BuilderView;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.Popup.MouseSoundEntry;

import java.util.ResourceBundle;


public class SettingsWindow implements SettingsView {
    /**
     * Creates a splash screen that allows the user to select a language before displaying the full
     * application
     *
     * @author Thivya Sivarajah
     */

    public static final String THEME_OPTIONS_PATH = "FormatOptions";
    public static final String FONT_OPTIONS_PATH = "FontList";


    private VBox topLayout;
    private VBox middleLayout;
    private VBox layout;
    private VBox bottomLayout;
    private Button returnToGame;
    private Text header;
    private Stage newStage;
    private BuilderView ourBuild;


    public SettingsWindow(BuilderView build, Stage stage) {
        layout = new VBox();
        layout.setId("rightPane");
        newStage = stage;
        ourBuild = build;
        makeHeader();
        makeTheme();
        makeFont();
        makeButton();
        makeStage();

    }

    @Override
    public void makeStage() {
        newStage = new Stage(StageStyle.TRANSPARENT);
        newStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/builder/view/css/tabFormat.css").toExternalForm());
        newStage.setScene(scene);
        newStage.show();
    }

    public Button getReturnToGame() {
        return returnToGame;
    }

    private void makeHeader() {
        topLayout = new VBox();
        topLayout.setId("titleBox");
        header = new Text(ResourceBundle.getBundle("/builder/view/languages/" + "English").getString("settings"));
        header.setId("titleText");
        topLayout.getChildren().add(header);
//        root.setTop(topLayout);
        topLayout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(topLayout);
        layout.setAlignment(Pos.CENTER);
    }

    private void makeTheme() {
        middleLayout = new VBox();
        FormatDropDown themeSelector = new FormatDropDown(ourBuild, THEME_OPTIONS_PATH);
        middleLayout.getChildren().addAll(themeSelector);
        middleLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(middleLayout);

    }

    private void makeFont() {
        bottomLayout = new VBox();
        FormatDropDown fontSelector = new FormatDropDown(ourBuild, FONT_OPTIONS_PATH);
        bottomLayout.getChildren().addAll(fontSelector);
        middleLayout.setAlignment(Pos.BOTTOM_CENTER);
        layout.getChildren().addAll(bottomLayout);
    }

    private void makeButton() {
        returnToGame = new Button(ResourceBundle.getBundle("/builder/view/languages/" + "English").getString("Return"));
        returnToGame.setId("message-screen-button");
        layout.getChildren().add(returnToGame);
    }


    @Override
    public void setup() {

    }
}
