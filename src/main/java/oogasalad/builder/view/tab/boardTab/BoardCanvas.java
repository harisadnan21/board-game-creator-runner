package oogasalad.builder.view.tab.boardTab;


import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.property.Property;

public class BoardCanvas {


  private Paint colorOne;
  private Paint colorTwo;
  private Canvas boardCanvas;
  private Canvas pieceCanvas;
  private ResourceBundle resources;
  private GraphicsContext boardGraphics;
  private GraphicsContext pieceGraphics;
  private Map<String, Consumer<int[]>> boardTypeFunctionMap;
  private double rectWidth;
  private double rectHeight;
  private int[][] containsPiece;
  private BorderPane borderPane;
  private String currentPiece;

  private BuilderController controller; //FIXME: Use Event handlers instead of this

  public BoardCanvas(ResourceBundle rb, BorderPane boardTab, BuilderController controller) {
    resources = rb;
    borderPane = boardTab;
    this.controller = controller;

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
    boardGraphics.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
    calculateAndChangeCanvasSize();
    controller.makeBoard(xDim, yDim);

    rectWidth = boardCanvas.getWidth() / xDim;
    rectHeight = boardCanvas.getHeight() / yDim;

    containsPiece = new int[xDim][yDim];
    clearBoard();


    if (boardTypeFunctionMap.containsKey(type)){
      boardTypeFunctionMap.get(type).accept(new int[]{xDim, yDim});
      setClickToPlace();
    }
    else{
      //TODO : THROW EXCEPTION
      System.out.println("not a board type");
    }
  }
  private void calculateAndChangeCanvasSize(){
    boardCanvas.setWidth(borderPane.getWidth() - borderPane.getRight().getBoundsInParent().getWidth());
    boardCanvas.setHeight(borderPane.getHeight() - borderPane.getTop().getBoundsInParent().getHeight());

    pieceCanvas.setHeight(boardCanvas.getHeight());
    pieceCanvas.setWidth(boardCanvas.getWidth());
  }
  public void setupBoard(){
    boardCanvas = new Canvas(Integer.parseInt(resources.getString("boardSizeX")), Integer.parseInt(resources.getString("boardSizeY")));
    boardGraphics = boardCanvas.getGraphicsContext2D();
    boardCanvas.setId("builderBoard");

    pieceCanvas = new Canvas(boardCanvas.getWidth(), boardCanvas.getHeight());
    pieceGraphics = pieceCanvas.getGraphicsContext2D();
  }

  public int[][] getBoardConfig(){
    return containsPiece;
  }

  public void clearBoard() throws NullBoardException {
    pieceGraphics.clearRect(0, 0, pieceCanvas.getWidth(), pieceCanvas.getHeight());
    containsPiece = new int[containsPiece[0].length][containsPiece.length];
    for (int i = 0; i < containsPiece.length; i++) {
      for (int j = 0; j < containsPiece[0].length; j++) {
        controller.clearCell(j, i);
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

    boardTypeFunctionMap = Map.of(resources.getString("checkers"), e -> drawCheckerBoard(e[0], e[1]));
  }


  private void drawCheckerBoard(int xDim, int yDim) {
    for (int x = 0; x < xDim; x++) {
      for (int y = 0; y < yDim; y++) {
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

  private void erasePiece(MouseEvent click){
    int[] blockIndex = findSquare(click.getX(), click.getY());
    pieceGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
    controller.clearCell(blockIndex[0], blockIndex[1]);
  }

  private void addPiece(MouseEvent click)
      throws OccupiedCellException, NullBoardException, ElementNotFoundException {

    if (currentPiece == null){
      System.out.println("No piece Selected");
      return;
    }

    double clickX = click.getX();
    double clickY = click.getY();

    int[] blockIndex = findSquare(clickX, clickY);
    System.out.println(" xPos: " + blockIndex[0] + " yPos: " + blockIndex[1]);

    controller.placePiece(blockIndex[0], blockIndex[1], currentPiece);

    String filePath =  controller.getElementPropertyByKey("piece", currentPiece, "image");

    Image pieceImage = new Image(filePath);
    pieceGraphics.drawImage(pieceImage,blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);

  }

  private int[] findSquare(double xCord, double yCord){
    double xPos = xCord / rectWidth;
    double yPos = yCord / rectHeight;

    return new int[]{(int) xPos, (int) yPos};
  }

}
