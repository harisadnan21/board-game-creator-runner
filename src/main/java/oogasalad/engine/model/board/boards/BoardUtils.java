package oogasalad.engine.model.board.boards;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import oogasalad.engine.model.board.components.Piece;
import oogasalad.engine.model.board.components.PositionState;
import org.jooq.lambda.Seq;

public class BoardUtils {

  public static Stream<PositionState> getSatisfyingPositionStatesStream(Board board,
      Predicate<PositionState> positionStatePredicate) {
    return board.getPositionStatesStream().filter(positionStatePredicate);
  }

  public static Stream<PositionState> getNotSatisfyingPositionStatesStream(Board board,
      Predicate<PositionState> positionStatePredicate) {
    return board.myBoard.values().filterNot(positionStatePredicate).toJavaStream();
  }

  public static Seq<PositionState> getNotSatisfyingPositionStatesSeq(Board board,
      Predicate<PositionState> positionStatePredicate) {
    return Seq.seq(getNotSatisfyingPositionStatesStream(board, positionStatePredicate));
  }

  public static Seq<PositionState> getSatisfyingPositionStatesSeq(Board board,
      Predicate<PositionState> positionStatePredicate) {
    return Seq.seq(getSatisfyingPositionStatesStream(board, positionStatePredicate));
  }

  public static Map<Integer, List<PositionState>> piecesByPlayer(Board board) {
    return board.getPositionStatesSeq().groupBy(PositionState::player);
  }

  public static Map<Integer, Integer> numPiecesByPlayer(Board board){
    Map<Integer, Integer> map = Seq.seq(piecesByPlayer(board)).toMap(pair -> pair.v1, pair -> pair.v2.size());
    map.putIfAbsent(Piece.PLAYER_ONE, 0);
    map.putIfAbsent(Piece.PLAYER_TWO, 0);
    return map;
  }
}
