package oogasalad.builder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.builder.view.callback.Callback;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.CallbackHandler;
import oogasalad.builder.view.tab.ActionsTab;
import oogasalad.builder.view.tab.ConditionsTab;
import oogasalad.builder.view.tab.RulesTab;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.PiecesTab;
import java.util.ResourceBundle;

/**
 *
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static final String SPLASH_PACKAGE = "SplashLogin";
  private static final String TAB_LANGUAGE = "English";
  private static final String TAB_FORMAT = "tabFormat.css";

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

  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  public BuilderView(Stage mainStage) {
    splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_LANGUAGE);
    stage = mainStage;
    displayWelcome(stage);
    stage.show();
  }

  private void displayWelcome(Stage myStage) {
    boardPane = new BorderPane();
    buttonHolder = new HBox();
    myWelcome = new Label(splashResources.getString("Welcome"));
    myWelcome.setFont(new Font("Inter", 30));
    boardPane.setLeft(myWelcome);

    Button login = makeButton("Login", event -> {
      setupTabs();
    }, splashResources);
    login.setId("loginButton");
    login.setStyle("-fx-border-color: #fcba03; -fx-border-width: 2px; -fx-background-color: #fffaef; ");
    buttonHolder.getChildren().add(login);
    buttonHolder.setAlignment(Pos.CENTER_LEFT);
    boardPane.setCenter(buttonHolder);

    Scene myLoginScene = new Scene(boardPane, 600, 650);
    boardPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), Insets.EMPTY)));
    myStage.setScene(myLoginScene);
    myStage.show();
  }

  // Can possibly extend tab for each of the tab classes instead of just having toNode() to add them to the tabs
  // public void setupTabs() {
  // Creates all the tabs
  private void setupTabs() {
    TabPane tabPane = new TabPane();

    boardTabPane = new BoardTab(tabResources, callbackDispatcher);
    boardTabPane.setId("boardTab");
    Tab boardTab = new Tab("Board", boardTabPane);

    pieceTabPane = new PiecesTab(callbackDispatcher);
    pieceTabPane.setId("pieceTab");
    Tab pieceTab = new Tab("Piece", pieceTabPane);
    actionsTabPane = new ActionsTab(callbackDispatcher);
    actionsTabPane.setId("actionTab");
    Tab actionTab = new Tab("Action", actionsTabPane);
    conditionsTabPane = new ConditionsTab(callbackDispatcher);
    conditionsTabPane.setId("conditionTab");
    Tab conditionsTab = new Tab("Condition", conditionsTabPane);
    rulesTabPane = new RulesTab(callbackDispatcher);
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

  //public int[][] getBoardConfig(){
  public int[][] getBoardConfig() {
    return boardTabPane.getBoardConfig();
  }

  /**
   * Register a handler to be used when a given type of callback is needed
   * @param callback the callback to handle
   * @param handler the handler that can handle that type of callback
   * @param <R> the type that the handler must return
   * @param <C> the type of the callback
   */
  public <R, C extends Callback<R>> void registerCallbackHandler(Class<C> callback, CallbackHandler<R, C> handler) {
    callbackDispatcher.registerCallbackHandler(callback, handler);
  }
}

