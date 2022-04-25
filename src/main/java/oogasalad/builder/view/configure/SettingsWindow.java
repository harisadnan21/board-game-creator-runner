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

    private static final String SPLASH_PACKAGE = "SplashLogin.css";
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};


    private VBox topLayout;
    private VBox layout;
    private Button returnToGame;
    private HBox themeMenu;
    private Text header;
    private Text theme;
    private CSSSelect cssDropdown;
    private MouseSoundEntry mouseSoundEntry;
    private Stage newStage;
    private BuilderView ourBuild;


    public SettingsWindow(BuilderView build, Stage stage) {
        layout = new VBox();
        layout.setId("rightPane");
        newStage = stage;
        ourBuild = build;
        makeHeader();
        makeTheme();
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

    public CSSSelect getCssDropdown() {
        return cssDropdown;
    }

    private void makeHeader() {
        topLayout = new VBox();
        topLayout.setId("titleBox");
        header = new Text(ResourceBundle.getBundle("/builder/view/languages/" + "English").getString("settings"));
        header.setId("titleText");
        topLayout.getChildren().add(header);
//        root.setTop(topLayout);
        layout.getChildren().add(topLayout);
        layout.setAlignment(Pos.TOP_CENTER);
    }

    private void makeTheme() {
        FormatDropDown themeSelector = new FormatDropDown(ourBuild);
    }

//    private void makeFont() {
//        themeMenu = new HBox();
//        themeMenu.setId("theme-menu");
//        theme = new Text(myResources.getString("ThemeSelect"));
//        theme.setId("theme-text");
//        cssDropdown = new CSSSelect();
//        themeMenu.getChildren().addAll(theme, cssDropdown);
//        layout.getChildren().add(themeMenu);
//    }

    private void makeButton() {
        returnToGame = new Button(ResourceBundle.getBundle("/builder/view/languages/" + "English").getString("Return"));
        returnToGame.setId("message-screen-button");
        layout.getChildren().add(returnToGame);
    }


    @Override
    public void setup() {

    }
}
