package oogasalad.builder.view.tab.boardTab;

import static oogasalad.builder.view.BuilderView.DEFAULT_RESOURCE_PACKAGE;

import java.util.*;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.SaveCallback;
import oogasalad.builder.view.tab.BasicTab;


/**
 * @author Mike Keohane
 */
public class BoardTab extends BasicTab {

  public static final String BOARD_TYPE = "board";
  public static String BOARD_PROPERTIES = "BoardTypes";
  private BoardCanvas boardCanvas;
  private Spinner<Integer> xDimensionPicker;
  private Spinner<Integer> yDimensionPicker;
  private ColorPicker colorPickerA;
  private ColorPicker colorPickerB;
  private ComboBox<String> boardTypeBox;
  private static final ResourceBundle boardTypes = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + BOARD_PROPERTIES);

  public BoardTab(CallbackDispatcher dispatcher) {
    super(BOARD_TYPE, dispatcher);
  }

  @Override
  protected Node setupRightSide() {
    VBox rightBox = new VBox();

    rightBox.getChildren().addAll(setupButtonBar(), setupBoardConfigInput());
    rightBox.setId("rightBoardPane");
    rightBox.getStyleClass().add("rightPane");
    return rightBox;
  }

  @Override
  protected Node setupLeftSide() {
    // TODO : ADD A WAY TO TELL THE CANVAS SIZING -- this.getCenter().boundsInLocalProperty(),
    boardCanvas = new BoardCanvas(getCallbackDispatcher());

    Pane canvasPane = boardCanvas.getCanvasPane();

    canvasPane.setId("boardCanvas");
    return canvasPane;
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
    Label xDimLabel = new Label(ViewResourcesSingleton.getInstance().getString("xDimLabel"));
    Label yDimLabel = new Label(ViewResourcesSingleton.getInstance().getString("yDimLabel"));

    //TODO CHANGE FROM MAGIC NUMBER
    xDimensionPicker = new Spinner<>(0, 50, 8, 1);
    yDimensionPicker = new Spinner<>(0, 50, 8, 1);

    VBox xDimBox = new VBox(xDimLabel, xDimensionPicker);
    VBox yDimBox = new VBox(yDimLabel, yDimensionPicker);

    numberPickerBox.getChildren().addAll(xDimBox, yDimBox);
    return numberPickerBox;
  }

  private Node setupBoardTypeBox() {

    boardTypeBox = new ComboBox<>();
    boardTypes.keySet().forEach(key -> boardTypeBox.getItems().add(boardTypes.getString(key)));


    boardTypeBox.setPromptText(ViewResourcesSingleton.getInstance().getString("boardTypePicker"));
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
    ToggleButton eraseButton = new ToggleButton(ViewResourcesSingleton.getInstance().getString("eraser"));
    eraseButton.setOnAction(e -> toggleErase(eraseButton));
    return eraseButton;
  }

  private void toggleErase(ToggleButton eraser) {
    if (eraser.isSelected()) {
      boardCanvas.setClickToErase();
      setCursor(Cursor.CROSSHAIR);
    } else {
      boardCanvas.setClickToPlace();
      setCursor(Cursor.DEFAULT);
    }
  }


  private ComboBox setupPieceChoiceBox() {
    ComboBox<String> choosePieceBox = new ComboBox<>();

    choosePieceBox.setOnMouseEntered(e -> updatePieceOptions(choosePieceBox));

    choosePieceBox.setPromptText(ViewResourcesSingleton.getInstance().getString("placePiece"));
    choosePieceBox.valueProperty().addListener(
        (observableValue, s, t1) -> boardCanvas.setCurrentPiece(t1));

    return choosePieceBox;
  }

  private void updatePieceOptions(ComboBox<String> pieceBox) {
    //TODO: Remove Magic Value
    String currVal = pieceBox.getValue();
    Collection<String> pieceNames = getCallbackDispatcher().call(new GetElementNamesCallback("piece")).orElse(new ArrayList<>());
    pieceBox.getItems().setAll(pieceNames);
    pieceBox.setValue(currVal);
  }

  private void saveBoardConfig() {
    Stage stage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    //TODO: Remove Magic Value
    directoryChooser.setTitle("Choose Configuration Save Location");
    getCallbackDispatcher().call(new SaveCallback(directoryChooser.showDialog(stage)));
  }



}