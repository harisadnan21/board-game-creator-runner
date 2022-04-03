package oogasalad.builder.view.tab.pieceTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import oogasalad.builder.view.tab.TitlePane;
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
    private Piece pieceCanvas;
    private GraphicsContext pieceGraphics;
    private ColorPicker myColorPicker;
    public static String RESOURCE_PATH = "/view/";
    public static String LANGUAGE_TEMP = "English";
    private ResourceBundle resources;

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
        resources = ResourceBundle.getBundle(RESOURCE_PATH + LANGUAGE_TEMP);

        // TODO : Make this not magic
        //setupPiece(8, 8);
        setupBlankBoard();
        setupRightPane();
        setupTitle();

        // section for buttons
//        // TODO: Make this a function
    }

    public Node toNode(){
        return boardPane;
    }

    public void changeColor(ActionEvent event) {
        Color myColor = myColorPicker.getValue();
        pieceGraphics.setFill(myColor);
    }

    private void setupPiece(int xSize, int ySize){
//        pieceCanvas = new Canvas(40, 40);
//        pieceGraphics = pieceCanvas.getGraphicsContext2D();
//        pieceGraphics.fillOval(10, 60, 30, 30);
//        myColorPicker = new ColorPicker(Color.BLUE);
//        Circle myCircle = new Circle(40, 40, 40);
//        myColorPicker.getStyleClass().add(myColorPicker.STYLE_CLASS_SPLIT_BUTTON);
//        myColorPicker.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//            @Override
//            public void handle(javafx.event.ActionEvent event) {
//                myCircle.setFill(myColorPicker.getValue());
//            }
//        });
//        buttonHolder.getChildren().addAll(myColorPicker);
//        buttonHolder.setAlignment(Pos.CENTER);
//
//        //boardPane.setCenter(pieceCanvas);
//        //boardPane.setRight(myColorPicker);
//        //boardPane.setCenter(buttonHolder);
//        boardPane.getChildren().addAll(myCircle, buttonHolder);

    }

    private Node setupBoardConfigInput() {
        VBox boardConfigBox = new VBox();

        HBox colorChoiceBox = new HBox();

        ColorPicker colorPickerA = new ColorPicker();
        //ColorPicker colorPickerB = new ColorPicker(Color.BLACK);
        colorChoiceBox.getChildren().addAll(colorPickerA);

        //TODO : get the boardTypes from somewhere good
        List<String> boardTypeList = List.of(new String[]{resources.getString("checkers")});
        ObservableList<String> boardTypes = FXCollections.observableArrayList(boardTypeList);

        ComboBox<String> boardTypeBox = new ComboBox<>(boardTypes);
        // boardTypeBox.getItems().add(resources.getString("checkers"));
        boardTypeBox.setPromptText(resources.getString("pieceTypePicker"));


        boardConfigBox.getChildren()
                .addAll(colorChoiceBox, boardTypeBox);
        boardConfigBox.getChildren()
                .addAll(setupButtonBar());
        boardConfigBox.setId("boardConfigBox");
        return boardConfigBox;
    }

    private void setupRightPane() {
        VBox rightBox = new VBox();

        rightBox.getChildren().addAll(setupButtonBar(), setupBoardConfigInput());
        rightBox.setId("rightPane");
        boardPane.setRight(rightBox);
    }

    private void setupTitle() {
        boardPane.setTop(new TitlePane("boardTitle", resources).toNode());
    }

    public Pane getCanvasPane() {
        Pane ret = new Pane();
        ret.getChildren().addAll(pieceCanvas);
        return ret;
    }

    private void setupBlankBoard() {
        pieceCanvas = new Piece(resources, boardPane);

        Pane canvasPane = pieceCanvas.getPiecePane();
        canvasPane.prefWidthProperty().bind(boardPane.widthProperty().multiply(0.7));
        canvasPane.prefHeightProperty().bind(boardPane.heightProperty());

        canvasPane.setId("pieceCanvas");
        boardPane.setCenter(canvasPane);

    }

    private Node setupButtonBar() {
        VBox buttonBox = new VBox();
        Button saveButton = makeButton("savePiece", e -> clearPieces(), resources);
        Button resetPiecesButton = makeButton("clearPieces", e -> clearPieces(), resources);

        buttonBox.getChildren().addAll(saveButton, resetPiecesButton);
        buttonBox.setId("buttonBox");
        return buttonBox;
    }

    public static Button makeButton(String property, EventHandler<javafx.event.ActionEvent> handler,
                                    ResourceBundle resources) {
        Button result = new Button();
        String label = resources.getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    public void clearPieces(){
        pieceGraphics.clearRect(0, 0, Integer.parseInt(resources.getString("boardSizeX")), Integer.parseInt(resources.getString("boardSizeY")));
    }


}