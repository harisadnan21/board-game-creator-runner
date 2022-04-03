package oogasalad.builder.view.tab.pieceTab;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import oogasalad.builder.view.tab.pieceTab.Piece;

import java.util.*;

/**
 * 
 */
public class PiecesTab {

    private BorderPane boardPane;
    private String pieceType;

    /**
     * Default constructor
     */
    public PiecesTab() {
        boardPane = new BorderPane();

        // TODO : Make this not magic
        setupPiece(8, 8, "Checkers");
    }

    public Node toNode(){
        return boardPane;
    }

    private void setupPiece(int xSize, int ySize, String boardType){

        Piece gamePiece = new Piece(pieceType, xSize, ySize);
        //boardPane.setLeft(gamePiece.toNode())
    }

}