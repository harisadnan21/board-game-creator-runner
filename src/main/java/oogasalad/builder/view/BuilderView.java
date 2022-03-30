package oogasalad.builder.view;

import java.util.*;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import oogasalad.builder.view.tab.boardTab.BoardTab;

/**
 * 
 */
public class BuilderView {
    public static double SCENE_WIDTH = 600;
    public static double SCENE_HEIGHT = 650;

    private Stage stage;
    public BuilderView(Stage mainStage) {
        stage = mainStage;
        setupTabs();
        stage.show();
    }



//    /**
//     * @param element
//     * @return
//     */
//    public void putGameElement(ElementRecord element) {
//        // TODO implement here
//        return null;
//    }

    private void setupTabs(){
        TabPane tabPane = new TabPane();

        BoardTab boardTabPane = new BoardTab();
        Tab boardTab = new Tab("Board", boardTabPane.toNode());

        Tab tab2 = new Tab();
        tab2.setText("tab2");
        tab2.setContent(new Rectangle(600,600, Color.GREEN));


        tabPane.getTabs().addAll(boardTab, tab2);

        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        //TODO get this size from file and add more to the scene
        Scene myTabScene = new Scene(tabPane, 600, 650);
        stage.setScene(myTabScene);
    }

}