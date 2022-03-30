package oogasalad.builder.view.tab.boardTab;


import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BoardCanvas {

  public static double DEFAULT_CANVAS_HEIGHT = 600;
  public static double DEFAULT_CANVAS_WIDTH = 600;
  public static Paint COLOR_ONE = Color.WHITE;
  public static Paint COLOR_TWO = Color.BLACK;
  private Canvas boardCanvas;
  private ResourceBundle resources;
  private GraphicsContext canvasGraphics;

  public BoardCanvas(int xSize, int ySize) {

    boardCanvas = new Canvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
    canvasGraphics = boardCanvas.getGraphicsContext2D();

    //TODO: Get type from some sort of input
    drawCheckerBoard(xSize, ySize);
  }

  public Node toNode() {
    return boardCanvas;
  }


  private Map populateBoardTypeMap() {
    // TODO: Pull the Bank of Boards and create Map?
    return null;
  }


  private void drawCheckerBoard(int xDim, int yDim) {
    double rect_width = DEFAULT_CANVAS_WIDTH / xDim;
    double rect_height = DEFAULT_CANVAS_HEIGHT / yDim;
    for (int x = 0; x < xDim; x++) {
      for (int y = 0; y < yDim; y++) {
        if (((y % 2 == 0) && (x % 2 == 0)) || ((y % 2 == 1) && (x % 2 == 1))) {
          canvasGraphics.setFill(COLOR_ONE);
        } else {
          canvasGraphics.setFill(COLOR_TWO);
        }
        canvasGraphics.fillRect(x * rect_width, y * rect_height, (x + 1) * rect_width,
            (y + 1) * rect_height);
      }
    }
  }


}
