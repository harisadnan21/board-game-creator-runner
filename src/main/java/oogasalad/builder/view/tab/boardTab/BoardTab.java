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
  private ResourceBundle tabProperties;
  private static final ResourceBundle boardTypes = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_PACKAGE + BOARD_PROPERTIES);

  public BoardTab(ResourceBundle tabRb, CallbackDispatcher dispatcher) {
    super(BOARD_TYPE, dispatcher);
    tabProperties = tabRb;
  }

  @Override
  protected Node setupRightSide() {
    VBox rightBox = new VBox();

    rightBox.getChildren()
        .addAll(setupButtonBar(), setupBoardEditChoiceToggle(), setupBoardConfigInput());
    rightBox.setId("rightBoardPane");
    rightBox.getStyleClass().add("rightPane");
    return rightBox;
  }

  @Override
  protected Node setupLeftSide() {
    boardCanvas = new BoardCanvas(getCallbackDispatcher());

    return boardCanvas;
  }

  private Node setupBoardConfigInput() {
    VBox boardConfigBox = new VBox();

    Button confirmBoardButton = makeButton("drawBoard", e ->
        createBoard());
    confirmBoardButton.setId("drawBoard");

    boardConfigBox.getChildren()
        .addAll(setupColorChoiceBox(), setupDimensionChoiceBox(), setupBoardTypeBox(),
            confirmBoardButton);
    boardConfigBox.setId("boardConfigBox");
    boardConfigBox.getStyleClass().add("boardConfigBox");
    return boardConfigBox;
  }

  private Node setupColorChoiceBox() {
    HBox colorChoiceBox = new HBox();

    colorPickerA = new ColorPicker();
    colorPickerB = new ColorPicker(Color.BLACK);
    colorPickerA.setId("colorPickerA");
    colorPickerB.setId("colorPickerB");
    colorChoiceBox.getChildren().addAll(colorPickerA, colorPickerB);
    return colorChoiceBox;
  }

  private Node setupDimensionChoiceBox() {
    HBox numberPickerBox = new HBox();
    Label xDimLabel = new Label(ViewResourcesSingleton.getInstance().getString("xDimLabel"));
    Label yDimLabel = new Label(ViewResourcesSingleton.getInstance().getString("yDimLabel"));

    xDimensionPicker = new Spinner<>(Integer.parseInt(tabProperties.getString("numPickerMin")),
        Integer.parseInt(tabProperties.getString("numPickerMax")),
        Integer.parseInt(tabProperties.getString("defaultBoardX")),
        Integer.parseInt(tabProperties.getString("numPickerStep")));
    yDimensionPicker = new Spinner<>(Integer.parseInt(tabProperties.getString("numPickerMin")),
        Integer.parseInt(tabProperties.getString("numPickerMax")),
        Integer.parseInt(tabProperties.getString("defaultBoardY")),
        Integer.parseInt(tabProperties.getString("numPickerStep")));
    xDimensionPicker.setEditable(true);
    yDimensionPicker.setEditable(true);
    xDimensionPicker.setId("xDimEntry");
    yDimensionPicker.setId("yDimEntry");

    VBox xDimBox = new VBox(xDimLabel, xDimensionPicker);
    VBox yDimBox = new VBox(yDimLabel, yDimensionPicker);

    numberPickerBox.getChildren().addAll(xDimBox, yDimBox);
    return numberPickerBox;
  }

  private Node setupBoardTypeBox() {

    boardTypeBox = new ComboBox<>();
    boardTypes.keySet().forEach(key -> boardTypeBox.getItems().add(boardTypes.getString(key)));

    boardTypeBox.setPromptText(ViewResourcesSingleton.getInstance().getString("boardTypePicker"));
    boardTypeBox.setId("boardTypePicker");
    return boardTypeBox;
  }

  private void createBoard()
      throws NullBoardException {
    if (boardTypeBox.getValue() == null) {
      throw new IllegalBoardTypeException("");
    }

    boardCanvas.setColor(colorPickerA.getValue(), 1);
    boardCanvas.setColor(colorPickerB.getValue(), 2);
    boardCanvas.changeCanvasSize(
        getCenter().getBoundsInParent().getWidth() * getSplitPane().getDividerPositions()[0],
        getCenter().getBoundsInParent().getHeight());
    boardCanvas.drawBoard(xDimensionPicker.getValue(), yDimensionPicker.getValue(),
        boardTypeBox.getValue());
  }

  private Node setupButtonBar() {
    VBox buttonBox = new VBox();
    Button saveButton = makeButton("saveBoard", e -> saveBoardConfig());

    Button resetPiecesButton = makeButton("clearPieces", e -> boardCanvas.clearBoard());
    resetPiecesButton.setId("clearPieces");

    buttonBox.getChildren()
        .addAll(saveButton, setupPieceChoiceBox(), createEraserButton(), resetPiecesButton);
    buttonBox.setId("buttonBox");
    buttonBox.getStyleClass().add("boardConfigBox");
    return buttonBox;
  }

  private ToggleButton createEraserButton() {
    ToggleButton eraseButton = new ToggleButton(
        ViewResourcesSingleton.getInstance().getString("eraser"));
    eraseButton.setOnAction(e -> toggleErase(eraseButton));
    eraseButton.setId("eraserButton");
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
    choosePieceBox.setId("choosePieceBox");

    return choosePieceBox;
  }

  private Node setupBoardEditChoiceToggle() {
    VBox editBoardBox = new VBox();
    ColorPicker boardEditColorPicker = new ColorPicker();

    ToggleButton editBoardButton = new ToggleButton(
        ViewResourcesSingleton.getInstance().getString("editSquare"));
    editBoardButton.setOnAction(e -> toggleEditBoard(editBoardButton, boardEditColorPicker));

    editBoardBox.getChildren().addAll(editBoardButton, boardEditColorPicker);
    editBoardBox.getStyleClass().add("boardConfigBox");
    return editBoardBox;
  }

  private void toggleEditBoard(ToggleButton editBoardToggle, ColorPicker colorPicker) {
    if (editBoardToggle.isSelected()) {
      boardCanvas.setClickToEditBoard(colorPicker);
      setCursor(Cursor.HAND);
    } else {
      boardCanvas.setClickToPlace();
      setCursor(Cursor.DEFAULT);
    }
  }

  private void updatePieceOptions(ComboBox<String> pieceBox) {
    //TODO: Remove Magic Value
    String currVal = pieceBox.getValue();
    Collection<String> pieceNames = getCallbackDispatcher().call(
        new GetElementNamesCallback("piece")).orElse(new ArrayList<>());
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


  // For testing
  BoardCanvas getBoardCanvas() {
    return boardCanvas;
  }


}