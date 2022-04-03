package oogasalad.builder.view;

import java.util.Locale.Builder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.element.ElementRecord;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oogasalad.builder.view.tab.SplashLogin;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.pieceTab.PiecesTab;
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
  private SplashLogin loginPage;
  private Label myWelcome;
  private ResourceBundle myResources;
  private BorderPane boardPane;
  private HBox buttonHolder;
  private static BorderPane loginRoot;
  private ResourceBundle splashResources;
  private ResourceBundle tabResources;

  private BuilderController controller; //FIXME: Use Event handlers instead of this

  public BuilderView(Stage mainStage) {
    splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_LANGUAGE);
    controller = new BuilderController();
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

    boardTabPane = new BoardTab(tabResources, controller);
    boardTabPane.toNode().setId("boardTab");
    Tab boardTab = new Tab("Board", boardTabPane.toNode());

    pieceTabPane = new PiecesTab(controller);
    pieceTabPane.toNode().setId("pieceTab");
    Tab pieceTab = new Tab("Piece", pieceTabPane.toNode());

    tabPane.getTabs().addAll(boardTab, pieceTab);

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
}

