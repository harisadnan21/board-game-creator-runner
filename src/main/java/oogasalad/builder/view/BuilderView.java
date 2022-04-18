package oogasalad.builder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.Scene;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oogasalad.builder.view.callback.Callback;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.CallbackHandler;
import oogasalad.builder.view.callback.LoadCallback;
import oogasalad.builder.view.callback.SaveCallback;
import oogasalad.builder.view.tab.ActionsTab;
import oogasalad.builder.view.tab.ConditionsTab;
import oogasalad.builder.view.tab.MetaDataTab;
import oogasalad.builder.view.tab.RulesTab;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.PiecesTab;
import java.util.ResourceBundle;

/** Creates the scene and handles the builder GUI and the tabs within it
 * @author Mike Keohane
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static final String SPLASH_PACKAGE = "SplashLogin.css";
  // private static final String TAB_LANGUAGE = "English";
  private static String TAB_LANGUAGE = "English";
  private static String TAB_PROPERTIES = "tabResources";
  private static final String TAB_FORMAT = "tabFormat.css";
  private String[] languageChoice = {"English", "Spanish", "Italian", "PigLatin"};

  private Stage stage;
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
  private Label myLabel;

  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  public BuilderView(Stage mainStage) {
    //splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_PROPERTIES);
    stage = mainStage;
    displayWelcome();
  }

  private void displayWelcome() {
    boardPane = new BorderPane();
    buttonHolder = new HBox();
    myWelcome = new Label(ViewResourcesSingleton.getInstance().getString("Welcome"));
    //TODO : MAKE COME FROM CSS
    myWelcome.setFont(new Font("Inter", 30));
    boardPane.setLeft(myWelcome);
    Button login = makeButton("Proceed", event -> buildView());
    login.setId("loginButton");
    languageBox = new ChoiceBox<>();
    languageBox.getItems().addAll(languageChoice);
    languageBox.setOnAction(this::getLanguage);
    buttonHolder.getChildren().addAll(login, languageBox);


    Scene myLoginScene = new Scene(boardPane, Integer.parseInt(tabProperties.getString("sceneSizeX")), Integer.parseInt(tabProperties.getString("sceneSizeY")));
    myLoginScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE).toExternalForm());
    boardPane.getStyleClass().add("boardPane");
    languageBox.getStyleClass().add("languageBox");
    login.getStyleClass().add("buttonHolder");
    boardPane.setCenter(buttonHolder);
    stage = new Stage();
    stage.setScene(myLoginScene);
    stage.show();
  }

  // Builds the view, including all tabs and menus
  private void buildView() {
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(setupTabs());
    borderPane.setBottom(makeMenu());

    Scene tabScene = new Scene(borderPane, Integer.parseInt(tabProperties.getString("sceneSizeX")),
        Integer.parseInt(tabProperties.getString("sceneSizeY")));

    tabScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + TAB_FORMAT).toExternalForm());
    stage.setScene(tabScene);
  }

  //Sets up all tabs in the tab pane
  private TabPane setupTabs() {
    // TODO: Maybe refactor this into its own class?
    TabPane tabPane = new TabPane();
    // TODO: Replace this with a method call and reflection
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

    return tabPane;
  }

  // Makes the menu bar, which holds the save and load buttons
  private HBox makeMenu() {
    HBox menu = new HBox();
    menu.getChildren().add(makeButton("save", e -> saveConfig()));
    menu.getChildren().add(makeButton("load", e -> loadConfig()));
    return menu;
  }

  //returns a button with the title provided linked to the event passed as a parameter
  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    String label = ViewResourcesSingleton.getInstance().getString(property);
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  // Sets the language of the builder view
  private void getLanguage(ActionEvent event) {
    String myLanguage = languageBox.getValue();
    ViewResourcesSingleton.getInstance().setLanguage(myLanguage);
    displayWelcome();
  }

  // Saves the configuration of the game using a callback to call the controller
  private void saveConfig() {
    Stage stage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    //TODO: Remove Magic Value
    directoryChooser.setTitle("Choose Configuration Save Location");
    callbackDispatcher.call(new SaveCallback(directoryChooser.showDialog(stage)));
  }

  // Saves the configuration of the game using a callback to call the controller
  private void loadConfig() {
    Stage stage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    //TODO: Remove Magic Value
    directoryChooser.setTitle("Choose Configuration Load Location");
    callbackDispatcher.call(new LoadCallback(directoryChooser.showDialog(stage)));

  }

  /**
   * Shows a throwable error
   *
   * @param t a throwable error
   */
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

