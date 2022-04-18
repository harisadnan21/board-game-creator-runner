package oogasalad.builder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.Scene;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.builder.view.callback.Callback;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.CallbackHandler;
import oogasalad.builder.view.tab.ActionsTab;
import oogasalad.builder.view.tab.ConditionsTab;
import oogasalad.builder.view.tab.MetaDataTab;
import oogasalad.builder.view.tab.RulesTab;
import oogasalad.builder.view.tab.PiecesTab;
import oogasalad.builder.view.tab.SplashLogin;
import oogasalad.builder.view.tab.boardTab.BoardTab;


import java.util.ResourceBundle;

/** Creates the scene and handles the builder GUI and the tabs within it
 * @author Mike Keohane
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static final String SPLASH_PACKAGE = "SplashLogin.css";
  private static String TAB_LANGUAGE = "English";
  private static String TAB_PROPERTIES = "tabResources";
  private static final String TAB_FORMAT = "tabFormat.css";
  private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};

  private static Stage stage;
  private BoardTab boardTabPane;
  private PiecesTab pieceTabPane;
  private ActionsTab actionsTabPane;
  private ConditionsTab conditionsTabPane;
  private RulesTab rulesTabPane;
  private MetaDataTab metadataTabPane;
  private Label myWelcome;
  private BorderPane boardPane;
  private HBox buttonHolder;
  private ResourceBundle splashResources;
  private ResourceBundle tabProperties;
  private ChoiceBox<String> languageBox;

  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  public BuilderView(Stage mainStage) {
    //splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_PROPERTIES);
    stage = mainStage;
    displayWelcome();
  }

  public static void displayWelcome() {
    SplashLogin newWindow = new SplashLogin();
  }

  /**
   * Setups all of the tabs and adds them to the scene
   */
  public void setupTabs() {
    TabPane tabPane = new TabPane();

    boardTabPane = new BoardTab(callbackDispatcher);
    boardTabPane.setId("boardTab");
    Tab boardTab = new Tab(ViewResourcesSingleton.getInstance().getString("board"), boardTabPane);
    pieceTabPane = new PiecesTab(callbackDispatcher);
    pieceTabPane.setId("pieceTab");
    Tab pieceTab = new Tab(ViewResourcesSingleton.getInstance().getString("piece"), pieceTabPane);
    actionsTabPane = new ActionsTab(callbackDispatcher);
    actionsTabPane.setId("actionTab");
    Tab actionTab = new Tab(ViewResourcesSingleton.getInstance().getString("action"), actionsTabPane);
    conditionsTabPane = new ConditionsTab(callbackDispatcher);
    conditionsTabPane.setId("conditionTab");
    Tab conditionsTab = new Tab(ViewResourcesSingleton.getInstance().getString("condition"), conditionsTabPane);
    rulesTabPane = new RulesTab(callbackDispatcher);
    rulesTabPane.setId("ruleTab");
    Tab rulesTab = new Tab(ViewResourcesSingleton.getInstance().getString("rule"), rulesTabPane);
    metadataTabPane = new MetaDataTab(callbackDispatcher);
    metadataTabPane.setId("metadataTab");
    Tab metadataTab = new Tab(ViewResourcesSingleton.getInstance().getString("metadata"), metadataTabPane);

    tabPane.getTabs().addAll(boardTab, pieceTab, actionTab, conditionsTab, rulesTab, metadataTab);

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    Scene tabScene = new Scene(tabPane, Integer.parseInt(tabProperties.getString("sceneSizeX")),
        Integer.parseInt(tabProperties.getString("sceneSizeY")));

    tabScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + TAB_FORMAT).toExternalForm());
    stage.setScene(tabScene);
  }

  //returns a button with the title provided linked to the event passed as a parameter
  public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    String label = ViewResourcesSingleton.getInstance().getString(property);
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  public void showError(Throwable t) {
    new Alert(Alert.AlertType.ERROR, t.getMessage()).showAndWait();
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

