package oogasalad.builder.view;

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
  private static BorderPane loginRoot;
  private ResourceBundle splashResources;
  private ResourceBundle tabResources;

  public BuilderView(Stage mainStage) {
    splashResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SPLASH_PACKAGE);
    tabResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB_LANGUAGE);
    stage = mainStage;
    setupTabs();
    stage.show();
  }


  public Scene makeScene(int width, int height, Stage stage) {
    Scene scene = new Scene(loginRoot, width, height);
    displayWelcome(scene, stage);
    return scene;
  }

  private void displayWelcome(Scene scene, Stage myStage) {
    scene.getStylesheets()
        .add(getClass().getResource("/SplashLogin.css").toExternalForm());
    //myRoot.getChildren().clear();
    loginPage = new SplashLogin(splashResources);
    // myRoot.setCenter(myWelcome.getPane());
    //Button compiler = SlogoView.makeButton("Compiler", event -> displayConsole(), myResources);
    //myWelcome.getContainer().getChildren().addAll(proceed, compiler);
    //myWelcome.getContainer().setAlignment(Pos.CENTER);
    myStage.setScene(scene);
    myStage.show();
  }


  /**
   * @param element
   * @return
   */
  public void putGameElement(ElementRecord element) {
    // TODO implement here
    //return null;
  }

  // Creates all the tabs
  private void setupTabs() {
    TabPane tabPane = new TabPane();

    boardTabPane = new BoardTab(tabResources);
    boardTabPane.toNode().setId("boardTab");
    Tab boardTab = new Tab("Board", boardTabPane.toNode());

    pieceTabPane = new PiecesTab();
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

  public int[][] getBoardConfig() {
    return boardTabPane.getBoardConfig();
  }
}
