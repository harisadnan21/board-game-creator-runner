package oogasalad.builder.view.tab.boardTab;

import static oogasalad.builder.view.BuilderView.tabProperties;

import java.util.ArrayList;
import java.util.Collection;
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
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.tab.AbstractTab;


/**
 * The BoardTab handles the ability to create an edit a board as well as configure the starting
 * positions for pieces in the game.
 *
 * @author Mike Keohane
 */
public class BoardTab extends AbstractTab {
  private static final String PIECE_TYPE = "piece";
  public static final String BOARD_TYPE = "board";
  public static final int A_NUM = 1;
  public static final int B_NUM = 2;
  private BoardCanvas boardCanvas;
  private Spinner<Integer> xDimensionPicker;
  private Spinner<Integer> yDimensionPicker;
  private ColorPicker colorPickerA;
  private ColorPicker colorPickerB;

  /**
   * Default Constructor to create the BoardTab which extends BasicTab.
   *
   * @param dispatcher - the callback dispatcher to be able to call model methods
   */
  public BoardTab(CallbackDispatcher dispatcher) {
    super(BOARD_TYPE, dispatcher);

  }

  /**
   * Sets up the right side as a VBox of various buttons and selectors to specify options for the
   * board.
   *
   * @return rightBox - to be added to the scene
   */
  @Override
  protected Node setupRightSide() {
    VBox rightBox = new VBox();

    rightBox.getChildren()
        .addAll(setupButtonBar(), setupBoardEditChoiceToggle(), setupBoardConfigInput());
    rightBox.setId("rightBoardPane");
    rightBox.getStyleClass().add("rightPane");
    return rightBox;
  }

  /**
   * Creates a board and adds pieces based on the configuration loaded from a file in the model.
   */
  @Override
  public void loadElements() {
    boardCanvas.changeCanvasSize(
        getCenter().getBoundsInParent().getWidth() * getSplitPane().getDividerPositions()[0],
        getCenter().getBoundsInParent().getHeight());
    boardCanvas.loadBoard();
  }

  /**
   * Sets up the boardCanvas and returns it as the left side
   *
   * @return
   */
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
        .addAll(setupColorChoiceBox(), setupDimensionChoiceBox(),
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

    xDimensionPicker = new Spinner<>(Integer.parseInt(tabProperties.getString("minBoardSize")),
            Integer.MAX_VALUE,
            Integer.parseInt(tabProperties.getString("defaultBoardX")),
            1);
    yDimensionPicker = new Spinner<>(Integer.parseInt(tabProperties.getString("minBoardSize")),
            Integer.MAX_VALUE,
            Integer.parseInt(tabProperties.getString("defaultBoardY")),
            1);
    xDimensionPicker.setEditable(true);
    yDimensionPicker.setEditable(true);
    VBox xDimBox = new VBox(xDimLabel, xDimensionPicker);
    VBox yDimBox = new VBox(yDimLabel, yDimensionPicker);

    numberPickerBox.getChildren().addAll(xDimBox, yDimBox);
    return numberPickerBox;
  }

  private void createBoard() {
    boardCanvas.setColor(colorPickerA.getValue(), A_NUM);
    boardCanvas.setColor(colorPickerB.getValue(), B_NUM);
    boardCanvas.changeCanvasSize(
        getCenter().getBoundsInParent().getWidth() * getSplitPane().getDividerPositions()[0],
        getCenter().getBoundsInParent().getHeight());
    boardCanvas.drawBoard(xDimensionPicker.getValue(), yDimensionPicker.getValue());
  }

  private Node setupButtonBar() {
    VBox buttonBox = new VBox();

    Button resetPiecesButton = makeButton("clearPieces", e -> boardCanvas.clearBoard());
    resetPiecesButton.setId("clearPieces");
    buttonBox.getChildren()
        .addAll(setupPieceChoiceBox(), createEraserButton(), resetPiecesButton);
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
    String currVal = pieceBox.getValue();
    Collection<String> pieceNames = getCallbackDispatcher().call(
        new GetElementNamesCallback(PIECE_TYPE)).orElse(new ArrayList<>());
    pieceBox.getItems().setAll(pieceNames);
    pieceBox.setValue(currVal);
  }

  // For testing
  BoardCanvas getBoardCanvas() {
    return boardCanvas;
  }


}