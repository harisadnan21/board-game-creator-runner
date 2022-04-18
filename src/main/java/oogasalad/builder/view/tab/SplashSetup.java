package oogasalad.builder.view.tab;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import oogasalad.builder.view.ViewResourcesSingleton;

import java.util.ResourceBundle;

import static oogasalad.builder.view.BuilderView.makeButton;
import static oogasalad.builder.view.BuilderView.displayWelcome;
public class SplashSetup extends Parent {


    /**
     * Creates a splash screen that allows the user to select a language before displaying the full
     * application
     *
     * @author Thivya Sivarajah
     */

    public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
    private static final String SPLASH_PACKAGE = "SplashLogin.css";
    private static String TAB_LANGUAGE = "English";
    private static String TAB_PROPERTIES = "tabResources";
    private static final String TAB_FORMAT = "tabFormat.css";
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};

    private Label myWelcome;
    private ResourceBundle splashResources;
    private ResourceBundle tabProperties;
    private ChoiceBox<String> languageBox;
    private VBox leftPanel;
    private VBox rightPanel;
    private VBox rightPanelElements;
    private BorderPane buttonHolder;
    private Button proceed;
    private static Stage stage;
    private Scene myLoginScene;


    public SplashSetup() {
        createElements();
        setupHolders(proceed);
        createScreen(proceed);
        stage = new Stage();
        stage.setScene(myLoginScene);
        stage.show();
    }

    private void createScreen(Button proceed) {
        myLoginScene = new Scene(buttonHolder, 600, 650);
        myLoginScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
        myWelcome.getStyleClass().add("myWelcome");
        leftPanel.getStyleClass().add("leftPanel");
        proceed.getStyleClass().add("proceed");
        buttonHolder.getStyleClass().add("buttonHolder");
    }

    private void createElements() {
        proceed = makeButton("Proceed", event -> {displayWelcome();});
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

    private void getLanguage(ActionEvent event) {
        String myLanguage = languageBox.getValue();
        ViewResourcesSingleton.getInstance().setLanguage(myLanguage);
        displayWelcome();

    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}

