package oogasalad.builder.view.tab.pieceTab;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import oogasalad.builder.view.tab.boardTab.BoardCanvas;
import oogasalad.builder.view.tab.pieceTab.Piece;

import java.awt.event.ActionEvent;
import java.util.*;

/**
 * 
 */
public class PiecesTab {

    public static double DEFAULT_CANVAS_HEIGHT = 600;
    public static double DEFAULT_CANVAS_WIDTH = 600;
    private BorderPane boardPane;
    private HBox buttonHolder;
    private String pieceType;
    private Canvas pieceCanvas;
    private GraphicsContext pieceGraphics;
    private ColorPicker myColorPicker;

    /**
     * Default constructor
     */
    public PiecesTab() {
//        boardPane = new BorderPane();
//
//        // TODO : Make this not magic
//        setupPiece(8, 8, "Checkers");

        boardPane = new BorderPane();
        buttonHolder = new HBox();

        // TODO : Make this not magic
        setupPiece(8, 8);

        // section for buttons
//        // TODO: Make this a function
//        Button saveButton = new Button("Print Config");
//        saveButton.setOnAction(e -> savePieceConfig());
//        HBox.setHgrow(saveButton, Priority.ALWAYS);
//        saveButton.setMaxWidth(200); // ADD TO PROPERTIES FILE

//        Button addPiece = new Button("Add Game Piece");
//        saveButton.setOnAction(e -> addBoardPiece());
//        HBox.setHgrow(addPiece, Priority.ALWAYS);
//        addPiece.setMaxWidth(200); // ADD TO PROPERTIES FILE


        //buttonHolder.getChildren().addAll(saveButton, addPiece);
        //boardPane.setRight(buttonHolder);
    }

    public Node toNode(){
        return boardPane;
    }

    public void changeColor(ActionEvent event) {
        Color myColor = myColorPicker.getValue();
        pieceGraphics.setFill(myColor);
    }

    private void setupPiece(int xSize, int ySize){
        pieceCanvas = new Canvas(40, 40);
        pieceGraphics = pieceCanvas.getGraphicsContext2D();
        pieceGraphics.fillOval(10, 60, 30, 30);
        myColorPicker = new ColorPicker(Color.BLUE);
        Circle myCircle = new Circle(40, 40, 40);
        myColorPicker.getStyleClass().add(myColorPicker.STYLE_CLASS_SPLIT_BUTTON);
        myColorPicker.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                myCircle.setFill(myColorPicker.getValue());
            }
        });
        buttonHolder.getChildren().addAll(myColorPicker);
        buttonHolder.setAlignment(Pos.CENTER);

        //boardPane.setCenter(pieceCanvas);
        //boardPane.setRight(myColorPicker);
        //boardPane.setCenter(buttonHolder);
        boardPane.getChildren().addAll(myCircle, buttonHolder);

    }

    private Node getCanvasNode() {
        Pane ret = new Pane();
        ret.getChildren().addAll(pieceCanvas);
        return ret;
    }


}