package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class SplashLogin extends Parent {


/**
 * Creates a splash screen that allows the user to select a language before displaying the full
 * application
 *
 * @author Thivya Sivarajah
 */

    //Pane myPane;
    ResourceBundle myResources;
    private Label myWelcome;

    private BorderPane boardPane;
    private HBox buttonHolder;

    public SplashLogin(ResourceBundle resources) {
        boardPane = new BorderPane();
        buttonHolder = new HBox();
        myResources = resources;

        createScreen();
    }

    private void createScreen() {
        createElements();
        boardPane.getChildren().add(buttonHolder);
        boardPane.setRight(myWelcome);
    }

    private void createElements() {
        buttonHolder.getChildren().clear();
        myWelcome = new Label(myResources.getString("Welcome"));
        myWelcome.setTextAlignment(TextAlignment.RIGHT);
        buttonHolder.getChildren().addAll(myWelcome);
    }


    public BorderPane getPane() {
        return boardPane;
    }

    public HBox getContainer() {
        return buttonHolder;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}
