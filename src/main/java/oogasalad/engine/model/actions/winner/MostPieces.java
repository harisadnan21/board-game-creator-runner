package oogasalad.engine.model.actions.winner;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.boards.BoardUtils;
import oogasalad.engine.model.board.components.Piece;
import oogasalad.engine.model.board.components.PositionState;
import org.jooq.lambda.Seq;
/**
 * Class that decides winner based on which player has more pieces currently on the board.
 * @author Robert Cranston
 */
public class MostPieces implements Winner {

  /**
   * Counts how many pieces both players currently have on the board and returns the one with the highest amount
   * @param board current board state
   * @return int representation of player with the most pieces
   */
  @Override
  public int decideWinner(Board board) {
    Seq<PositionState> nonEmptyPositionStates = BoardUtils.getNotSatisfyingPositionStatesSeq(board,
        positionState -> positionState.piece()== Piece.EMPTY);
    return nonEmptyPositionStates.map(PositionState::player).mode().get(); //Maybe change to optional if there is a tie?
  }
}
