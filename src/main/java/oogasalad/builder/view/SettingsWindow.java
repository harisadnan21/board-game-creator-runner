package oogasalad.builder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Enumeration;
import java.util.ResourceBundle;

import static oogasalad.builder.view.BuilderView.DEFAULT_PROPERTY_PACKAGE;
import static oogasalad.builder.view.BuilderView.DEFAULT_STYLE_PACKAGE;

public class SettingsWindow extends Pane {
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
    private BorderPane buttonHolder;
    private Button proceed;
    private static Stage stage;
    private Scene myLoginScene;
    public static final String STYLE_OPTIONS_PATH = "FormatOptions";
    ResourceBundle styleOptions;
    private ComboBox<String> languages;
    // private final EventHandler<ActionEvent> handler;

//    public SplashLogin(EventHandler<ActionEvent> handler) {
//        this.handler = handler;
//        createElements();
//        setupHolders(proceed);
//        createScreen(proceed);
//        stage = new Stage();
//        stage.setScene(myLoginScene);
//        stage.show();
//    }

    public SettingsWindow() {
        //this.handler = handler;
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
        //myWelcome.getStyleClass().add("myWelcome");
        //leftPanel.getStyleClass().add("leftPanel");
        //proceed.getStyleClass().add("proceed");
        buttonHolder.getStyleClass().add("buttonHolder");
    }

    private void createElements() {
        //proceed = makeButton("Proceed", this::exitSplash);
        //proceed.setId("loginButton");
        //myWelcome = new Label(ViewResourcesSingleton.getInstance().getString("Welcome"));
        languageBox = new ChoiceBox<>();
        languageBox.getItems().addAll(languageChoice);
        languageBox.setOnAction(this::getLanguage);
    }

    private void setupHolders(Button proceed) {
        buttonHolder = new BorderPane();
        leftPanel = new VBox();
        rightPanel = new VBox();
        //leftPanel.getChildren().addAll(myWelcome);
//        rightPanel.getChildren().addAll(proceed, languageBox);
        rightPanel.getChildren().addAll(languageBox);
        rightPanel.setAlignment(Pos.CENTER);
        //buttonHolder.setLeft(leftPanel);
        buttonHolder.setCenter(rightPanel);
        buttonHolder.setAlignment(rightPanel, Pos.CENTER);
    }

//    //Exits the splash screen, moving to the builder view
//    private void exitSplash(ActionEvent e) {
//        handler.handle(e);
//        stage.close();
//    }

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

    private void FormatDropDown(BuilderView view){
        fillDropDown();
        languages = new ComboBox<>();
        languages.setPromptText(ViewResourcesSingleton.getInstance().getString("formatPrompt"));
        languages.valueProperty().addListener((observable, oldValue, newValue) -> view.setFormat(
                styleOptions.getString(
                        ViewResourcesSingleton.getInstance().getString(newValue.replace(" ", "_")))));
    }

    //fills DropDown from a file
    private void fillDropDown() {
        styleOptions = ResourceBundle.getBundle(
                DEFAULT_PROPERTY_PACKAGE + STYLE_OPTIONS_PATH);
        Enumeration keys = styleOptions.getKeys();
        while (keys.asIterator().hasNext()) {
            languages.getItems()
                    .add(ViewResourcesSingleton.getInstance().getString((String) keys.nextElement()));
        }
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

//
//    @Override
//    public Node getStyleableNode() {
//        return super.getStyleableNode();
//    }
}
