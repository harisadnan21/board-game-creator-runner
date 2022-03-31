package oogasalad.builder.view.tab.boardTab;

import java.util.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * 
 */
public class BoardTab {

    private BorderPane boardPane;
    private BoardCanvas boardCanvas;
    public BoardTab() {
        boardPane = new BorderPane();

        // TODO : Make this not magic
        setupBoard(8, 8, "Checkers");

        //TODO : Make a section for buttons and do this right
        Button saveButton = new Button("Print Config");
        saveButton.setOnAction(e -> saveBoardConfig());
        boardPane.setRight(saveButton);

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
}