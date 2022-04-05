package oogasalad.builder.view.tab.boardTab;


import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BoardCanvas {

  public static double DEFAULT_CANVAS_HEIGHT = 600;
  public static double DEFAULT_CANVAS_WIDTH = 600;
  public static Paint COLOR_ONE = Color.WHITE;
  public static Paint COLOR_TWO = Color.BLACK;
  private Canvas boardCanvas;
  private Canvas pieceCanvas;
  private ResourceBundle resources;
  private GraphicsContext boardGraphics;
  private GraphicsContext pieceGraphics;
  private double rectWidth;
  private double rectHeight;
  private int[][] containsPiece;

  public BoardCanvas(int xSize, int ySize) {

    boardCanvas = new Canvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
    boardGraphics = boardCanvas.getGraphicsContext2D();

    pieceCanvas = new Canvas(boardCanvas.getWidth(), boardCanvas.getHeight());
    pieceGraphics = pieceCanvas.getGraphicsContext2D();


    containsPiece = new int[xSize][ySize];



    //TODO: Bind these values to the size of canvas
    rectWidth = boardCanvas.getWidth() / xSize;
    rectHeight = boardCanvas.getHeight() / ySize;
    //TODO: Get type from some sort of input

    drawCheckerBoard(xSize, ySize);
    addClickHandeling();
  }

  public int[][] getBoardConfig(){
    return containsPiece;
  }

  public StringBuilder printBoardConfig(){
    StringBuilder ret = new StringBuilder();
    for (int x = 0; x < containsPiece[0].length; x++){
      for (int y = 0; y < containsPiece.length; y++) {
        ret.append(x + "," + y + ": " + containsPiece[x][y] +"\n");
      }
    }
    return ret;
  }

  public Pane getCanvasPane() {
    Pane ret = new Pane();
    ret.getChildren().addAll(boardCanvas, pieceCanvas);
    return ret;
  }

  private Map populateBoardTypeMap() {
    // TODO: Pull the Bank of Boards and create Map?
    return null;
  }


  private void drawCheckerBoard(int xDim, int yDim) {
    for (int x = 0; x < xDim; x++) {
      for (int y = 0; y < yDim; y++) {
        if (((y % 2 == 0) && (x % 2 == 0)) || ((y % 2 == 1) && (x % 2 == 1))) {
          boardGraphics.setFill(COLOR_ONE);
        } else {
          boardGraphics.setFill(COLOR_TWO);
        }
        boardGraphics.fillRect(x * rectWidth, y * rectHeight, (x + 1) * rectWidth,
            (y + 1) * rectHeight);
      }
    }
  }

  private void addClickHandeling(){
    pieceCanvas.setOnMouseClicked(e -> addPiece(e));
  }

  private void addPiece(MouseEvent click){
    double clickX = click.getX();
    double clickY = click.getY();

    int[] blockIndex = findSquare(clickX, clickY);
    System.out.println(" xPos: " + blockIndex[0] + " yPos: " + blockIndex[1]);

    // TODO: ADD OPTIONS FOR ADDING PIECES THEN ACTUALLY ADD PIECES
    if (containsPiece[blockIndex[0]][blockIndex[1]] > 0){
      pieceGraphics.clearRect(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
      containsPiece[blockIndex[0]][blockIndex[1]] = 0;
    }
    else{
      pieceGraphics.setFill(Color.BLUE);
      pieceGraphics.fillOval(blockIndex[0] * rectWidth, blockIndex[1] * rectHeight, rectWidth, rectHeight);
      containsPiece[blockIndex[0]][blockIndex[1]] = 1;

    }

  }

  private int[] findSquare(double xCord, double yCord){
    double xPos = xCord / rectWidth;
    double yPos = yCord / rectHeight;

    return new int[]{(int) xPos, (int) yPos};
  }





}
