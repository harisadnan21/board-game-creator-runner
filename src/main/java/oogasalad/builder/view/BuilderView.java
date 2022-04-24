package oogasalad.builder.view;


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
import oogasalad.builder.view.tab.AllTabs;


import java.util.ResourceBundle;

/**
 * Creates the scene and handles the builder GUI and the tabs within it
 *
 * @author Mike Keohane
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static String TAB_PROPERTIES = "tabResources";
  public static final String TAB_FORMAT = "tabFormat.css";

  private final Stage stage;
  public static final ResourceBundle tabProperties = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_PACKAGE + TAB_PROPERTIES);
  private AllTabs allTabs;
  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  public BuilderView(Stage mainStage) {
    stage = mainStage;
    SplashLogin newWindow = new SplashLogin(e -> buildView());
  }

  // Builds the view, including all tabs and menus
  private void buildView() {
    BorderPane borderPane = new BorderPane();
    allTabs = new AllTabs(callbackDispatcher);
    borderPane.setCenter(allTabs);
    borderPane.setBottom(makeMenu());

    Scene tabScene = new Scene(borderPane, Integer.parseInt(tabProperties.getString("sceneSizeX")),
        Integer.parseInt(tabProperties.getString("sceneSizeY")));

    tabScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + TAB_FORMAT).toExternalForm());
    stage.setScene(tabScene);
    stage.show();
  }

  // Makes the menu bar, which holds the save and load buttons
  private HBox makeMenu() {
    HBox menu = new HBox();
    menu.getChildren().add(makeButton("save", e -> saveConfig()));
    menu.getChildren().add(makeButton("load", e -> loadConfig()));
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

  // Saves the configuration of the game using a callback to call the controller
  private void loadConfig() {
    Stage loadStage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    //TODO: Remove Magic Value
    directoryChooser.setTitle("Choose Configuration Load Location");
    callbackDispatcher.call(new LoadCallback(directoryChooser.showDialog(loadStage)));
    allTabs.loadAllTabs();
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
}

