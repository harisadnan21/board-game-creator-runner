package oogasalad.builder.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import oogasalad.builder.model.element.ElementRecord;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import oogasalad.builder.view.tab.SplashLogin;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.pieceTab.PiecesTab;
import java.util.ResourceBundle;

/**
 *
 */
public class BuilderView {

  public static double SCENE_WIDTH = 600;
  public static double SCENE_HEIGHT = 650;
  public static final String DEFAULT_RESOURCE_PACKAGE = "/view/";
  private static final String TAB = "SplashLogin";

  private Stage stage;
  private BoardTab boardTabPane;
  private PiecesTab pieceTabPane;
  private SplashLogin loginPage;
  private static BorderPane loginRoot;
  private ResourceBundle myResources;

  public BuilderView(Stage mainStage) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TAB);
    stage = mainStage;
    setupTabs();
    //displayWelcome();
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
    loginPage = new SplashLogin(myResources);
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
//    /**
//     * @param element
//     * @return
//     */
//    public void putGameElement(ElementRecord element) {
//        // TODO implement here
//        return null;
//    }
  }

  // Can possibly extend tab for each of the tab classes instead of just having toNode() to add them to the tabs
  private void setupTabs() {
    TabPane tabPane = new TabPane();

    boardTabPane = new BoardTab();
    boardTabPane.toNode().setId("boardTab");
    Tab boardTab = new Tab("Board", boardTabPane.toNode());

    pieceTabPane = new PiecesTab();
    pieceTabPane.toNode().setId("boardTab");
    Tab pieceTab = new Tab("Piece", pieceTabPane.toNode());

    tabPane.getTabs().addAll(boardTab, pieceTab);

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    //TODO get this size from file and add more to the scene
    Scene myTabScene = new Scene(tabPane, 600, 650);
    stage.setScene(myTabScene);
  }

  public int[][] getBoardConfig(){
    return boardTabPane.getBoardConfig();
  }
}
