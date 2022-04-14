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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.view.tab.TitlePane;


/**
 *
 */
public class BoardTab extends BorderPane {

  private BoardCanvas boardCanvas;
  private ResourceBundle resources;
  private Spinner<Integer> xDimensionPicker;
  private Spinner<Integer> yDimensionPicker;
  private ColorPicker colorPickerA;
  private ColorPicker colorPickerB;
  private ComboBox<String> boardTypeBox;

  private BuilderController controller; // FIXME: Use event handlers instead

  public BoardTab(ResourceBundle resourcesBundle, BuilderController controller) {
    resources = resourcesBundle;
    this.controller = controller;


    setupBlankBoard();
    setupRightPane();
    setupTitle();
  }


  private void setupTitle() {
    setTop(new TitlePane("boardTitle").toNode());
  }

  private void setupBlankBoard() {
    boardCanvas = new BoardCanvas(resources, this, controller);

    Pane canvasPane = boardCanvas.getCanvasPane();
    canvasPane.prefWidthProperty().bind(this.widthProperty().multiply(0.7));
    canvasPane.prefHeightProperty().bind(this.heightProperty());

    canvasPane.setId("boardCanvas");
    setCenter(canvasPane);
  }

  private void setupRightPane() {
    VBox rightBox = new VBox();

    rightBox.getChildren().addAll(setupButtonBar(), setupBoardConfigInput());
    rightBox.setId("rightPane");
    setRight(rightBox);
  }

  private Node setupBoardConfigInput() {
    VBox boardConfigBox = new VBox();

    Button confirmBoardButton = makeButton("drawBoard", e ->
        createBoard());

    boardConfigBox.getChildren()
        .addAll(setupColorChoiceBox(), setupDimensionChoiceBox(), setupBoardTypeBox(),
            confirmBoardButton);
    boardConfigBox.setId("boardConfigBox");
    return boardConfigBox;
  }

  private Node setupColorChoiceBox() {
    HBox colorChoiceBox = new HBox();

    colorPickerA = new ColorPicker();
    colorPickerB = new ColorPicker(Color.BLACK);
    colorChoiceBox.getChildren().addAll(colorPickerA, colorPickerB);
    return colorChoiceBox;
  }

  private Node setupDimensionChoiceBox() {
    HBox numberPickerBox = new HBox();
    Label xDimLabel = new Label(resources.getString("xDimLabel"));
    Label yDimLabel = new Label(resources.getString("yDimLabel"));

    //TODO CHANGE FROM MAGIC NUMBER
    xDimensionPicker = new Spinner<>(0, 50, 8, 1);
    yDimensionPicker = new Spinner<>(0, 50, 8, 1);

    VBox xDimBox = new VBox(xDimLabel, xDimensionPicker);
    VBox yDimBox = new VBox(yDimLabel, yDimensionPicker);

    numberPickerBox.getChildren().addAll(xDimBox, yDimBox);
    return numberPickerBox;
  }

  private Node setupBoardTypeBox() {
    //TODO : get the boardTypes from somewhere good
    List<String> boardTypeList = List.of(new String[]{resources.getString("checkers")});
    ObservableList<String> boardTypes = FXCollections.observableArrayList(boardTypeList);

    ComboBox<String> boardTypeBox = new ComboBox<>(boardTypes);
    boardTypeBox.setPromptText(resources.getString("boardTypePicker"));
    boardTypeBox.setValue(boardTypeList.get(0));
    return boardTypeBox;
  }

  private void createBoard()
      throws NullBoardException {
    if (boardTypeBox.getValue() == null) {
      System.out.println("No Board Type Chosen Error");
      return;
    }

    boardCanvas.setColor(colorPickerA.getValue(), 1);
    boardCanvas.setColor(colorPickerB.getValue(), 2);
    boardCanvas.drawBoard(xDimensionPicker.getValue(), yDimensionPicker.getValue(),
        boardTypeBox.getValue());
  }

  private Node setupButtonBar() {
    VBox buttonBox = new VBox();
    Button saveButton = makeButton("saveBoard", e -> saveBoardConfig());

    Button resetPiecesButton = makeButton("clearPieces", e -> boardCanvas.clearBoard());

    buttonBox.getChildren()
        .addAll(saveButton, setupPieceChoiceBox(), createEraserButton(), resetPiecesButton);
    buttonBox.setId("buttonBox");
    return buttonBox;
  }

  private ToggleButton createEraserButton() {
    ToggleButton eraseButton = new ToggleButton(resources.getString("eraser"));
    eraseButton.setOnAction(e -> toggleErase(eraseButton));

    return eraseButton;
  }

  private void toggleErase(ToggleButton eraser) {
    if (eraser.isSelected()) {
      boardCanvas.setClickToErase();
    } else {
      boardCanvas.setClickToPlace();
    }
  }


  private ComboBox setupPieceChoiceBox() {
    ComboBox<String> choosePieceBox = new ComboBox<>();

    choosePieceBox.setOnMouseEntered(e -> updatePieceOptions(choosePieceBox));

    choosePieceBox.setPromptText(resources.getString("placePiece"));
    choosePieceBox.valueProperty().addListener(
        (observableValue, s, t1) -> boardCanvas.setCurrentPiece(t1));

    return choosePieceBox;
  }

  private void updatePieceOptions(ComboBox<String> pieceBox) {
    //TODO: Remove Magic Value
    String currVal = pieceBox.getValue();
    Collection<String> pieceNames = controller.getElementNames("piece");
    pieceBox.getItems().setAll(pieceNames);
    pieceBox.setValue(currVal);
  }

  private void saveBoardConfig() {
    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();
    controller.save(fileChooser.showSaveDialog(stage));
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