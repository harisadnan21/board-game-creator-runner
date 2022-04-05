package oogasalad.builder.view.tab.boardTab;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oogasalad.builder.controller.ResourcesSingleton;

/**
 * 
 */
public class BoardTab {

    public static String RESOURCE_PATH = "/view/";
    public static String LANGUAGE_TEMP = "English";
    private BorderPane boardPane;
    private BoardCanvas boardCanvas;
    private static ResourceBundle resources;

    public BoardTab() {
        resources = ResourceBundle.getBundle(
            RESOURCE_PATH + LANGUAGE_TEMP);
        boardPane = new BorderPane();


        // TODO : Make this not magic
        setupBoard(8, 8, "Checkers");

        setupRightPane();
    }

    public Node toNode(){
        return boardPane;
    }

    private void setupBoard(int xSize, int ySize, String boardType){
        boardCanvas = new BoardCanvas(xSize, ySize);

        Pane canvasPane = boardCanvas.getCanvasPane();
        canvasPane.prefWidthProperty().bind(boardPane.widthProperty().multiply(0.7));
        canvasPane.prefHeightProperty().bind(boardPane.heightProperty());
        boardPane.setCenter(canvasPane);
    }

    private void setupRightPane(){

        VBox rightBox = new VBox();

        Button saveButton = makeButton("saveBoard", e -> saveBoardConfig());
        Button addPiece = makeButton("placePiece", e-> addBoardPiece());

        addPiece.setMaxWidth(200);

        rightBox.getChildren().addAll(saveButton, addPiece);

        boardPane.setRight(rightBox);
    }

    private void saveBoardConfig(){
        System.out.println(boardCanvas.printBoardConfig());
    }

    private void addBoardPiece(){
        System.out.println(boardCanvas.printBoardConfig());
    }

    public int[][] getBoardConfig(){
       return boardCanvas.getBoardConfig();
    }

    //create buttons with their own names and actions
    public static Button makeButton(String labelName, EventHandler<ActionEvent> handler) {

        Button buttonCreated = new Button();
        String buttonLabel = resources.getString(labelName);

        buttonCreated.setText(buttonLabel);
        buttonCreated.setOnAction(handler);
        buttonCreated.setId(labelName);

        return buttonCreated;

    }
}