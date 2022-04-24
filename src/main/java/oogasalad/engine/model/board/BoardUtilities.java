package oogasalad.engine.model.board;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jooq.lambda.Seq;

public class BoardUtilities {

  public static Stream<PositionState> getSatisfyingPositionStatesStream(Board board,
      Predicate<PositionState> positionStatePredicate) {
    return board.getPositionStatesStream().filter(positionStatePredicate);
  }

  public static Stream<PositionState> getNotSatisfyingPositionStatesStream(Board board,
      Predicate<PositionState> positionStatePredicate) {
    var reversed = Predicate.not(positionStatePredicate);
    return board.getPositionStatesSeq().filter(reversed);
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
    Map<Integer, Integer> piecesByPlayer = Seq.seq(piecesByPlayer(board)).toMap(pair -> pair.v1, pair -> pair.v2.size());
    piecesByPlayer.putIfAbsent(Piece.PLAYER_ONE, 0);
    piecesByPlayer.putIfAbsent(Piece.PLAYER_TWO, 0);
    return piecesByPlayer;
  }

  public static Map<Integer, List<PositionState>> getRows(Board board) {
    return board.getPositionStatesSeq().groupBy(PositionState::i);
  }

  public static Map<Integer, List<PositionState>> getCols(Board board) {
    return board.getPositionStatesSeq().groupBy(PositionState::j);
  }
}
