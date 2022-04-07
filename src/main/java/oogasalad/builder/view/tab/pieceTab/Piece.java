package oogasalad.builder.view.tab.pieceTab;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;

import java.util.*;

/**
 *
 */
public class Piece extends Node {

    private static final String firstColor = "RED";
    private static final String secondColor = "BLACK";
    private BorderPane pieceTab;
    private HBox buttonHolder;
    private String pieceType;
    private Canvas pieceCanvas;
    private GraphicsContext pieceGraphics;
    private ColorPicker myColorPicker;
    public static String RESOURCE_PATH = "/view/";
    public static String LANGUAGE_TEMP = "English";
    private ResourceBundle resources;


    /**
     * Default constructor
     */
    public Piece(ResourceBundle rb, BorderPane borderPane) {

        Circle newPiece = new Circle();
        pieceTab = borderPane;
        resources = rb;
        setupPieceBoard();
    }

    public void setupPieceBoard(){
        pieceCanvas = new Canvas(Integer.parseInt(resources.getString("boardSizeX")), Integer.parseInt(resources.getString("boardSizeY")));
        pieceGraphics = pieceCanvas.getGraphicsContext2D();
        pieceCanvas.setId("pieceBuilder");

    }

    public Pane getPiecePane() {
        Pane ret = new Pane();
        ret.getChildren().addAll(pieceCanvas);
        return ret;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    /**
     * Default constructor
     */



}