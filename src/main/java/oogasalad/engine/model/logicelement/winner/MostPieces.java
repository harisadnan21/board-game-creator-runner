package oogasalad.engine.model.logicelement.winner;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.utilities.BoardUtilities;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.PositionState;
import org.jooq.lambda.Seq;
/**
 * Class that decides winner based on which player has more pieces currently on the board.
 * @author Robert Cranston
 */
public class MostPieces extends AbstractWinDecision {

  /**
   *
   * @param parameters unused
   */
  public MostPieces(int[] parameters) {
    super(parameters);
  }

  /**
   * Counts how many pieces both players currently have on the board and returns the one with the highest amount
   * @param board current board state
   * @return int representation of player with the most pieces
   */
  @Override
  public int decideWinner(Board board) {
    Seq<PositionState> nonEmptyPositionStates = BoardUtilities.getNotSatisfyingPositionStatesSeq(board,
        positionState -> positionState.piece()== Piece.EMPTY);
    return nonEmptyPositionStates.map(PositionState:: player).mode().get();
  }
}
