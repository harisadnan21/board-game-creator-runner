package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.jooq.lambda.Seq;

// Make this board that display can use??
public interface DisplayableBoard extends Cloneable, Iterable<PositionState> {

  int getPlayer();

  PositionState getPositionStateAt(Position position);

  boolean hasPieceAtLocation(int row, int column);

  boolean isValidPosition(int x, int y);

  int getHeight();

  int getWidth();

  boolean isValidRow(int row);

  boolean isValidColumn(int column);

  Stream<PositionState> getPositionStatesStream();

  Seq<PositionState> getPositionStatesSeq();

  PositionState getPositionStateAt(int x, int y);

  // TODO: implement
  Map<Integer, List<PositionState>> getRows();

  // TODO: implement
  Map<Integer, List<PositionState>> getCols();

  @Override
  Iterator<PositionState> iterator();

  int getWinner();

}
