package oogasalad.engine.model.engine;

import java.util.List;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.conditions.piece_conditions.IsEmpty;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Rule;

public class NoSelectionEngine extends Engine {

  public NoSelectionEngine(Game game, List<Rule> rules, List<WinCondition> winConditions) {
    super(game, rules, winConditions);
    //createTicTacToeMove();
  }

  public Board onCellSelect(int x, int y) throws OutOfBoardException, CloneNotSupportedException {
    Board board = getGame().getBoard();

    for (Rule move: getMoves()) {
      if (move.isValid(board, x, y)) {
        board = move.doMovement(board, x, y);
        getGame().setBoard(board);
        return board;
      }
    }
    return board;
  }

  private void createTicTacToeMove() {
    // should Conditions and Actions have the relative relationships build into them?
    PieceCondition[] conditions = new PieceCondition[]{new IsEmpty(new int[]{0, 0})};
    Action[] actions = new Action[]{new Place(new int[]{0, 0, 0, 0})};

    getMoves().add(new Rule(conditions, actions, 0, 0));
  }
}
