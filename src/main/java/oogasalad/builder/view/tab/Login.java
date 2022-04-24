package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.builder.view.ViewResourcesSingleton;

public class Login extends GridPane {
    /**
     * Creates a splash screen that allows the user to select a language before displaying the full
     * application
     *
     * @author Thivya Sivarajah
     */

    public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
    private static final String SPLASH_PACKAGE = "SplashLogin.css";
    private final EventHandler<ActionEvent> handler;
    private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};
    private static Stage stage;
    private Scene myLoginScene;
    private GridPane loginHere;



    public Login(EventHandler<ActionEvent> handler) {
        this.handler = handler;
        createElements();
        createScreen();
    }

    private void createElements() {
        loginHere = new GridPane();
        loginHere.setAlignment(Pos.CENTER);
        loginHere.setHgap(10);
        loginHere.setVgap(10);
        loginHere.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        loginHere.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        loginHere.add(userName, 0, 1);

        TextField userTextField = new TextField();
        loginHere.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        loginHere.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        loginHere.add(pwBox, 1, 2);

        Button signin = makeButton("Signin", this::exitSplash);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signin);
        loginHere.add(hbBtn, 1, 4);
    }

    private void createScreen() {
        myLoginScene = new Scene(loginHere, 600, 650);
        stage = new Stage();
        stage.setScene(myLoginScene);
        stage.show();

    }

    //returns a button with the title provided linked to the event passed as a parameter
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = ViewResourcesSingleton.getInstance().getString(property);
        result.setText(label);
        result.setOnAction(handler);
        changeFontsButton(result);
        return result;
    }

    private void changeFontsButton(Button changeButton) {
        if(FormatTab.FANCY == 1) { changeButton.setFont(Font.font("Papyrus")); }
        if(FormatTab.PRESENTATION == 1) { changeButton.setFont(Font.font("Lucida Sans")); }
        if(FormatTab.NORMAL == 1) { changeButton.setFont(Font.font("Comic Sans")); }
    }

    //Exits the splash screen, moving to the builder view
    private void exitSplash(ActionEvent e) {
        handler.handle(e);
        stage.close();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
