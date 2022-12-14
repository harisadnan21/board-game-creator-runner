package oogasalad.engine.view.game;

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
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.parser.CellParser;
import oogasalad.engine.model.parser.MetadataParser;
import oogasalad.engine.model.parser.PieceParser;
import oogasalad.engine.view.ApplicationAlert;
import oogasalad.engine.view.Popup.MessageView;
import oogasalad.engine.view.setup.dashboard.GameIcon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

public class BoardView implements PropertyChangeListener {
  public static final String ENGINE_VIEW_RESOURCES = "/engine-view/";
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static final String GAME_PATH = "games/";
  private FileInputStream fis = new FileInputStream("data/Properties/BoardViewProperties.properties");
  private static final Logger LOG = LogManager.getLogger(BoardView.class);
  public static String IMAGES_FOLDER = ENGINE_VIEW_RESOURCES + "images/";
  private ResourceBundle myResources;
  private String cssFilePath;

  private Map<Integer, String> PIECE_TYPES = new HashMap<>();
  private Map<String, String> metadata = new HashMap<>();

  private Controller myController;

  private Cell[][] myGrid;

  private StackPane root;
  private GridPane gridRoot;
  private GameUpdateText text;
  private boolean gameIsUploadedFile;
  private String language;
  double BOARD_OUTLINE_SIZE;
  private Properties prop;

  /**
   *
   *
   *
   * @param controller
   * @param game
   * @param initialBoard
   * @param width
   * @param height
   * @param css
   * @param language
   * @throws IOException
   *
   * @author Cynthia France, Robert Crantson, Jake Heller, Haris Adnan
   */
  public BoardView(Controller controller, File game, Board initialBoard, double width, double height,
      String css, String language)
      throws IOException {
    this.language = language;
    cssFilePath = css;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    prop = new Properties();
    prop.load(fis);
    BOARD_OUTLINE_SIZE = Double.parseDouble(prop.getProperty("BOARDOUTLINESIZE"));

    myController = controller;

    gameIsUploadedFile = true;

    getMetadata(game);

    setPiecePaths(game);

    text = new GameUpdateText(language);
    root = new StackPane();
    gridRoot = new GridPane();
    gridRoot.setId("grid");

    int rows = initialBoard.getHeight();
    int columns = initialBoard.getWidth();
    myGrid = new Cell[rows][columns];

    Pair<Double, Double> cellSize = calcCellSize(rows, columns, width, height);
    double cellWidth = cellSize.getKey();
    double cellHeight = cellSize.getValue();

    makeBoardBacking(width, height, cellSize, rows, columns);
    makeBoard(rows, columns, cellWidth, cellHeight, game);

    updateBoard(initialBoard);
  }

  public String getGameInfo() {
    return metadata.get("description");
  }

  public void cellClicked(MouseEvent e, int i, int j)
      throws OutOfBoardException {
    myController.click(i, j);
    selectCell(i, j);
  }

  public Text getText() {
    return text.getUpdateText();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    LOG.info("Board changed");
    Board board = (Board) evt.getNewValue();
    updateBoard(board);
  }

  public void updateBoard(Board newBoard) {
    LOG.info("updateBoard called");
    Board board = newBoard;
    text.updateText(board.getPlayer());
    for (PositionState cell: board) {
      Position pos = cell.position();
      if (cell.isPresent()) {
        myGrid[pos.row()][pos.column()].addPiece(PIECE_TYPES.get(cell.type()));
      }
      else {
        try {
          myGrid[pos.row()][pos.column()].removePiece();
        } catch (Exception e) {
          LOG.warn(myResources.getString("ExceptionThrown"), e);
          ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("ExceptionThrown"));
        }
      }
    }
    text.updateText(board.getPlayer());
  }

  /**
   * Ends game
   * @param winner
   */
  public void endGame(int winner) {
    text.gameIsWon(winner);
    LOG.info("gameOver! Player {} wins%n", (winner+1));
    displayGameOver(winner);
  }

  /**
   * Adds a marker to all the cells that are valid moves for the currently selected piece
   * @param validMoves - current Game Board
   */
  public void setValidMarkers(Set<Position> validMoves) {
    clearValidMarks();
    for(Position pos : validMoves){
      myGrid[pos.row()][pos.column()].addValidMarker();
    }
  }

  public Node getRoot() {
    return root;
  }

  private void makeBoard(int rows, int columns, double cellWidth, double cellHeight, File game)
      throws FileNotFoundException {
    Optional<String[][]> colorConfig = getCellColors(game);
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        Optional<String> color = colorConfig.isPresent() ? Optional.of(colorConfig.get()[row][column]) : Optional.empty();
        Cell temp = new Cell(row, column, cellWidth, cellHeight, color);
        gridRoot.add(temp.getMyRoot(), column, rows - row - 1); // documentation says the first input is column and the second is row
        myGrid[row][column] = temp;

        int finalRow = row;
        int finalColumn = column;

        myGrid[row][column].getMyRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
          try {
            cellClicked(e, finalRow, finalColumn);
          } catch (OutOfBoardException ex) {
            LOG.warn(ex);
            ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("BoardOutOfBounds"));
          }
        });
      }
    }
    root.getChildren().add(gridRoot);
    gridRoot.setAlignment(Pos.CENTER);

    root.setAlignment(Pos.CENTER);
  }

  private void getMetadata(File game) {
    MetadataParser mdp = new MetadataParser();
    try {
      metadata = mdp.parse(game.listFiles(GameIcon.getConfigFile)[0]);
    }
    catch (FileNotFoundException e) {
      LOG.error(e);
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("FileNotFound"));
    }
  }

  private Optional<String[][]> getCellColors(File game) throws FileNotFoundException {
    CellParser cParser = new CellParser();
    Optional<String[][]> cellColors;
    try {
      cellColors = Optional.of(cParser.parse(game.listFiles(GameIcon.getConfigFile)[0]));
      return cellColors;
    }
    catch (JSONException e) {
      LOG.error(e);
      return Optional.empty();
    }
  }

  private void setPiecePaths(File game) throws FileNotFoundException {
    PieceParser parser = new PieceParser();
    String name = metadata.get("gameName");
    Map<Integer, String> pieces = getConfigFile(game.listFiles(GameIcon.getConfigFile)[0], parser);
    for(Entry<Integer, String> entry : pieces.entrySet()) {
      if (gameIsUploadedFile) {
        File f = new File(game.getAbsolutePath() + entry.getValue());
        PIECE_TYPES.put(entry.getKey(), f.toString());
      }
      else{
        PIECE_TYPES.put(entry.getKey(), GAME_PATH + name + entry.getValue());
      }
    }
  }

  private Map<Integer, String> getConfigFile(File game, PieceParser parser) {
    Map<Integer, String> pieces = null;
    try {
      pieces = parser.parse(game);
    }
    catch(FileNotFoundException e){
      LOG.error(myResources.getString("ConfigFileNotFound"));
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("ConfigFileNotFound"));
    }
    return pieces;
  }

  private void makeBoardBacking(double width, double height, Pair<Double, Double> cellSize, int rows, int cols) {

    Rectangle foundation = new Rectangle(width, height);
    foundation.setId("board-foundation");

    LOG.debug(cellSize.toString());
    double cellW = cellSize.getKey();
    double cellH = cellSize.getValue();


    Rectangle outline = new Rectangle((cellW*cols)+BOARD_OUTLINE_SIZE, (cellH*rows)+BOARD_OUTLINE_SIZE);
    outline.setId("board-outline");
    root.getChildren().addAll(foundation, outline);
  }

  private Pair<Double, Double> calcCellSize(int rows, int cols, double width, double height) {
    double cellWidth = width / (rows + 1);
    double cellHeight = height / (cols + 1);

    return new Pair<>(cellWidth, cellHeight);
  }

  private void displayGameOver(int winner) {
    myController.resetGame();
    root.setEffect(new GaussianBlur());

    MessageView pauseView = new MessageView(
        getGameOverText(winner),
        myResources.getString("NewGame"), cssFilePath, language);

    Stage popupStage = pauseView.getStage();
    pauseView.getReturnToGame().setOnAction(event -> {
      root.setEffect(null);
      popupStage.hide();
    });
    popupStage.show();
  }

  private String getGameOverText(int winner) {
    System.out.println(myController.getPlayerManager().getScores().toString());
    return MessageFormat.format(myResources.getString(winner==-1 ? "Draw" : "GameOver") +
        getScoreboard(), (winner+1));
  }

  private String getScoreboard() {
    String scoreboard = "";
    Map<Integer, Integer> scores = myController.getPlayerManager().getScores();
    for (int player : scores.keySet()) {
      scoreboard = scoreboard + MessageFormat.format(myResources.getString("Scoreboard"),
          player+1, scores.get(player));
    }
    System.out.println(scoreboard);
    return scoreboard;
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

  private void selectCell(int row, int column) {
    clearCellSelection();
    Cell cell = myGrid[row][column];
    if (cell.containsPiece()) {
      myGrid[row][column].addSelectedHighlight();
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
    int row = index / myGrid.length;
    int column = index % myGrid[0].length;
    return new Position(row, column);
  }


}
