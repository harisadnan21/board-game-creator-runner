package oogasalad.engine.model.engine;

import java.util.HashSet;
import java.util.Set;
import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.actions.Move;
import oogasalad.engine.model.actions.Remove;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.conditions.IsEmpty;
import oogasalad.engine.model.conditions.IsOccupied;
import oogasalad.engine.model.conditions.IsPlayer;
import oogasalad.engine.model.conditions.IsPlayerPiece;
import oogasalad.engine.model.move.Rule;

public class PieceSelectionEngine extends Engine {

  private boolean myIsPieceSelected = false;
  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;

  public PieceSelectionEngine(Game game) {
    super(game);
    try {
      Board board = getGame().getBoard();
      board.placeNewPiece(5, 5, 0, 0);
      board.placeNewPiece(4, 4, 0, 1);
      board.placeNewPiece(3, 1, 0, 0);
      board.placeNewPiece(3, 4, 0, 1);
      board.placeNewPiece(0, 1, 0, 0);
      board.placeNewPiece(1, 2, 0, 1);
    } catch (Exception e) {

    }
    createCheckersMove();
    createPlayer1Moves();
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
          getGame().setBoard(newBoard);
          resetSelected();
          return newBoard;
        }
      }
      resetSelected();
      board.setValidMoves(null);
      //So you have to reselect the piece
      //makePieceSelected(x, y);

    }
    System.out.println(board.getValidMoves());
    return board;
  }

  private void makePieceSelected(int x, int y) {
    Board board = getGame().getBoard();

    if (!board.isEmpty(x, y) && board.getPieceRecord(x, y).get().player() == board.getPlayer()) {
      myIsPieceSelected = true;
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

  // while we don't have json reading, this method is used to create a rule
  private void createCheckersMove() {
    Condition empty0 = new IsEmpty(new int[]{1, 1});
    Condition occupied0 = new IsOccupied(new int[]{0, 0});
    Condition isPlayer0 = new IsPlayer(new int[]{0});
    Condition isPlayersPiece = new IsPlayerPiece(new int[]{0, 0, 0});
    Condition[] conditions = new Condition[]{empty0, occupied0, isPlayer0};
    Action[] actions = new Action[]{new Move(new int[]{0, 0, 1, 1})};

    getMoves().add(new Rule(conditions, actions, 1, 1));

    Condition empty1 = new IsEmpty(new int[]{2, 2});
    Condition isPlayer1 = new IsPlayer(new int[]{0});
    Condition occupied1 = new IsOccupied(new int[]{0, 0});
    Condition occupied2 = new IsOccupied(new int[]{1, 1});
    Condition isOpposite = new IsPlayerPiece(new int[]{1, 1, 1});

    Condition[] conditions1 = new Condition[]{empty1, isPlayer1, occupied1, occupied2, isOpposite};

    Action remove = new Remove(new int[]{1,1});
    Action move = new Move(new int[]{0, 0, 2, 2});
    Action[] actions1 = new Action[]{remove, move};

    getMoves().add(new Rule(conditions1, actions1, 2, 2));
  }

  private void createPlayer1Moves() {
    Condition empty0 = new IsEmpty(new int[]{-1, -1});
    Condition occupied0 = new IsOccupied(new int[]{0, 0});
    Condition isPlayer0 = new IsPlayer(new int[]{1});
    Condition[] conditions = new Condition[]{empty0, occupied0, isPlayer0};
    Action[] actions = new Action[]{new Move(new int[]{0, 0, -1, -1})};

    getMoves().add(new Rule(conditions, actions, -1, -1));

    Condition empty1 = new IsEmpty(new int[]{-2, -2});
    Condition isPlayer1 = new IsPlayer(new int[]{1});
    Condition occupied1 = new IsOccupied(new int[]{0, 0});
    Condition occupied2 = new IsOccupied(new int[]{-2, -2});
    Condition isOpposite = new IsPlayerPiece(new int[]{-1, -1, 0});

    Condition[] conditions1 = new Condition[]{empty1, isPlayer1, occupied1, occupied2, isOpposite};

    Action remove = new Remove(new int[]{-1,-1});
    Action move = new Move(new int[]{0, 0, -2, -2});
    Action[] actions1 = new Action[]{remove, move};

    getMoves().add(new Rule(conditions1, actions1, -2, -2));
  }
}
