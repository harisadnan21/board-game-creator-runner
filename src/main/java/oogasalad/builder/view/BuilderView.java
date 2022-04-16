package oogasalad.builder.view;

import java.util.Locale.Builder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.element.ElementRecord;

import javafx.scene.Scene;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.builder.view.tab.ActionsTab;
import oogasalad.builder.view.tab.ConditionsTab;
import oogasalad.builder.view.tab.RulesTab;
import oogasalad.builder.view.tab.SplashLogin;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.pieceTab.PiecesTab;
import java.util.ResourceBundle;

/**
 *
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static final String SPLASH_PACKAGE = "SplashLogin.css";
  // private static final String TAB_LANGUAGE = "English";
  private static String TAB_LANGUAGE = "English";
  private static final String TAB_FORMAT = "tabFormat.css";
  private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};

  private Stage stage;
  private BoardTab boardTabPane;
  private PiecesTab pieceTabPane;
  private ActionsTab actionsTabPane;
  private ConditionsTab conditionsTabPane;
  private RulesTab rulesTabPane;
  private Label myWelcome;
  private BorderPane boardPane;
  private HBox buttonHolder;
  private ResourceBundle splashResources;
  private ResourceBundle tabResources;
  private ChoiceBox<String> languageBox;
  private Label myLabel;

  private BuilderController controller; //FIXME: Use Event handlers instead of this

  public BuilderView(Stage mainStage) {
    //splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_LANGUAGE);
    controller = new BuilderController();
    stage = mainStage;
    displayWelcome();
    //stage.show();
  }

  private void displayWelcome() {
    boardPane = new BorderPane();
    buttonHolder = new HBox();
    myWelcome = new Label(tabResources.getString("Welcome"));
    myWelcome.setFont(new Font("Inter", 30));
    //boardPane.getChildren().add(myWelcome);
    boardPane.setLeft(myWelcome);

    Button login = makeButton("Proceed", event -> {setupTabs();}, tabResources);
    languageBox = new ChoiceBox<>();
    languageBox.getItems().addAll(languageChoice);
    languageBox.setOnAction(this::getLanguage);
    buttonHolder.getChildren().addAll(login, languageBox);

    //boardPane.setCenter(languageBox);
    //boardPane.setBottom(buttonHolder);

    Scene myLoginScene = new Scene(boardPane, 600, 650);
    myLoginScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
    boardPane.getStyleClass().add("boardPane");
    languageBox.getStyleClass().add("languageBox");
    login.getStyleClass().add("buttonHolder");
    boardPane.setCenter(buttonHolder);
    //boardPane.setBottom(buttonHolder);
    stage = new Stage();
    stage.setScene(myLoginScene);
    stage.show();
   // myStage.setScene(myLoginScene);
    //myStage.show();
  }

  // Can possibly extend tab for each of the tab classes instead of just having toNode() to add them to the tabs
  // public void setupTabs() {
  // Creates all the tabs
  private void setupTabs() {
    TabPane tabPane = new TabPane();

    boardTabPane = new BoardTab(tabResources, controller);
    boardTabPane.setId("boardTab");
    Tab boardTab = new Tab("Board", boardTabPane);

    pieceTabPane = new PiecesTab(controller);
    pieceTabPane.setId("pieceTab");
    Tab pieceTab = new Tab("Piece", pieceTabPane);
    actionsTabPane = new ActionsTab(controller);
    actionsTabPane.setId("actionTab");
    Tab actionTab = new Tab("Action", actionsTabPane);
    conditionsTabPane = new ConditionsTab(controller);
    conditionsTabPane.setId("conditionTab");
    Tab conditionsTab = new Tab("Condition", conditionsTabPane);
    rulesTabPane = new RulesTab(controller);
    rulesTabPane.setId("ruleTab");
    Tab rulesTab = new Tab("Rule", rulesTabPane);

    tabPane.getTabs().addAll(boardTab, pieceTab, actionTab, conditionsTab, rulesTab);

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    //TODO get this size from file and add more to the scene
    Scene tabScene = new Scene(tabPane, Integer.parseInt(tabResources.getString("sceneSizeX")),
        Integer.parseInt(tabResources.getString("sceneSizeY")));

    tabScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + TAB_FORMAT).toExternalForm());
    stage.setScene(tabScene);
  }

  //returns a button with the title provided linked to the event passed as a parameter
  public static Button makeButton(String property, EventHandler<ActionEvent> handler,
                                  ResourceBundle resources) {
    Button result = new Button();
    String label = resources.getString(property);
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  private void getLanguage(ActionEvent event) {
    String myLanguage = languageBox.getValue();
    //myLabel.setText(myLanguage);
    TAB_LANGUAGE = myLanguage;
    tabResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_LANGUAGE);
    displayWelcome();

  }

}

