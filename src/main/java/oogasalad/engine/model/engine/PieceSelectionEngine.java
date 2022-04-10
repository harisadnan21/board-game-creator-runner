package oogasalad.engine.model.engine;

import oogasalad.engine.model.actions.winner.MostPieces;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;
import oogasalad.engine.model.conditions.board_conditions.PlayerHasNoPieces;
import oogasalad.engine.model.conditions.board_conditions.NoMovesLeft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.actions.Move;
import oogasalad.engine.model.actions.Remove;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.conditions.piece_conditions.IsOccupied;
import oogasalad.engine.model.conditions.piece_conditions.IsPlayer;
import oogasalad.engine.model.conditions.piece_conditions.IsPlayerPiece;
import oogasalad.engine.model.move.Rule;

public class PieceSelectionEngine extends Engine {
  private static final Logger LOG = LogManager.getLogger(PieceSelectionEngine.class);
  private boolean myIsPieceSelected = false;
  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;

  public PieceSelectionEngine(Game game, List<Rule> rules,
      List<WinCondition> winConditions) {
    super(game, rules, winConditions);

    //createWinCondition();
    //createCheckersMove();
    //createPlayer1Moves();

  }

  @Override
  public Board onCellSelect(int x, int y) throws OutOfBoardException {
    Board board = getGame().getBoard();
    Position cellClicked = new Position(x, y);
    if (!myIsPieceSelected) {
      makePieceSelected(x, y);
    }
    else {
      for (Rule move: getMoves()) {
        if (move.isValid(board, mySelectedCell.i(), mySelectedCell.j()) && move.getRepresentativeCell(mySelectedCell.i(), mySelectedCell.j()).equals(cellClicked)) {
          Board newBoard = move.doMovement(board, mySelectedCell.i(), mySelectedCell.j());
          checkForWin(newBoard);
          getGame().setBoard(newBoard);
          resetSelected();
          return newBoard;
        }
      }
      resetSelected();
      board.setValidMoves(null);
    }

    LOG.info("Valid Moves for selected piece are {} ", board.getValidMoves());
    return board;
  }

  //checks to see if any of the win conditions are satisfied and if they are it sets the winner on the board.
  private void checkForWin(Board board) {
    for(WinCondition winCondition : getWinConditions()){
      if(winCondition.isOver(board)){
        board.setWinner(winCondition.getWinner(board));
      }
    }
  }

  private void makePieceSelected(int x, int y) {
    Board board = getGame().getBoard();

    // makes position selected if board has piece with current player or position is empty
    // should this condition exist, or should it be baked into rules?
    if (!board.isEmpty(x, y) && board.getPositionStateAt(x, y).piece().player() == board.getPlayer() || board.isEmpty(x, y)) {  myIsPieceSelected = true;
      mySelectedCell = new Position(x, y);
      myValidMoves = getValidMoves();
      board.setValidMoves(myValidMoves);
      System.out.printf("%d valid moves for this piece\n", myValidMoves.size());
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
    for (Rule move : getMoves()) {
      if (move.isValid(getGame().getBoard(), mySelectedCell.i(), mySelectedCell.j())) {
        validMoves.add(move.getRepresentativeCell(mySelectedCell.i(), mySelectedCell.j()));
      }
    }
    return validMoves;
  }

  private void resetSelected() {
    myIsPieceSelected = false;
    mySelectedCell = null;
    myValidMoves = null;
  }
}
