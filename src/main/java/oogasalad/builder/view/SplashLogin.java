package oogasalad.builder.view;

import static oogasalad.builder.view.BuilderView.DEFAULT_STYLE_PACKAGE;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SplashLogin extends Parent {


/**
 * Creates a splash screen that allows the user to select a language before displaying the full
 * application
 *
 * @author Thivya Sivarajah
 */

    private static final String SPLASH_PACKAGE = "SplashLogin.css";
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};

    private Label myWelcome;
    private ChoiceBox<String> languageBox;
    private VBox leftPanel;
    private VBox rightPanel;
    private VBox rightPanelElements;
    private BorderPane buttonHolder;
    private Button proceed;
    private static Stage stage;
    private Scene myLoginScene;
    private final EventHandler<ActionEvent> handler;


    public SplashLogin(EventHandler<ActionEvent> handler) {
        this.handler = handler;
        createElements();
        setupHolders(proceed);
        createScreen(proceed);
        stage = new Stage();
        stage.setScene(myLoginScene);
        stage.show();
    }

    private void createScreen(Button proceed) {
        myLoginScene = new Scene(buttonHolder, 600, 650);
        myLoginScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
        myWelcome.getStyleClass().add("myWelcome");
        leftPanel.getStyleClass().add("leftPanel");
        proceed.getStyleClass().add("proceed");
        buttonHolder.getStyleClass().add("buttonHolder");
    }

    private void createElements() {
        proceed = makeButton("Proceed", this::exitSplash);
        proceed.setId("loginButton");
        myWelcome = new Label(ViewResourcesSingleton.getInstance().getString("Welcome"));
        languageBox = new ChoiceBox<>();
        languageBox.getItems().addAll(languageChoice);
        languageBox.setOnAction(this::getLanguage);
    }

    private void setupHolders(Button proceed) {
        buttonHolder = new BorderPane();
        leftPanel = new VBox();
        rightPanel = new VBox();
        rightPanelElements = new VBox();
        leftPanel.getChildren().addAll(myWelcome);
        rightPanelElements.getChildren().addAll(proceed, languageBox);
        rightPanel.getChildren().addAll(rightPanelElements);
        rightPanelElements.setAlignment(Pos.CENTER);
        rightPanel.setAlignment(Pos.CENTER);
        buttonHolder.setLeft(leftPanel);
        buttonHolder.setRight(rightPanel);
    }

    //Exits the splash screen, moving to the builder view
    private void exitSplash(ActionEvent e) {
        handler.handle(e);
        stage.close();
    }

    private void getLanguage(ActionEvent event) {
        String myLanguage = languageBox.getValue();
        ViewResourcesSingleton.getInstance().setLanguage(myLanguage);
    }

    //returns a button with the title provided linked to the event passed as a parameter
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = ViewResourcesSingleton.getInstance().getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}
