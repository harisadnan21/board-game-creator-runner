package oogasalad.engine.model.actions.winner;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PositionState;
import org.jooq.lambda.Seq;
/**
 * Class that decides winner based on which player still has moves left
 * @author Haris Adnan
 */
public class NoMovesForOnePlayer extends AbstractWinner {

  /**
   *
   * @param parameters unused field
   */
  public NoMovesForOnePlayer(int[] parameters) {
    super(parameters);
  }

  /**
   * Checks which player has moves left and returns the player with moves still left
   * @param board current board state
   * @return int representation of player with moves still left
   */
  @Override
  public int decideWinner(Board board) {
    Map<Integer, List<PositionState>> map = board.piecesByPlayer();
    Set<Integer> keySet = map.keySet();
    Iterator<Integer> keyIterator = keySet.iterator();
    while (keyIterator.hasNext()) {
      Integer player = keyIterator.next();
      if (map.get(player) == null) {
        return player;
      }
    }
    return -1;}
}
