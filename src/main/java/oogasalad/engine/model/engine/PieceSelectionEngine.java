package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.utilities.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import org.jooq.lambda.function.Consumer0;

public class PieceSelectionEngine extends Engine {
  private static final Logger LOG = LogManager.getLogger(PieceSelectionEngine.class);
  private boolean myIsPieceSelected = false;
  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;
  private List<Move> myPersistentRules = new ArrayList<>();
  private Map<Integer, Player> myPlayers = new HashMap<>();

  public PieceSelectionEngine(Game game, List<Move> moves,
      List<WinCondition> winConditions, Consumer<Board> update, Consumer<Set<Position>> setValidMarks, Consumer0 clearMarkers) {
    super(game, moves, winConditions, update, setValidMarks, clearMarkers);
    myPlayers.put(0, new HumanPlayer(this, setValidMarks, clearMarkers));
    myPlayers.put(1, new HumanPlayer(this, setValidMarks, clearMarkers));
  }

  public void gameLoop() {
    while(true) {
      for (int playerID: myPlayers.keySet()) {
        Player player = myPlayers.get(playerID);
        Pair<Position, Move> choice = player.chooseMove(this, getGameStateBoard());
      }
    }
  }

  @Override
  public void onCellSelect(int x, int y) throws OutOfBoardException {
    Board board = getGame().getBoard();
    Position cellClicked = new Position(x, y);
    if (!myIsPieceSelected) {
      makePieceSelected(x, y);
    }
    else {
      for (Move move: getMoves()) {
        if (move.isValid(board, mySelectedCell.i(), mySelectedCell.j()) && move.getRepresentativeCell(mySelectedCell.i(), mySelectedCell.j()).equals(cellClicked)) {
          Board newBoard = move.doMovement(board, mySelectedCell.i(), mySelectedCell.j());
          newBoard = checkForWin(newBoard);
          getGame().setBoard(newBoard);
          resetSelected();
          clearMarkers();
          return;
        }
      }
      resetSelected();
      clearMarkers();
    }

    LOG.info("Valid Moves for selected piece are {} ", board.getValidMoves());
  }

  //checks to see if any of the win conditions are satisfied and if they are it sets the winner on the board.
  private Board checkForWin(Board board) {
    for(WinCondition winCondition : getWinConditions()){
      if(winCondition.isOver(board)){
        return board.setWinner(winCondition.getWinner(board));
      }
    }
    return board;
  }

  private void makePieceSelected(int x, int y) {
    Board board = getGame().getBoard();

    // makes position selected if board has piece with current player or position is empty
    // should this condition exist, or should it be baked into rules?
    if (!board.isEmpty(x, y) && board.getPositionStateAt(x, y).player() == board.getPlayer() || board.isEmpty(x, y)) {
      myIsPieceSelected = true;
      mySelectedCell = new Position(x, y);
      myValidMoves = getValidMoves();
      setMarkers(myValidMoves);
      LOG.info("{} valid moves for this piece\n", myValidMoves.size());
    }
  }

  private boolean hasValidMove(int x, int y) {
    return myValidMoves.contains(new Position(x, y));
  }

  /**
   * maybe should return Map<Position, Movement>
   * @return
   */
  private Set<Position> getValidMoves() {
    Set<Position> validMoves = new HashSet<>();
    for (Move move : getMoves()) {
      if (move.isValid(getGame().getBoard(), mySelectedCell.i(), mySelectedCell.j())) {
        validMoves.add(move.getRepresentativeCell(mySelectedCell.i(), mySelectedCell.j()));
      }
    }
    return validMoves;
  }

  /**
   * Returns valid moves for given position and board
   * If you want to use the game's current board, you can
   * use the gameGameStateBoard() function
   *
   * Note: this returns all available moves, not specific to
   * a player
   *
   * @param board
   * @param i
   * @param j
   * @return
   */
  @Override
  public Set<Move> getValidMoves(Board board, int i, int j) {
    // If a player wants to display the moves on a screen, they should use the representative point
    // of the move with rule.getRepresentativePoint(i, j)
    Set<Move> moves = new HashSet<>();
    for (Move move: getMoves()) {
      if (move.isValid(board, i, j)) {
        moves.add(move);
      }
    }
    return moves;
  }

  /**
   * Outer map
   * @param board
   * @return two dimensional map, where outer map key is the 'reference point' for the move, while the
   * inner map key is the 'representative point' of the move, or the
   */
  public Map<Position, Set<Move>> getAllValidMoves(Board board) {
    Map<Position, Set<Move>> allMoves = new HashMap<>();
    for (PositionState cell: board) {
      Position position = cell.position();
      allMoves.put(position, getValidMoves(board, position.i(), position.j()));
    }
    return allMoves;
  }

  /**
   * Plays a turn, presumably executed from the Player class
   * Updates the game's board with the resultant board
   * @param player
   * @param move
   * @param i
   * @param j
   */
  public void playTurn(Player player, Move move, int i, int j) {
    Board board = getGameStateBoard();
    int activePlayer = board.getPlayer();
    if (move.isValid(getGameStateBoard(), i, j) && myPlayers.get(activePlayer) == player) {
      board = move.doMovement(board, i, j);
    }
    board = applyRules(board);
    getGame().setBoard(board);
  }

  /**
   * Applies persistent rules to a board
   * @param board
   * @return
   */
  public Board applyRules(Board board) {
    for (Move rule: myPersistentRules) {
      board = rule.doMovement(board, 0, 0);
    }
    return board;
  }



  /**
   *
   * @return the board which is at the head of the game's board stack
   */
  @Override
  public Board getGameStateBoard() {
    return getGame().getBoard();
  }

  private void resetSelected() {
    myIsPieceSelected = false;
    mySelectedCell = null;
    myValidMoves = null;
  }
}
