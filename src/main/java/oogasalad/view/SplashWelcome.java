package oogasalad.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.engine.view.ViewManager;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SplashWelcome {
    public static final String DEFAULT_RESOURCE_PACKAGE = "/builder/view/css/";
    public static final String WELCOME_IMAGE = DEFAULT_RESOURCE_PACKAGE + "welcome.jpg";
    private static final String SPLASH_PACKAGE = "SplashWelcome.css";
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};



    private static final Logger LOG = LogManager.getLogger(SplashWelcome.class);

    private Label myWelcome;
    private BorderPane elementHolder;
    private HBox buttonHolder;
    private Button builder;
    private Button engine;
    private static Stage stage;
    private Scene myWelcomeScene;
    private ImageView myImageView;
    private ChoiceBox<String> languageBox;

    public SplashWelcome() {
        createElements();
        setupHolders();
        createScreen();
        stage = new Stage();
        stage.setScene(myWelcomeScene);
        stage.show();
    }


    private void createScreen() {
        myWelcomeScene = new Scene(elementHolder, 600, 650);
        myWelcomeScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
        elementHolder.getStyleClass().add("elementHolder");
        myWelcome.getStyleClass().add("myWelcome");
        myImageView.getStyleClass().add("image");
        builder.getStyleClass().add("proceed");
        engine.getStyleClass().add("proceed");

    }

    private void createElements() {
        Image welcomeImage = new Image(getClass().getResourceAsStream(WELCOME_IMAGE));
        myImageView = new ImageView(welcomeImage);
        builder = makeButton("Builder", e -> startBuilder());
        engine = makeButton("Engine", e-> startEngine());
        languageBox = new ChoiceBox<>();
        languageBox.getItems().addAll(languageChoice);
        languageBox.setOnAction(this::getLanguage);
        myWelcome = new Label(ViewResourcesSingleton.getInstance().getString("Welcome"));
    }

    private void setupHolders() {
        elementHolder = new BorderPane();
        buttonHolder = new HBox();
        buttonHolder.getChildren().addAll(builder, engine, languageBox);
        buttonHolder.setAlignment(Pos.BOTTOM_CENTER);
        elementHolder.setBottom(buttonHolder);
        elementHolder.setAlignment(buttonHolder, Pos.CENTER);
        elementHolder.setCenter(myImageView);
        elementHolder.setTop(myWelcome);
    }

    //returns a button with the title provided linked to the event passed as a parameter
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = ViewResourcesSingleton.getInstance().getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    private void startBuilder() {
        new BuilderController(new BuilderView(stage));
    }

    private void startEngine() {
        try {
            ViewManager manager = new ViewManager(stage);
            Scene scene = manager.getCurrScene();
            stage.setTitle("OOGABOOGA Engine");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            LOG.fatal(e);
        }
    }

    private void getLanguage(ActionEvent event) {
        String myLanguage = languageBox.getValue();
        ViewResourcesSingleton.getInstance().setLanguage(myLanguage);
        ExceptionResourcesSingleton.getInstance().setLanguage(myLanguage);
    }


}
