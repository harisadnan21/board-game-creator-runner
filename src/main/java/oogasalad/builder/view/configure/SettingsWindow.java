package oogasalad.builder.view.configure;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.builder.view.configure.SettingsView;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.OptionSelect.MouseSoundSelect;
import oogasalad.engine.view.Popup.MouseSoundEntry;

import java.util.ResourceBundle;


public class SettingsWindow extends SettingsView {
    /**
     * Creates a splash screen that allows the user to select a language before displaying the full
     * application
     *
     * @author Thivya Sivarajah
     */

    private static final String SPLASH_PACKAGE = "SplashLogin.css";
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};


    private VBox topLayout;
    private GridPane layout;
    private Button returnToGame;
    private HBox themeMenu;
    private Text header;
    private Text theme;
    private CSSSelect cssDropdown;
    private MouseSoundEntry mouseSoundEntry;


    public SettingsWindow() {
//        super(css, language);
        //myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        setup();
    }

    public Stage getStage() {
        return popupStage;
    }

    public Button getReturnToGame() {
        return returnToGame;
    }

    public CSSSelect getCssDropdown() {
        return cssDropdown;
    }

    public MouseSoundSelect getSoundDropdown() {return (MouseSoundSelect) mouseSoundEntry.getDropdown();}

    @Override
    protected void setup() {
        layout = new GridPane();
        layout.setId("settings-layout");
        makeHeader();
        makeTheme();
        makeButton();
        root.setCenter(layout);
    }

    private void makeHeader() {
        topLayout = new VBox();
        topLayout.setId("header-layout");
        header = new Text(ResourceBundle.getBundle("/builder/view/languages/" + "English").getString("settings"));
        header.setId("settings-header");
        topLayout.getChildren().add(header);
//        root.setTop(topLayout);
        layout.add(header, 2, 0, 4, 4);
    }

    private void makeTheme() {
//        themeMenu = new NewSettings();
//        themeMenu.setId("theme-menu");
//        theme = new Text(myResources.getString("ThemeSelect"));
//        theme.setId("theme-text");
//        cssDropdown = new CSSSelect();
//        themeMenu.getChildren().addAll(theme, cssDropdown);
//        layout.getChildren().add(themeMenu);
    }

    private void makeButton() {
        returnToGame = new Button(myResources.getString("Resume"));
        returnToGame.setId("message-screen-button");
        layout.getChildren().add(returnToGame);
    }
}
