package oogasalad.builder.view.tab.boardTab;

import java.util.*;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * 
 */
public class BoardTab {

    private BorderPane boardPane;

    public BoardTab() {
        boardPane = new BorderPane();

        // TODO : Make this not magic
        setupBoard(18, 12, "Checkers");
    }

    public Node toNode(){
        return boardPane;
    }

    private void setupBoard(int xSize, int ySize, String boardType){
        BoardCanvas boardCanvas = new BoardCanvas(xSize, ySize);
        boardPane.setCenter(boardCanvas.toNode());
    }
}