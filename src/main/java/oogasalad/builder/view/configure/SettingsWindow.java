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

import java.util.ResourceBundle;

import static oogasalad.builder.view.BuilderView.DEFAULT_PROPERTY_PACKAGE;


public class SettingsWindow implements SettingsView {
    /**
     * Creates a splash screen that allows the user to change font and background
     *
     * @author Thivya Sivarajah
     */


    public static final String DEFAULT_STYLE_PACKAGE = "/builder/view/css/";
    public static final String DEFAULT_TAB_FORMAT = "tabFormat.css";
    public static final String THEME = "theme";
    public static final String FONT = "font";


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
        newStage = stage;
        ourBuild = build;
        setup();
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

    public FormatDropDown getTheme() {
        return themeSelector;
    }

    public FormatDropDown getFont() {
        return fontSelector;
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
        themeSelector = new FormatDropDown(THEME);
        middleLayout.getChildren().addAll(themeSelector);
        middleLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(middleLayout);

    }

    // create special combo box for choosing font
    private void makeFont() {
        bottomLayout = new VBox();
        fontSelector = new FormatDropDown(FONT);
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
        layout = new VBox();
        layout.setId("rightPane");
        makeHeader();
        makeTheme();
        makeFont();
        makeButton();
        makeStage();
    }
}
