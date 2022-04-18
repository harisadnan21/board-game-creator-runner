package oogasalad.builder.view.tab.boardTab;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.ClearCellCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public class BoardCanvas {

  private Paint colorOne;
  private Paint colorTwo;
  private Canvas boardCanvas;
  private Canvas pieceCanvas;
  private GraphicsContext boardGraphics;
  private GraphicsContext pieceGraphics;
  private Map<String, Consumer<int[]>> boardTypeFunctionMap;
  private double rectWidth;
  private double rectHeight;
  private ReadOnlyObjectProperty
      <javafx.geometry.Bounds> centerBoundProperty;
  private String currentPiece;
  private int xDimension;
  private int yDimension;

  private final CallbackDispatcher callbackDispatcher;

  public BoardCanvas(CallbackDispatcher dispatcher) {
    this.callbackDispatcher = dispatcher;

    setupBoard();
    populateBoardTypeMap();
  }

  public void setColor(Paint color, int colorNum){
    switch (colorNum){
      case 1 -> colorOne = color;
      case 2 -> colorTwo = color;
    }
  }


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
  public void changeCanvasSize(double width, double height){

    boardCanvas.setWidth(width);
    boardCanvas.setHeight(height);

    pieceCanvas.setHeight(boardCanvas.getHeight());
    pieceCanvas.setWidth(boardCanvas.getWidth());
  }
  public void setupBoard(){
    boardCanvas = new Canvas();
    boardGraphics = boardCanvas.getGraphicsContext2D();
    boardCanvas.setId("builderBoard");

    pieceCanvas = new Canvas(boardCanvas.getWidth(), boardCanvas.getHeight());
    pieceGraphics = pieceCanvas.getGraphicsContext2D();
    boardCanvas.getStyleClass().add("boardCanvas");
  }


  public void clearBoard() throws NullBoardException {
    pieceGraphics.clearRect(0, 0, pieceCanvas.getWidth(), pieceCanvas.getHeight());
    for (int i = 0; i < xDimension; i++) {
      for (int j = 0; j < yDimension; j++) {
        callbackDispatcher.call(new ClearCellCallback(j, i));
      }
    }
  }

  public Pane getCanvasPane() {
    Pane ret = new Pane();
    ret.getChildren().addAll(boardCanvas, pieceCanvas);
    return ret;
  }

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
        } else {
          boardGraphics.setFill(colorTwo);
        }
        boardGraphics.fillRect(x * rectWidth, y * rectHeight, (x + 1) * rectWidth,
            (y + 1) * rectHeight);
      }
    }
  }


  public void setCurrentPiece(String pieceName){
    currentPiece = pieceName;
  }

  public void setClickToErase(){
    pieceCanvas.setOnMouseClicked(this::erasePiece);
  }
  public void setClickToPlace(){
    pieceCanvas.setOnMouseClicked(this::addPiece);
  }
  public void setClickToEditBoard(ColorPicker colorPicker){
    pieceCanvas.setOnMouseClicked(click -> changeCellColor(click, colorPicker.getValue()));
  }

  private void erasePiece(MouseEvent click){
    int[] blockIndex = findSquare(click.getX(), click.getY());
    pieceGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    callbackDispatcher.call(new ClearCellCallback(blockIndex[0], blockIndex[1]));
  }
  private void changeCellColor(MouseEvent click, Paint color){
    int[] blockIndex = findSquare(click.getX(), click.getY());
    boardGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    boardGraphics.setFill(color);
    boardGraphics.fillRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
  }

  private void addPiece(MouseEvent click)
      throws NullBoardException, ElementNotFoundException {

    if (currentPiece == null){
      System.out.println("No piece Selected");
      return;
    }

    double clickX = click.getX();
    double clickY = click.getY();

    int[] blockIndex = findSquare(clickX, clickY);

    callbackDispatcher.call(new PlacePieceCallback(blockIndex[0], blockIndex[1], currentPiece));

    String filePath =  callbackDispatcher.call(new GetElementPropertyByKeyCallback("piece", currentPiece, "image")).orElseThrow();
    Image pieceImage = new Image(new File(filePath).toURI().toString());
    pieceGraphics.drawImage(pieceImage,blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);

  }

  private int[] findSquare(double xCord, double yCord){
    double xPos = xCord / rectWidth;
    double yPos = yCord / rectHeight;

    return new int[]{(int) xPos, (int) yPos};
  }

  Paint getColor(int index) {
    return switch(index) {
      case 2 -> colorTwo;
      default -> colorOne;
    };
  }

}
