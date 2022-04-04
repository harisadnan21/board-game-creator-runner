package oogasalad.engine.model.engine;

import java.util.HashSet;
import java.util.Set;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.action.Move;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.conditions.IsEmpty;
import oogasalad.engine.model.conditions.IsOccupied;
import oogasalad.engine.model.move.Movement;

public class PieceSelectionEngine extends Engine {

  private boolean myIsPieceSelected = false;
  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;

  public PieceSelectionEngine(Game game) {
    super(game);
    try {
      getGame().getBoard().placeNewPiece(5, 5, 0, 0);
      getGame().getBoard().placeNewPiece(3, 4, 0, 0);
      getGame().getBoard().placeNewPiece(0, 1, 0, 0);
      getGame().getBoard().placeNewPiece(1, 2, 0, 0);
    } catch (Exception e) {

    }
    createCheckersMove();
  }

  @Override
  public Board onCellSelect(int x, int y) throws OutOfBoardException {
    Board board = getGame().getBoard();
    Position cellClicked = new Position(x, y);
    if (!myIsPieceSelected) {
      // also needs to check that the piece belongs to the active player before selecting it
      // should piece selection be controlled by player?
      if (!board.isEmpty(x, y)) {
        myIsPieceSelected = true;
        mySelectedCell = new Position(x, y);
        myValidMoves = getValidMoves();
      }
    }
    else {
      for (Movement move: getMoves()) {
        if (move.isValid(board, mySelectedCell.i(), mySelectedCell.j()) && move.getRepresentativeCell(mySelectedCell.i(), mySelectedCell.j()).equals(cellClicked)) {
          Board newBoard = move.doMovement(board, mySelectedCell.i(), mySelectedCell.j());
          getGame().setBoard(newBoard);
          resetSelected();
          return newBoard;
        }
      }
    }
    return board;
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
    for (Movement move : getMoves()) {
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

  private void createCheckersMove() {
    Condition empty = new IsEmpty(new int[]{1, 1});
    Condition occupied = new IsOccupied(new int[]{0, 0});
    Condition[] conditions = new Condition[]{empty, occupied};
    Action[] actions = new Action[]{new Move(new int[]{0, 0, 1, 1})};

    getMoves().add(new Movement(conditions, actions, 1, 1));
  }
}
