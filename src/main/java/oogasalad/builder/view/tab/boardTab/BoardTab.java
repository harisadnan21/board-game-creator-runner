package oogasalad.builder.view.tab.boardTab;

import java.util.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * 
 */
public class BoardTab {

    private BorderPane boardPane;
    private HBox buttonHolder;
    private BoardCanvas boardCanvas;
    public BoardTab() {
        boardPane = new BorderPane();
        buttonHolder = new HBox();

        // TODO : Make this not magic
        setupBoard(8, 8, "Checkers");

        // section for buttons
        // TODO: Make this a function
        Button saveButton = new Button("Print Config");
        saveButton.setOnAction(e -> saveBoardConfig());
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        saveButton.setMaxWidth(200); // ADD TO PROPERTIES FILE

        Button addPiece = new Button("Add Game Piece");
        saveButton.setOnAction(e -> addBoardPiece());
        HBox.setHgrow(addPiece, Priority.ALWAYS);
        addPiece.setMaxWidth(200); // ADD TO PROPERTIES FILE


        buttonHolder.getChildren().addAll(saveButton, addPiece);
        boardPane.setRight(buttonHolder);


    }

    public Node toNode(){
        return boardPane;
    }

    private void setupBoard(int xSize, int ySize, String boardType){
        boardCanvas = new BoardCanvas(xSize, ySize);
        boardPane.setCenter(boardCanvas.getCanvasNode());
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
}