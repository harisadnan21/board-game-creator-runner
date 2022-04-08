package oogasalad.engine.model.board;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import org.jooq.lambda.Seq;

// Make this board that display can use??
public interface DisplayableBoard extends Cloneable, Iterable<PositionState> {

  int getPlayer();

  PositionState getPositionStateAt(Position position);

  Boolean isPieceAtLocation(int row, int column);

  Boolean isPieceAtCoordinate(int x, int y);

  boolean isValidXY(int x, int y);

  int getHeight();

  int getWidth();

  Boolean isValidRow(int row);

  Boolean isValidColumn(int column);

  Stream<PositionState> getPositionStatesStream();

  Seq<PositionState> getPositionStatesSeq();

  PositionState getPositionStateAt(int x, int y);

  // TODO: implement
  Collection<Stream<PositionState>> getRows();

  // TODO: implement
  Collection<Stream<PositionState>> getCols();

  @Override
  Iterator<PositionState> iterator();

  int getWinner();

  Map<Object, Object> getPieceRecord(int x, int y);
}
