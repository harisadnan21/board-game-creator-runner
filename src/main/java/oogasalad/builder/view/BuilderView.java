package oogasalad.builder.view;


import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oogasalad.builder.view.callback.Callback;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.CallbackHandler;
import oogasalad.builder.view.callback.LoadCallback;
import oogasalad.builder.view.configure.FormatDropDown;
import oogasalad.builder.view.configure.SettingsWindow;
import oogasalad.builder.view.tab.AllTabs;


import java.util.ResourceBundle;
import oogasalad.view.SplashWelcome;

/**
 * Creates the scene and handles the builder GUI and the tabs within it
 *
 * @author Mike Keohane
 */
public class BuilderView {

  public static final String DEFAULT_STYLE_PACKAGE = "/builder/view/css/";
  public static final String DEFAULT_PROPERTY_PACKAGE = "/builder/view/information-properties/";
  private static final String TAB_PROPERTIES = "tabResources";
  public static final String DEFAULT_TAB_FORMAT = "tabFormat.css";
  public static final String DEFAULT_FONT_FORMAT = "Default.css";

  private static final String LOAD_DIR_CHOOSER_TITLE_KEY = "LoadChooserTitle";

  private final Stage stage;
  public static final ResourceBundle tabProperties = ResourceBundle.getBundle(
      DEFAULT_PROPERTY_PACKAGE + TAB_PROPERTIES);
  private Scene tabScene;
  private AllTabs allTabs;
  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  /**
   * Constructor to initialise the builderView
   *
   * @param mainStage - stage to display the view
   */
  public BuilderView(Stage mainStage) {
    stage = mainStage;
    buildView();

  }

  // Builds the view, including all tabs and menus
  private void buildView() {
    BorderPane borderPane = new BorderPane();
    allTabs = new AllTabs(callbackDispatcher);
    borderPane.setCenter(allTabs);
    borderPane.setBottom(makeMenu());
    tabScene = new Scene(borderPane, Integer.parseInt(tabProperties.getString("sceneSizeX")),
        Integer.parseInt(tabProperties.getString("sceneSizeY")));
    setFormat(DEFAULT_TAB_FORMAT, DEFAULT_FONT_FORMAT);
    stage.setScene(tabScene);
    stage.centerOnScreen();
    stage.show();
  }


  // Makes the menu bar, which holds the save and load buttons
  private HBox makeMenu() {
    HBox menu = new HBox();
    Button saveButton = makeButton("save", e -> saveConfig());
    saveButton.setId("saveGameButton");
    Button loadButton = makeButton("load", e -> loadConfig());
    Button configureButton = makeButton("configure", e -> settingsConfig());
    menu.getChildren().add(configureButton);
    loadButton.setId("loadGameButton");
    menu.getChildren().add(saveButton);
    menu.getChildren().add(loadButton);
    menu.getChildren().add(makeButton("newWindow", e-> new SplashWelcome()));
    menu.getStyleClass().add("saveMenu");
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

  // Saves the configuration of the game using a callback to call the controller
  private void saveConfig() {
    new MetaDataAndSavePopup(callbackDispatcher);
  }

  private void settingsConfig() { new SettingsWindow(this, stage);}

  // Saves the configuration of the game using a callback to call the controller
  private void loadConfig() {
    Stage loadStage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle(
        ViewResourcesSingleton.getInstance().getString(LOAD_DIR_CHOOSER_TITLE_KEY));
    File file = directoryChooser.showDialog(loadStage);
    if (file != null){
      callbackDispatcher.call(new LoadCallback(file));
      allTabs.loadAllTabs();
    }

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
   *
   * @param callback the callback to handle
   * @param handler  the handler that can handle that type of callback
   * @param <R>      the type that the handler must return
   * @param <C>      the type of the callback
   */
  public <R, C extends Callback<R>> void registerCallbackHandler(Class<C> callback,
      CallbackHandler<R, C> handler) {
    callbackDispatcher.registerCallbackHandler(callback, handler);
  }

  /**
   * Method to set the format of the view
   *
   * @param fontFile -  css file name to set the format
   */
  public void setFormat(String themeFile, String fontFile) {
    tabScene.getStylesheets().clear();
    tabScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_STYLE_PACKAGE + themeFile).toExternalForm());
    tabScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLE_PACKAGE + fontFile).toExternalForm());
  }


  //Gets the tabs FOR TESTING PURPOSES
  AllTabs getAllTabs() {
    return allTabs;
  }
}

