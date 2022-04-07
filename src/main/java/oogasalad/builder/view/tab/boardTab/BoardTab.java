package oogasalad.builder.view.tab.boardTab;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import oogasalad.builder.view.tab.TitlePane;


/**
 *
 */
public class BoardTab {

  public static String RESOURCE_PATH = "/view/";
  public static String LANGUAGE_TEMP = "English";
  private BorderPane boardPane;
  private BoardCanvas boardCanvas;
  private ResourceBundle resources;

  public BoardTab(ResourceBundle resourcesBundle) {
    boardPane = new BorderPane();
    resources = resourcesBundle;

//        // TODO : Make this not magic
//        setupBoard(8, 8, "Checkers");
    setupBlankBoard();
    setupRightPane();
    setupTitle();
  }

  public Node toNode() {
    return boardPane;
  }

  private void setupTitle() {
    boardPane.setTop(new TitlePane("boardTitle", resources).toNode());
  }

  private void setupBlankBoard() {
    boardCanvas = new BoardCanvas(resources, boardPane);

    Pane canvasPane = boardCanvas.getCanvasPane();
    canvasPane.prefWidthProperty().bind(boardPane.widthProperty().multiply(0.7));
    canvasPane.prefHeightProperty().bind(boardPane.heightProperty());

    canvasPane.setId("boardCanvas");
    boardPane.setCenter(canvasPane);
  }

  private void setupRightPane() {
    VBox rightBox = new VBox();

    rightBox.getChildren().addAll(setupButtonBar(), setupBoardConfigInput());
    rightBox.setId("rightPane");
    boardPane.setRight(rightBox);
  }

  private Node setupBoardConfigInput() {
    VBox boardConfigBox = new VBox();

    HBox colorChoiceBox = new HBox();

    ColorPicker colorPickerA = new ColorPicker();
    ColorPicker colorPickerB = new ColorPicker(Color.BLACK);
    colorChoiceBox.getChildren().addAll(colorPickerA, colorPickerB);

    HBox numberPickerBox = new HBox();
    Label xDimLabel = new Label(resources.getString("xDimLabel"));
    Label yDimLabel = new Label(resources.getString("yDimLabel"));
    Spinner<Integer> xSpinner = new Spinner<>(0, 50, 8, 1);
    Spinner<Integer> ySpinner = new Spinner<>(0, 50, 8, 1);

    VBox xDimBox = new VBox(xDimLabel, xSpinner);
    VBox yDimBox = new VBox(yDimLabel, ySpinner);


    numberPickerBox.getChildren().addAll(xDimBox, yDimBox);

    //TODO : get the boardTypes from somewhere good
    List<String> boardTypeList = List.of(new String[]{resources.getString("checkers")});
    ObservableList<String> boardTypes = FXCollections.observableArrayList(boardTypeList);

    ComboBox<String> boardTypeBox = new ComboBox<>(boardTypes);
    // boardTypeBox.getItems().add(resources.getString("checkers"));
    boardTypeBox.setPromptText(resources.getString("boardTypePicker"));

    Button confirmBoardButton = makeButton("drawBoard", e -> createBoard(xSpinner.getValue(),
        ySpinner.getValue(), colorPickerA.getValue(), colorPickerB.getValue(),
        boardTypeBox.getValue()));

    boardConfigBox.getChildren()
        .addAll(colorChoiceBox, numberPickerBox, boardTypeBox, confirmBoardButton);
    boardConfigBox.setId("boardConfigBox");
    return boardConfigBox;
  }

  private void createBoard(int xDim, int yDim, Paint colorA, Paint colorB, String boardType) {
    if (boardType == null){
      System.out.println("No Board Type Chosen Error");
      return;
    }

    boardCanvas.setColor(colorA, 1);
    boardCanvas.setColor(colorB, 2);
    boardCanvas.drawBoard(xDim, yDim, boardType);
  }

  private Node setupButtonBar() {
    VBox buttonBox = new VBox();
    Button saveButton = makeButton("saveBoard", e -> saveBoardConfig());
    Button addPieceButton = makeButton("placePiece", e -> addBoardPiece());

    Button resetPiecesButton = makeButton("clearPieces", e -> boardCanvas.clearBoard());

    buttonBox.getChildren().addAll(saveButton, addPieceButton, resetPiecesButton);
    buttonBox.setId("buttonBox");
    return buttonBox;
  }


  private void saveBoardConfig() {
    System.out.println(boardCanvas.printBoardConfig());
  }

  private void addBoardPiece() {
    System.out.println(boardCanvas.printBoardConfig());
  }

  public int[][] getBoardConfig() {
    return boardCanvas.getBoardConfig();
  }

  //create buttons with their own names and actions
  public Button makeButton(String labelName, EventHandler<ActionEvent> handler) {

    Button buttonCreated = new Button();
    String buttonLabel = resources.getString(labelName);

    buttonCreated.setText(buttonLabel);
    buttonCreated.setOnAction(handler);
    buttonCreated.setId(labelName);

    return buttonCreated;

  }
}