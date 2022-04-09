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
    if (!board.isEmpty(x, y) && board.getPieceRecord(x, y).get().player() == board.getPlayer() || board.isEmpty(x, y)) {
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
    PieceCondition empty0 = new IsEmpty(new int[]{1, 1});
    PieceCondition occupied0 = new IsOccupied(new int[]{0, 0});
    PieceCondition isPlayer0 = new IsPlayer(new int[]{0});
    PieceCondition isPlayersPiece = new IsPlayerPiece(new int[]{0, 0, 0});
    PieceCondition[] conditions = new PieceCondition[]{empty0, occupied0, isPlayer0};
    Action[] actions = new Action[]{new Move(new int[]{0, 0, 1, 1})};

    getMoves().add(new Rule(conditions, actions, 1, 1));

    PieceCondition empty01 = new IsEmpty(new int[]{-1, 1});
    PieceCondition occupied01 = new IsOccupied(new int[]{0, 0});
    PieceCondition isPlayer01 = new IsPlayer(new int[]{0});
    PieceCondition isPlayersPiece01 = new IsPlayerPiece(new int[]{0, 0, 0});
    PieceCondition[] conditions01 = new PieceCondition[]{empty01, occupied01, isPlayer01};
    Action[] actions01 = new Action[]{new Move(new int[]{0, 0, -1, 1})};

    getMoves().add(new Rule(conditions01, actions01, -1, 1));

    PieceCondition empty1 = new IsEmpty(new int[]{2, 2});
    PieceCondition isPlayer1 = new IsPlayer(new int[]{0});
    PieceCondition occupied1 = new IsOccupied(new int[]{0, 0});
    PieceCondition occupied2 = new IsOccupied(new int[]{1, 1});
    PieceCondition isOpposite = new IsPlayerPiece(new int[]{1, 1, 1});

    PieceCondition[] conditions1 = new PieceCondition[]{empty1, isPlayer1, occupied1, occupied2, isOpposite};

    Action remove = new Remove(new int[]{1,1});
    Action move = new Move(new int[]{0, 0, 2, 2});
    Action[] actions1 = new Action[]{remove, move};

    getMoves().add(new Rule(conditions1, actions1, 2, 2));
  }

  private void createPlayer1Moves() {
    PieceCondition empty0 = new IsEmpty(new int[]{-1, -1});
    PieceCondition occupied0 = new IsOccupied(new int[]{0, 0});
    PieceCondition isPlayer0 = new IsPlayer(new int[]{1});
    PieceCondition[] conditions = new PieceCondition[]{empty0, occupied0, isPlayer0};
    Action[] actions = new Action[]{new Move(new int[]{0, 0, -1, -1})};

    getMoves().add(new Rule(conditions, actions, -1, -1));

    PieceCondition empty11 = new IsEmpty(new int[]{1, -1});
    PieceCondition occupied11 = new IsOccupied(new int[]{0, 0});
    PieceCondition isPlayer11 = new IsPlayer(new int[]{1});
    PieceCondition isPlayersPiece11 = new IsPlayerPiece(new int[]{0, 0, 0});
    PieceCondition[] conditions11 = new PieceCondition[]{empty11, occupied11, isPlayer11};
    Action[] actions11 = new Action[]{new Move(new int[]{0, 0, 1, -1})};

    getMoves().add(new Rule(conditions11, actions11, 1, -1));

    PieceCondition empty1 = new IsEmpty(new int[]{-2, -2});
    PieceCondition isPlayer1 = new IsPlayer(new int[]{1});
    PieceCondition occupied1 = new IsOccupied(new int[]{0, 0});
    PieceCondition occupied2 = new IsOccupied(new int[]{-2, -2});
    PieceCondition isOpposite = new IsPlayerPiece(new int[]{-1, -1, 0});

    PieceCondition[] conditions1 = new PieceCondition[]{empty1, isPlayer1, occupied1, occupied2, isOpposite};

    Action remove = new Remove(new int[]{-1,-1});
    Action move = new Move(new int[]{0, 0, -2, -2});
    Action[] actions1 = new Action[]{remove, move};

    getMoves().add(new Rule(conditions1, actions1, -2, -2));
  }
  //temporary to create win conditions
  private void createWinCondition(){
    BoardCondition noPiecesLeft = new PlayerHasNoPieces();
    Winner mostPieces = new MostPieces();

    getWinConditions().add(new WinCondition(new BoardCondition[]{noPiecesLeft}, mostPieces));
  }
  private void createDrawCondition(){

    BoardCondition noMovesLeft= new NoMovesLeft();
    getWinConditions().add(new WinCondition(new BoardCondition[]{noMovesLeft}, null));

  }
}
