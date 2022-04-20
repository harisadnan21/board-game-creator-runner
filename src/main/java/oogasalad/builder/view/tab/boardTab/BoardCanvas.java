package oogasalad.builder.view.tab.boardTab;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.ClearCellCallback;
import oogasalad.builder.view.callback.ColorCellBackgroundCallback;
import oogasalad.builder.view.callback.FindCellBackgroundCallback;
import oogasalad.builder.view.callback.FindPieceAtCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.GetHeightCallback;
import oogasalad.builder.view.callback.GetWidthCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Mike Keoahne
 */
public class BoardCanvas extends Pane{

  public static final String EMPTY = "empty";
  private Paint colorOne;
  private Paint colorTwo;
  private Canvas boardCanvas;
  private Canvas pieceCanvas;
  private GraphicsContext boardGraphics;
  private GraphicsContext pieceGraphics;
  private Map<String, Consumer<int[]>> boardTypeFunctionMap;
  private double rectWidth;
  private double rectHeight;
  private String currentPiece;
  private int xDimension;
  private int yDimension;

  private final CallbackDispatcher callbackDispatcher;

  /**
   * constructor
   * @param dispatcher
   */
  public BoardCanvas(CallbackDispatcher dispatcher) {
    this.callbackDispatcher = dispatcher;

    setupBoard();
    populateBoardTypeMap();
  }

  /**
   * Used to set the color for each of the alternating squares
   * @param color
   * @param colorNum
   */
  public void setColor(Paint color, int colorNum){
    switch (colorNum){
      case 1 -> colorOne = color;
      case 2 -> colorTwo = color;
    }
  }

  /**
   * Called to draw the board of specified size and type after selecting the colors.
   * @param xDim
   * @param yDim
   * @param type
   * @throws NullBoardException
   */
  public void drawBoard(int xDim, int yDim, String type) throws NullBoardException {
    xDimension = xDim;
    yDimension = yDim;
    boardGraphics.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
    callbackDispatcher.call(new MakeBoardCallback(xDimension, yDimension));

    rectWidth = boardCanvas.getWidth() / xDimension;
    rectHeight = boardCanvas.getHeight() / yDimension;

    clearBoard();

    if (boardTypeFunctionMap.containsKey(type)){
      boardTypeFunctionMap.get(type).accept(new int[]{xDim, yDim});
      setClickToPlace();
    } else {
      throw new IllegalBoardTypeException(type);
    }
  }

  /**
   * Changes the size of the canvas so that it fits in the window when drawn
   * @param width
   * @param height
   */
  public void changeCanvasSize(double width, double height){

    boardCanvas.setWidth(width);
    boardCanvas.setHeight(height);

    pieceCanvas.setHeight(boardCanvas.getHeight());
    pieceCanvas.setWidth(boardCanvas.getWidth());
  }

  //Sets up the canvases
  private void setupBoard(){
    boardCanvas = new Canvas();
    boardGraphics = boardCanvas.getGraphicsContext2D();
    boardCanvas.setId("builderBoard");

    pieceCanvas = new Canvas(boardCanvas.getWidth(), boardCanvas.getHeight());
    pieceGraphics = pieceCanvas.getGraphicsContext2D();
    boardCanvas.getStyleClass().add("boardCanvas");

    this.getChildren().addAll(boardCanvas, pieceCanvas);
  }

  /**
   * Clears all of the pieces from the board
   * @throws NullBoardException
   */

  public void clearBoard() throws NullBoardException {
    pieceGraphics.clearRect(0, 0, pieceCanvas.getWidth(), pieceCanvas.getHeight());
    for (int i = 0; i < xDimension; i++) {
      for (int j = 0; j < yDimension; j++) {
        callbackDispatcher.call(new ClearCellCallback(j, i));
      }
    }
  }

  //FIXME : FIGURE OUT WHAT TO DO ABOUT POPULATING BOARD TYPES
  private void populateBoardTypeMap() {
    // TODO: Pull the Bank of Boards and create Map?

    boardTypeFunctionMap = Map.of(("Checkers"), e -> drawCheckerBoard(e[0], e[1]));
  }


  private void drawCheckerBoard(int xDim, int yDim) {
    xDimension = xDim;
    yDimension = yDim;
    for (int x = 0; x < xDimension; x++) {
      for (int y = 0; y < yDimension; y++) {
        if (((y % 2 == 0) && (x % 2 == 0)) || ((y % 2 == 1) && (x % 2 == 1))) {
          boardGraphics.setFill(colorOne);
          callbackDispatcher.call(new ColorCellBackgroundCallback(x, y, colorOne.toString()));
        } else {
          boardGraphics.setFill(colorTwo);
          callbackDispatcher.call(new ColorCellBackgroundCallback(x, y, colorTwo.toString()));
        }
        boardGraphics.fillRect(x * rectWidth, y * rectHeight, (x + 1) * rectWidth,
            (y + 1) * rectHeight);
      }
    }
  }
  /**
   * using callbacks create the board that corresponds to the model
   */
  public void loadBoard(){
    int boardHeight = callbackDispatcher.call(new GetHeightCallback()).orElseThrow();
    int boardWidth = callbackDispatcher.call(new GetWidthCallback()).orElseThrow();
    rectWidth = boardCanvas.getWidth() / boardWidth;
    rectHeight = boardCanvas.getHeight() / boardHeight;
    for (int x = 0; x < boardWidth; x++){
      for (int y = 0; y < boardHeight; y++){
        boardGraphics.setFill(Color.valueOf(
            callbackDispatcher.call(new FindCellBackgroundCallback(x,y)).orElseThrow()));
        boardGraphics.fillRect(x*rectWidth, y*rectHeight, (x + 1) * rectWidth,
            (y + 1) * rectHeight);
        System.out.println(callbackDispatcher.call(new FindCellBackgroundCallback(x,y)).orElseThrow());
        if (!callbackDispatcher.call(new FindPieceAtCallback(x,y)).orElseThrow().equals(EMPTY)){
          setCurrentPiece(callbackDispatcher.call(new FindPieceAtCallback(x,y)).orElseThrow());
          addPiece(x,y);
        }
      }
    }
  }

  /**
   * Set current piece to be placed on board
   * @param pieceName
   */
  public void setCurrentPiece(String pieceName){
    currentPiece = pieceName;
  }

  /**
   * Sets click action to erase pieces
   */
  public void setClickToErase(){
    pieceCanvas.setOnMouseClicked(this::erasePiece);
  }

  /**
   * Sets click action to place pieces
   */
  public void setClickToPlace(){
    pieceCanvas.setOnMouseClicked(this::addPieceOnClick);
  }

  /**
   * Sets click action to set the color of a board square
   * @param colorPicker
   */
  public void setClickToEditBoard(ColorPicker colorPicker){
    pieceCanvas.setOnMouseClicked(click -> changeCellColor(click, colorPicker.getValue()));
  }

  //erases piece
  private void erasePiece(MouseEvent click){
    int[] blockIndex = findSquare(click.getX(), click.getY());
    pieceGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    callbackDispatcher.call(new ClearCellCallback(blockIndex[0], blockIndex[1]));
  }
  //changes cell color for square clicked on
  private void changeCellColor(MouseEvent click, Paint color){
    int[] blockIndex = findSquare(click.getX(), click.getY());
    boardGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    boardGraphics.setFill(color);
    boardGraphics.fillRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    callbackDispatcher.call(new ColorCellBackgroundCallback(blockIndex[0], blockIndex[1], color.toString()));
  }

  //adds a piece to the board where it is clicked
  private void addPieceOnClick(MouseEvent click)
      throws NullBoardException, ElementNotFoundException {

    if (currentPiece == null){
      return;
    }

    double clickX = click.getX();
    double clickY = click.getY();

    int[] blockIndex = findSquare(clickX, clickY);
    addPiece(blockIndex[0], blockIndex[1]);
  }

  //adds piece on board at specified grid location
  private void addPiece(int xloc, int yloc){
    callbackDispatcher.call(new PlacePieceCallback(xloc, yloc, currentPiece));

    String filePath =  callbackDispatcher.call(new GetElementPropertyByKeyCallback("piece", currentPiece, "image")).orElseThrow();
    Image pieceImage = new Image(new File(filePath).toURI().toString());
    pieceGraphics.drawImage(pieceImage,xloc * rectWidth, yloc * rectHeight, rectWidth, rectHeight);

  }

  //finds the square clicked on
  private int[] findSquare(double xCord, double yCord){
    double xPos = xCord / rectWidth;
    double yPos = yCord / rectHeight;

    return new int[]{(int) xPos, (int) yPos};
  }

  //gets the colors of the squares drawn FOR TESTING ONLY!
  Paint getColor(int index) {
    return switch(index) {
      case 2 -> colorTwo;
      default -> colorOne;
    };
  }

}
