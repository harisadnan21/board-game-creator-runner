package oogasalad.builder.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.Scene;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.Callback;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.CallbackHandler;
import oogasalad.builder.view.callback.LoadCallback;
import oogasalad.builder.view.callback.SaveCallback;
import oogasalad.builder.view.property.PropertySelector;
import oogasalad.builder.view.tab.ActionsTab;
import oogasalad.builder.view.tab.BasicTab;
import oogasalad.builder.view.tab.ConditionsTab;
import oogasalad.builder.view.tab.GameElementTab;
import oogasalad.builder.view.tab.HelpTab;
import oogasalad.builder.view.tab.MetaDataTab;
import oogasalad.builder.view.tab.PiecesTab;
import oogasalad.builder.view.tab.RulesTab;
import oogasalad.builder.view.tab.SplashLogin;
import oogasalad.builder.view.tab.WinConditionsTab;
import oogasalad.builder.view.tab.boardTab.BoardTab;


import java.util.ResourceBundle;

/** Creates the scene and handles the builder GUI and the tabs within it
 * @author Mike Keohane
 */
public class BuilderView {


  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  public static final String TABS_LIST = "TabsList";
  public static final String TABS_PATH = "oogasalad.builder.view.tab.";
  private static String TAB_PROPERTIES = "tabResources";
  private static final String TAB_FORMAT = "tabFormat.css";


  private static Stage stage;
  private Collection<BasicTab> tabs;
  public static final  ResourceBundle tabProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_PROPERTIES);
  public static final  ResourceBundle tabsList = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TABS_LIST);

  private final CallbackDispatcher callbackDispatcher = new CallbackDispatcher();

  public BuilderView(Stage mainStage) {
    tabs = new HashSet<>();
    stage = mainStage;

    SplashLogin newWindow = new SplashLogin(e -> buildView());
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
    stage.show();
  }

  //Sets up all tabs in the tab pane
  private TabPane setupTabs() {
    TabPane tabPane = new TabPane();
    for (String tabKey : tabsList.keySet()){
      tabPane.getTabs().add(createTab(tabKey));
    }
    return tabPane;
  }

  //Create a tab using reflection
  private Tab createTab(String tabNameKey) {
    try {
      Class<?> clss = Class.forName(TABS_PATH + tabsList.getString(tabNameKey));
      Constructor<?> ctor = clss.getDeclaredConstructor(CallbackDispatcher.class);
      BasicTab createdTab = (BasicTab) ctor.newInstance(callbackDispatcher);
      createdTab.setId(tabNameKey + "Tab");
      tabs.add(createdTab);
      return new Tab(ViewResourcesSingleton.getInstance().getString(tabNameKey), createdTab);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InvalidFormException(e.getMessage()); // TODO: Handle this properly
    }
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
    for (BasicTab tab : tabs) {
      tab.loadElements();
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
   * @param callback the callback to handle
   * @param handler the handler that can handle that type of callback
   * @param <R> the type that the handler must return
   * @param <C> the type of the callback
   */
  public <R, C extends Callback<R>> void registerCallbackHandler(Class<C> callback, CallbackHandler<R, C> handler) {
    callbackDispatcher.registerCallbackHandler(callback, handler);
  }
}

