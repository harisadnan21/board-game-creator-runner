package oogasalad.builder.view.tab;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class SplashLogin {


/**
 * Creates a splash screen that allows the user to select a language before displaying the full
 * application
 *
 * @author Thivya Sivarajah
 */

    Pane myPane;
    ResourceBundle myResources;
    private Label myWelcome;
    private VBox myContainer;

    public SplashLogin(ResourceBundle resources) {
        myPane = new StackPane();
        myContainer = new VBox();
        myResources = resources;

        createScreen();
    }

    private void createScreen() {
        createElements();
        myPane.getChildren().add(myContainer);
    }

    private void createElements() {
        myContainer.getChildren().clear();
        myWelcome = new Label(myResources.getString("Welcome"));
        myWelcome.setId("welcome-text");
        // myContainer.setId("opening-window");
        // myWelcome.setTextAlignment(TextAlignment.CENTER);
        myContainer.getChildren().addAll(myWelcome);
    }


    public Pane getPane() {
        return myPane;
    }

    public VBox getContainer() {
        return myContainer;
    }
}
