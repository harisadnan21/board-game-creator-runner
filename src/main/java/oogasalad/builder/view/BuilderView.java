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

    //Temp method just to show how to create and manage tabs
    private void exampleTabStuff(Stage stage){
        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("tab");
        BorderPane tabObj = new BorderPane();
        tabObj.setCenter(new Rectangle(600,200, Color.BLUE));
        tab.setContent(tabObj);
        tabPane.getTabs().add(tab);

        Tab tab2 = new Tab();
        tab2.setText("tab2");
        tab2.setContent(new Rectangle(200,200, Color.GREEN));
        tabPane.getTabs().add(tab2);

        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Scene myScene = new Scene(tabPane, 200, 200);
        stage.setScene(myScene);
        stage.show();
    }

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
        Scene myTabScene = new Scene(tabPane, 600, 600);
        stage.setScene(myTabScene);
    }

}