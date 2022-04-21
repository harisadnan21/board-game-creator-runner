package oogasalad.builder.view.tab.boardTab;

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
import oogasalad.builder.view.ViewResourcesSingleton;


public class SplashWelcome {
    public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
    public static final String WELCOME_IMAGE = DEFAULT_RESOURCE_PACKAGE + "welcome.jpg";
    private static final String SPLASH_PACKAGE = "SplashWelcome.css";
    
    private Label myWelcome;
    private BorderPane elementHolder;
    private HBox buttonHolder;
    private Button proceed;
    private static Stage stage;
    private Scene myWelcomeScene;
    private final EventHandler<ActionEvent> handler;
    private ImageView myImageView;

    public SplashWelcome(EventHandler<ActionEvent> handler) {
        this.handler = handler;
        createElements();
        setupHolders(proceed);
        createScreen(proceed);
        stage = new Stage();
        stage.setScene(myWelcomeScene);
        stage.show();
    }


    private void createScreen(Button proceed) {
        myWelcomeScene = new Scene(elementHolder, 600, 650);
        myWelcomeScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
        elementHolder.getStyleClass().add("elementHolder");
        myWelcome.getStyleClass().add("myWelcome");
        myImageView.getStyleClass().add("image");
        proceed.getStyleClass().add("proceed");

    }

    private void createElements() {
        Image welcomeImage = new Image(getClass().getResourceAsStream(WELCOME_IMAGE));
        myImageView = new ImageView(welcomeImage);
        proceed = makeButton("Proceed", this::exitSplash);
        myWelcome = new Label(ViewResourcesSingleton.getInstance().getString("Welcome"));
    }

    private void setupHolders(Button proceed) {
        elementHolder = new BorderPane();
        buttonHolder = new HBox();
        elementHolder.setBottom(proceed);
        elementHolder.setAlignment(proceed, Pos.CENTER);
        elementHolder.setCenter(myImageView);
        elementHolder.setTop(myWelcome);
    }

    //Exits the splash screen, moving to the builder view
    private void exitSplash(ActionEvent e) {
        handler.handle(e);
        stage.close();
    }

    //returns a button with the title provided linked to the event passed as a parameter
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = ViewResourcesSingleton.getInstance().getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }


}
