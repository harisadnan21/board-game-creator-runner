package oogasalad.engine.model.engine;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.actions.Place;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;
import org.jooq.lambda.function.Consumer0;

@Deprecated
public class NoSelectionEngine extends Engine {

  public NoSelectionEngine(Game game, List<Move> moves, List<WinCondition> winConditions, Consumer<Board> update, Consumer<Set<Position>> setValidMarks, Consumer0 clearMarkers) {
    super(game, moves, winConditions, update, setValidMarks, clearMarkers);
    //createTicTacToeMove();
  }

  public void onCellSelect(int x, int y) throws OutOfBoardException {
    Board board = getGame().getBoard();

    for (Move move: getMoves()) {
      if (move.isValid(board, x, y)) {
        board = move.doMovement(board, x, y);
        checkForWin(board);
        getGame().setBoard(board);
        updateView(board);
      }
    }
    updateView(board);
  }

  @Override
  public Set<Move> getValidMoves(Board board, int i, int j) {
    return null;
  }

  @Override
  public Board getGameStateBoard() {
    return null;
  }

  private void createTicTacToeMove() {
    // should Conditions and Actions have the relative relationships build into them?
    PieceCondition[] conditions = new PieceCondition[]{new IsEmpty(new int[]{0, 0})};
    Action[] actions = new Action[]{new Place(new int[]{0, 0, 0, 0})};

    getMoves().add(new Move(conditions, actions, 0, 0));
  }
  //checks to see if any of the win conditions are satisfied and if they are it sets the winner on the board.
  private void checkForWin(Board board) {
    for(WinCondition winCondition : getWinConditions()){
      if(winCondition.isOver(board)){
        board.setWinner(winCondition.getWinner(board));
      }
    }
  }
}
