package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.parser.CellParser;
import oogasalad.engine.model.parser.PieceParser;
import oogasalad.engine.view.dashboard.GameIcon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

public class BoardView implements PropertyChangeListener{
  //TODO: add file path and strings
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";
  public static final String GAME_PATH = "games/";
  private FileInputStream fis = new FileInputStream("data/Properties/BoardViewProperties.properties");
  private static final Logger LOG = LogManager.getLogger(BoardView.class);
  public static String IMAGES_FOLDER = "images/";
  private ResourceBundle myResources;
  private String cssFilePath;

  private Map<Integer, String> PIECE_TYPES = new HashMap<>();

  private Controller myController;

  private Cell[][] myGrid;

  private StackPane root;
  private GridPane gridRoot;
  private GameUpdateText text;

  private String BLACK_KNIGHT;
  String WHITE_KNIGHT;
  double BOARD_OUTLINE_SIZE;
  private Properties prop;
  public BoardView(File game, int rows, int columns, double width, double height,
      String css)
      throws IOException {
    cssFilePath = css;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    prop = new Properties();
    prop.load(fis);
    BLACK_KNIGHT = IMAGES_FOLDER + prop.getProperty("BLACKNIGHT");
    WHITE_KNIGHT = IMAGES_FOLDER + prop.getProperty("WHITENIGHT");
    BOARD_OUTLINE_SIZE = Double.parseDouble(prop.getProperty("BOARDOUTLINESIZE"));

    setPiecePaths(game);


    text = new GameUpdateText();
    root = new StackPane();
    gridRoot = new GridPane();
    myGrid = new Cell[rows][columns];

    Pair<Double, Double> cellSize = calcCellSize(rows, columns, width, height);
    double cellWidth = cellSize.getKey();
    double cellHeight = cellSize.getValue();

    makeBoardBacking(width, height, cellSize, rows, columns);
    makeBoard(rows, columns, cellWidth, cellHeight, game);
//    for (int i = 0; i < rows; i++) {
//      for (int j = 0; j < columns; j++) {
//        Cell temp = new Cell(i, j, cellWidth, cellHeight);
//        gridRoot.add(temp.getMyRoot(), j, i); // documentation says the first input is column and the second is row
//        myGrid[i][j] = temp;
//
//        int finalI = i;
//        int finalJ = j;
//
//        myGrid[i][j].getMyRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
//          try {
//            cellClicked(e, finalI, finalJ);
//          } catch (OutOfBoardException ex) {
//            ex.printStackTrace();
//          }
//        });
//      }
//    }
//    root.getChildren().add(gridRoot);
//    gridRoot.setAlignment(Pos.CENTER);
//
//    root.setAlignment(Pos.CENTER);
  }

  private void makeBoard(int rows, int columns, double cellWidth, double cellHeight, File game)
      throws FileNotFoundException {
    Optional<String[][]> colorConfig = getCellColors(game);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Optional<String> color = colorConfig.isPresent() ? Optional.of(colorConfig.get()[i][j]) : Optional.empty();
        Cell temp = new Cell(i, j, cellWidth, cellHeight, color);
        gridRoot.add(temp.getMyRoot(), j, i); // documentation says the first input is column and the second is row
        myGrid[i][j] = temp;

        int finalI = i;
        int finalJ = j;

        myGrid[i][j].getMyRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
          try {
            cellClicked(e, finalI, finalJ);
          } catch (OutOfBoardException ex) {
            ex.printStackTrace();
          }
        });
      }
    }
    root.getChildren().add(gridRoot);
    gridRoot.setAlignment(Pos.CENTER);

    root.setAlignment(Pos.CENTER);
  }

//  private Optional<String[][]> getCellColors(File game) throws FileNotFoundException {
//    CellParser cParser = new CellParser();
//    return cParser.parse(game);
//  }

  private Optional<String[][]> getCellColors(File game) throws FileNotFoundException {
    CellParser cParser = new CellParser();
    try {
      return Optional.of(cParser.parse(game.listFiles(GameIcon.getConfigFile)[0]));
    }
    catch (JSONException e) {
      return Optional.empty();
    }
  }

  private void setPiecePaths(File game) throws FileNotFoundException {
    PieceParser parser = new PieceParser();
    Map<Integer, String> pieces = getConfigFile(game, parser);
    for(Entry<Integer, String> entry : pieces.entrySet()){
      PIECE_TYPES.put(entry.getKey(), GAME_PATH + game.getName()+ entry.getValue());
    }
  }

  private Map<Integer, String> getConfigFile(File game, PieceParser parser) {
    Map<Integer, String> pieces = null;
    try {
      pieces = parser.parse(game.listFiles(GameIcon.getConfigFile)[0]);
    }
    catch(FileNotFoundException e){
      LOG.error("Config File Not Found");

    }
    return pieces;
  }

  public void cellClicked(MouseEvent e, int i, int j)
      throws OutOfBoardException {
    myController.click(i, j);
    selectCell(i, j);
  }

  public void addController(Controller c) {
    myController = c;
    Board board = myController.setCallbackUpdates(this::updateBoard, this::setValidMarkers);
    updateBoard(board);
  }

  public Text getText() {
    return text.getUpdateText();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Board board = (Board) evt.getSource();
    updateBoard(board);
  }

  private void makeBoardBacking(double width, double height, Pair<Double, Double> cellSize, int rows, int cols) {

    Rectangle foundation = new Rectangle(width, height);
    foundation.setId("board-foundation");

    System.out.println(cellSize.toString());
    double cellW = cellSize.getKey();
    double cellH = cellSize.getValue();


    Rectangle outline = new Rectangle((cellW*cols)+BOARD_OUTLINE_SIZE, (cellH*rows)+BOARD_OUTLINE_SIZE);
    outline.setId("board-outline");
    root.getChildren().addAll(foundation, outline);
  }
//  private void setupPieces(File game){
//    File
//  }

  private Pair<Double, Double> calcCellSize(int rows, int cols, double width, double height) {
    double cellWidth = width / (rows + 1);
    double cellHeight = height / (cols + 1);

    return new Pair<>(cellWidth, cellHeight);
  }

  private void updateBoard(Object newBoard) {
    Board board = (Board)newBoard;
    text.updateText(board.getPlayer());
    for (PositionState cell: board) {
      Position pos = cell.position();
      if (cell.isPresent()) {
        myGrid[pos.i()][pos.j()].addPiece(PIECE_TYPES.get(cell.player()));
      }
      else {
        try {
          myGrid[pos.i()][pos.j()].removePiece();
        } catch (Exception e) {
          LOG.warn("Exception thrown", e);
        }
      }
    }
    checkForWin(board);
    text.updateText(board.getPlayer());
  }

  //checks to see if the winner variable in the returned new board has a valid winner value to end the game.
  private void checkForWin(Board board) {
    if(board.getWinner() != Board.NO_WINNER_YET){
      text.gameIsWon(board.getWinner());
      LOG.info("gameOver! Player {} wins%n", board.getWinner());
      displayGameOver(board);
      Board newBoard = myController.resetGame();
      updateBoard(newBoard);
    }
  }

  private void displayGameOver(Board board) {
    myController.resetGame();
    root.setEffect(new GaussianBlur());
    MessageView pauseView = new MessageView(
        MessageFormat.format(myResources.getString("GameOver"), board.getWinner()),
        myResources.getString("NewGame"), cssFilePath);
    Stage popupStage = pauseView.getStage();
    pauseView.getButton().setOnAction(event -> {
      root.setEffect(null);
      popupStage.hide();
    });
    popupStage.show();
  }

  /**
   * Adds a marker to all the cells that are valid moves for the currently selected piece
   * @param validMoves - current Game Board
   */
  private void setValidMarkers(Set<Position> validMoves) {
    clearValidMarks();
    for(Position pos : validMoves){
      myGrid[pos.i()][pos.j()].addValidMarker();
    }

  }

  /**
   * Removes all the current valid moves markers from the scene. Called after selecting a move
   * or clicking off a selected piece
   */
  private void clearValidMarks(){
    for (Cell[] row : myGrid){
      for(Cell cell : row){
        cell.removeValidMarker();
      }
    }
  }

  private void selectCell(int i, int j) {
    clearCellSelection();
    Cell cell = myGrid[i][j];
    if (cell.containsPiece()) {
      myGrid[i][j].addSelectedHighlight();
    }
  }

  private void clearCellSelection() {
    for (Cell[] row : myGrid){
      for(Cell cell : row){
        cell.removeHighlight();
      }
    }
  }


  private Position getIndices(int index) {
    int i = index / myGrid.length;
    int j = index % myGrid[0].length;
    return new Position(i, j);
  }

  public Node getRoot() {
    return root;
  }
}
