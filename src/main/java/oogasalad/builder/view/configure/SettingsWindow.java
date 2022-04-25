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
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.Popup.MouseSoundEntry;

import java.util.ResourceBundle;

import static oogasalad.builder.view.BuilderView.DEFAULT_PROPERTY_PACKAGE;


public class SettingsWindow implements SettingsView {
    /**
     * Creates a splash screen that allows the user to change font and background
     *
     * @author Thivya Sivarajah
     */

    public static final String THEME_OPTIONS_PATH = "FormatOptions";
    public static final String FONT_OPTIONS_PATH = "FontList";
    public static final String DEFAULT_STYLE_PACKAGE = "/builder/view/css/";
    public static final String DEFAULT_TAB_FORMAT = "tabFormat.css";
    public static final String STYLE_LABEL = "formatPrompt";
    public static final String FONT_LABEL = "fontPrompt";


    private VBox topLayout;
    private VBox middleLayout;
    private VBox layout;
    private VBox bottomLayout;
    private Button returnToGame;
    private Text header;
    private Stage newStage;
    private BuilderView ourBuild;
    private FormatDropDown fontSelector;
    private FormatDropDown themeSelector;

    // set up our mini pop-up screen
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

    // display our elements on the stage
    @Override
    public void makeStage() {
        newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLE_PACKAGE + DEFAULT_TAB_FORMAT).toExternalForm());
        newStage.setScene(scene);
        newStage.show();
    }

    // make header for our mini pop up
    private void makeHeader() {
        topLayout = new VBox();
        topLayout.setId("titleBox");
        header = new Text(ViewResourcesSingleton.getInstance().getString("settings"));
        header.setId("titleText");
        topLayout.getChildren().add(header);
        topLayout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(topLayout);
        layout.setAlignment(Pos.CENTER);
    }

    // create special combo box for choosing theme
    private void makeTheme() {
        middleLayout = new VBox();
        themeSelector = new FormatDropDown(ourBuild, THEME_OPTIONS_PATH, STYLE_LABEL);
        middleLayout.getChildren().addAll(themeSelector);
        middleLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(middleLayout);

    }

    // create special combo box for choosing font
    private void makeFont() {
        bottomLayout = new VBox();
        fontSelector = new FormatDropDown(ourBuild, FONT_OPTIONS_PATH, FONT_LABEL);
        bottomLayout.getChildren().addAll(fontSelector);
        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        layout.getChildren().addAll(bottomLayout);
    }

    // make button to apply our changes
    private void makeButton() {
        returnToGame = new Button(ViewResourcesSingleton.getInstance().getString("Return"));
        returnToGame.setId("message-screen-button");
        layout.getChildren().add(returnToGame);
        returnToGame.setOnAction(e -> applyChanges());
    }

    // apply our theme and font changes
    private void applyChanges() {
        ourBuild.setFormat(themeSelector.getFormat(), fontSelector.getFormat());
    }

    @Override
    public void setup() {

    }
}
